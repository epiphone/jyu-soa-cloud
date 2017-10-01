"""
Authentication- and authorization-related functionality.
"""
import jwt
from flask import abort, Blueprint, current_app as app, jsonify, request
from datetime import datetime, timedelta
from eve.auth import TokenAuth
from werkzeug.security import check_password_hash, generate_password_hash


JWT_ALGORITHM = 'HS512'
JWT_EXPIRATION = 60 * 60 * 48
JWT_ISSUER = 'rest-analytics'
JWT_SECRET = 'some_super_secret'  # TODO separate from version control


auth_blueprint = Blueprint('auth', __name__)

class JWTAuth(TokenAuth):
  def check_auth(self, token, allowed_roles, _resource, _method):
    try:
      payload = verify_token(token)
    except Exception as e:
      abort(401, str(e))

    self.set_request_auth_value(payload['id'])
    if not allowed_roles or any(r in allowed_roles for r in payload['roles']):
      return payload

@auth_blueprint.route('/tokens', methods=['POST'])
def create_token():
  """
  Parse submitted credentials, return JWT if valid.
  """
  body = request.get_json(force=True)
  email, password = body.get('email'), body.get('password')
  if not email or not password:
    abort(422, '`email` and `password` fields required')

  user = app.data.driver.db['users'].find_one({'email': email})
  if user and is_authenticated(user, password):
    token = sign_token(user)
    return jsonify(token), 201

  abort(404, 'User not found')


def is_authenticated(user, password):
  """Return True if password matches given user model."""
  return check_password_hash(user.get('password'), password)


def on_insert_users(documents):
  """Hash passwords on user creation."""
  for document in documents:
    hashed_pw = generate_password_hash(document['password'])
    document['password'] = hashed_pw


def sign_token(user):
  """Return a signed user JWT."""
  payload = {
    'email': user['email'],
    'id': str(user['_id']),
    'roles': user['roles'],
    'exp': datetime.utcnow() + timedelta(seconds=JWT_EXPIRATION),
    'iat': datetime.utcnow(),
    'iss': JWT_ISSUER,
    'nbf': datetime.utcnow(),
  }
  return jwt.encode(payload, JWT_SECRET, algorithm=JWT_ALGORITHM)


def verify_token(token):
  """Return decoded JWT payload, throw error if invalid."""
  return jwt.decode(
    token,
    JWT_SECRET,
    issuer=JWT_ISSUER,
    algorithms=[JWT_ALGORITHM]
  )

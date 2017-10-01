"""
App starter script.
"""
import os

from eve import Eve
from eve_swagger import add_documentation, swagger
from flask import abort, jsonify, request

from auth import auth_blueprint, JWTAuth, on_insert_users


app = Eve(auth=JWTAuth, settings='settings.py')
app.register_blueprint(swagger)
app.register_blueprint(auth_blueprint)

app.on_insert_users += on_insert_users


# Error handler:
def handle_error(e):
  if not hasattr(e, 'code'):
    e.code = 500
    e.description = 'internal server error'

  response = {
    'status': 'ERR',
    'error': {'code': e.code, 'message': e.description}
  }
  return jsonify(response), e.code

for code in app.config['STANDARD_ERRORS']:
  app.register_error_handler(code, handle_error)


# Display the custom /tokens path on Swagger docs:
add_documentation({'paths': {'/tokens': {'post': {
  'summary': 'JWT Authentication',
  'parameters': [
    {
      'in': 'body',
      'name': 'credentials',
      'required': True,
      'schema': {
        'type': 'object',
        'properties': {
          'email': {
            'type': 'string',
            'required': True
          },
          'password': {
            'type': 'string',
            'required': True
          }
        }
      }
    }
  ],
  'responses': {
    '201': {'description': 'JWT token with user info encoded in payload'},
    '404': {'description': 'User not found'},
    '422': {'description': 'Invalid parameters'}
  },
  'tags': ['Tokens']
}}}})

# Display auth header on Swagger docs (https://github.com/pyeve/eve-swagger/issues/42):
add_documentation({'securityDefinitions': {
  'Authorization': {
    'type': 'apiKey',
    'name': 'Authorization',
    'in': 'header'
  }
}})

for resource, rd in app.config['DOMAIN'].items():
  if rd.get('disable_documentation') or resource.endswith('_versions'):
    continue

  methods = rd['resource_methods']
  url = '/%s' % rd['url']
  for method in methods:
    add_documentation({'paths': {url: {method.lower(): {'security': [{'Authorization': []}]}}}})

  methods = rd['item_methods']
  item_id = '%sId' % rd['item_title'].lower()
  url = '/%s/{%s}' % (rd['url'], item_id)
  for method in methods:
    add_documentation({'paths': {url: {method.lower(): {'security': [{'Authorization': []}]}}}})


# Use env variable for port in production, run in debug mode locally:
if __name__ == '__main__':
  if 'PORT' in os.environ:
    app.run(host='0.0.0.0', port=int(os.environ.get('PORT')))
  else:
    app.run(port=5000, debug=True, use_reloader=True)

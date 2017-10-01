"""
App starter script.
"""
import os

from eve import Eve
from eve_swagger import add_documentation, swagger
from flask import abort, jsonify, request
from flask_cors import CORS

from auth import auth_blueprint, JWTAuth, on_insert_users


app = Eve(auth=JWTAuth, settings='settings.py')
CORS(app)
app.register_blueprint(swagger)
app.register_blueprint(auth_blueprint)

app.on_insert_users += on_insert_users

def on_inserted_users(items):
  # Add a duplicate user_id field to user to allow resource-based auth: https://github.com/pyeve/eve/issues/107
  users = app.data.driver.db['users']
  for item in items:
    users.update_one({'_id': item['_id']}, {'$set': {'user_id': item['_id']}})

app.on_inserted_users += on_inserted_users


def handle_error(e):
  """Custom error handler."""
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
add_documentation({
  'paths': {
    '/tokens': {'post': {
      'summary': 'JWT Authentication',
      'parameters': [{
        'in': 'body',
        'name': 'credentials',
        'required': True,
        'schema': {
          'type': 'object',
          'properties': {
            'email': {'type': 'string', 'required': True},
            'password': {'type': 'string', 'required': True}
          }
        }
      }],
      'responses': {
        '201': {'description': 'JWT token with user info encoded in payload'},
        '404': {'description': 'User not found'},
        '422': {'description': 'Invalid parameters'}
      },
      'tags': ['Tokens']
    }},
    '/users/{userId}/categories': {
      'get': {
        'summary': 'Retrieves one or more of user\'s categories',
        'parameters': [
          {'$ref': '#/parameters/User__id'}
        ],
        'responses': {
          '200': {
            'description': 'An array of user\'s categories',
            'schema': {
              'type': 'array',
              'items': {
                '$ref': '#/definitions/category'
              }
            }
          }
        },
        'tags': ['User categories'],
        'security': [{'Authorization': []}]
      }
    },
    '/users/{userId}/alarms': {
      'get': {
        'summary': 'Retrieves one or more of user\'s alarms',
        'parameters': [
          {'$ref': '#/parameters/User__id'}
        ],
        'responses': {
          '200': {
            'description': 'An array of user\'s alarms',
            'schema': {
              'type': 'array',
              'items': {
                '$ref': '#/definitions/Alarm'
              }
            }
          }
        },
        'tags': ['User alarms'],
        'security': [{'Authorization': []}]
      }
    },
    '/categories/{categoryId}/events': {
      'get': {
        'summary': 'Retrieves one or more of category\'s events',
        'parameters': [
          {'$ref': '#/parameters/category__id'}
        ],
        'responses': {
          '200': {
            'description': 'An array of category\'s events',
            'schema': {
              'type': 'array',
              'items': {
                '$ref': '#/definitions/Event'
              }
            }
          }
        },
        'tags': ['Category events'],
        'security': [{'Authorization': []}]
      }
    }
  }
})

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

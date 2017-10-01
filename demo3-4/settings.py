"""
App settings.
"""
import os

import resources


MONGO_URI = os.environ.get('MONGODB_URI', 'mongodb://localhost/analytics-rest-dev')

RESOURCE_METHODS = ['GET', 'POST']

ITEM_METHODS = ['GET', 'PATCH', 'PUT', 'DELETE']

# Disable concurrency control:
ENFORCE_IF_MATCH = False

SWAGGER_INFO = {
  'title': 'Analytics REST API',
  'version': '1.0',
  'description': 'A simple analytics REST API implementation; coursework for a SOA class.',
  'contact': {
    'name': 'AP',
    'url': 'http://github.com/epiphone'
  }
}

DOMAIN = resources.DOMAIN

XML = False

X_DOMAINS = '*'
# X_DOMAINS = [
#   'http://localhost:5000',
#   'http://editor.swagger.io',
#   'http://petstore.swagger.io',
#   'https://analytics-rest.herokuapp.com/'
# ]

X_HEADERS = ['api_key', 'Authorization', 'Content-Type', 'If-Match']

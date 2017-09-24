"""
App settings.
"""
import os


MONGO_URI = os.environ.get('MONGODB_URI', 'mongodb://localhost/analytics-rest-dev')

# Enable reads (GET), inserts (POST) and DELETE for resources/collections
# (if you omit this line, the API will default to ['GET'] and provide
# read-only access to the endpoint).
RESOURCE_METHODS = ['GET', 'POST']

# Enable reads (GET), edits (PATCH) and deletes of individual items
# (defaults to read-only item access).
ITEM_METHODS = ['GET', 'PATCH', 'DELETE']

# Disable concurrency control:
ENFORCE_IF_MATCH = False

SWAGGER_INFO = {
  'title': 'Analytics REST API',
  'version': '1.0',
  'description': 'A simple analytics REST API implementation; coursework for a SOA class.',
  'contact': {
    'name': 'AP',
    'url': 'http://github.com/epiphone'
  },
  # 'schemes': ['http', 'https']
}


# Our API will expose two resources (MongoDB collections): 'people' and
# 'works'. In order to allow for proper data validation, we define beaviour
# and structure.
people = {
  # 'title' tag used in item links.
  'item_title': 'person',

  # by default the standard item entry point is defined as
  # '/people/<ObjectId>/'. We leave it untouched, and we also enable an
  # additional read-only entry point. This way consumers can also perform GET
  # requests at '/people/<lastname>/'.
  'additional_lookup': {
    'url': 'regex("[\w]+")',
    'field': 'lastname'
  },

  # Schema definition, based on Cerberus grammar. Check the Cerberus project
  # (https://github.com/pyeve/cerberus) for details.
  'schema': {
    'firstname': {
      'type': 'string',
      'minlength': 1,
      'maxlength': 10,
    },
    'lastname': {
      'type': 'string',
      'minlength': 1,
      'maxlength': 15,
      'required': True,
      # talk about hard constraints! For the purpose of the demo
      # 'lastname' is an API entry-point, so we need it to be unique.
      'unique': True,
    },
    # 'role' is a list, and can only contain values from 'allowed'.
    'role': {
      'type': 'list',
      'allowed': ["author", "contributor", "copy"],
    },
    # An embedded 'strongly-typed' dictionary.
    'location': {
      'type': 'dict',
      'schema': {
        'address': {'type': 'string'},
        'city': {'type': 'string'}
      },
    },
    'born': {
      'type': 'datetime',
    },
  }
}

works = {
  # if 'item_title' is not provided Eve will just strip the final
  # 's' from resource name, and use it as the item_title.
  #'item_title': 'work',

  # We choose to override global cache-control directives for this resource.
  'cache_control': 'max-age=10,must-revalidate',
  'cache_expires': 10,

  'schema': {
    'title': {
      'type': 'string',
      'required': True,
    },
    'description': {
      'type': 'string',
    },
    'owner': {
      'type': 'objectid',
      'required': True,
      # referential integrity constraint: value must exist in the
      # 'people' collection. Since we aren't declaring a 'field' key,
      # will default to `people._id` (or, more precisely, to whatever
      # ID_FIELD value is).
      'data_relation': {
        'resource': 'people',
        # make the owner embeddable with ?embedded={"owner":1}
        'embeddable': True
      },
    },
  }
}

DOMAIN = {
  'people': people,
  'works': works,
}

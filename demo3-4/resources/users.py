"""
User resource.
"""

users = {
  # allow GET requests at '/users/<email>/':
  'additional_lookup': {
    'url': 'regex("[\w@\.]+")',
    'field': 'email'
  },
  'auth_field': 'email',
  'cache_control': '',
  'cache_expires': 0,
  'datasource': {
    'projection': {'password': 0} # hide password hash from return values
  },
  'public_methods': ['GET', 'POST'],
  'schema': {
    'email': {
      'type': 'string',
      'maxlength': 255,
      'regex': '^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$',
      'required': True,
      'unique': True
    },
    'password': {
      'type': 'string',
      'minlength': 5
    },
    'roles': {
      'type': 'list',
      'allowed': ['user', 'admin', 'superadmin'],
      'default': ['user']
    }
  }
}

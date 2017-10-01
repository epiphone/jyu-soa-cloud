alarms = {
  'auth_field': 'user_id',
  'public_methods': ['GET'],
  'schema': {
    'name': {
      'type': 'string',
      'required': True,
    },
    'description': {
      'type': 'string',
    },
    'category_id': {
      'type': 'objectid',
      'required': True,
      'data_relation': {
        'resource': 'categories',
        'embeddable': True
      },
    },
    'user_id': {
      'type': 'objectid',
      'required': True,
      'data_relation': {
        'resource': 'users',
        'embeddable': True
      },
    },
  }
}

user_alarms = {
  'auth_field': 'user_id',
  'schema': alarms['schema'],
  'datasource': {'source': 'alarms'},
  'url': 'users/<string:user_id>/alarms',
  'resource_methods': ['GET'],
  'item_methods': [],
  'public_methods': ['GET']
}

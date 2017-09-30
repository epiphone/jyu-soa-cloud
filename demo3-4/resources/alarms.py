alarms = {
  'schema': {
    'name': {
      'type': 'string',
      'required': True,
    },
    'description': {
      'type': 'string',
    },
    'category': {
      'type': 'objectid',
      'required': True,
      'data_relation': {
        'resource': 'categories',
        'embeddable': True
      },
    },
    'user': {
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
  'schema': alarms['schema'],
  'datasource': {'source': 'alarms'},
  'url': 'users/<string:user>/alarms',
  'resource_methods': ['GET'],
  'item_methods': []
}

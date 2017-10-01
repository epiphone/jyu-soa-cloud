categories = {
  'auth_field': 'user_id',
  'item_title': 'category',
  'public_methods': ['GET'],
  'schema': {
    'name': {
      'type': 'string',
      'required': True,
    },
    'description': {
      'type': 'string',
    },
    'user_id': {
      'type': 'objectid',
      'required': True,
      'data_relation': {
        'resource': 'users',
        'embeddable': True
      },
    }
  }
}

user_categories = {
  'auth_field': 'user_id',
  'schema': categories['schema'],
  'datasource': {'source': 'categories'},
  'disable_documentation': True,
  'url': 'users/<string:user_id>/categories',
  'resource_methods': ['GET'],
  'item_methods': [],
}

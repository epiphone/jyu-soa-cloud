events = {
  'public_methods': ['GET', 'POST'],
  'public_item_methods': ['GET'],
  'schema': {
    'meta': {
      'type': 'dict'
    },
    'timestamp': {
      'type': 'integer'
    },
    'category_id': {
      'type': 'objectid',
      'required': True,
      'data_relation': {
        'resource': 'categories',
        'embeddable': True
      },
    },
  }
}

category_events = {
  'schema': events['schema'],
  'datasource': {'source': 'events'},
  'disable_documentation': True,
  'url': 'categories/<string:category>/events',
  'resource_methods': ['GET'],
  'item_methods': [],
  'public_methods': ['GET']
}

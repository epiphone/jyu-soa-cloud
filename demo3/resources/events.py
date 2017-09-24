events = {
  'schema': {
    'meta': {
      'type': 'dict'
    },
    'timestamp': {
      'type': 'integer'
    },
    'category': {
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
  'url': 'categories/<string:category>/events',
  'resource_methods': ['GET'],
  'item_methods': []
}

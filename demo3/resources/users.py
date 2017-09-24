users = {
  # allow GET requests at '/users/<email>/':
  'additional_lookup': {
    'url': 'regex("[\w]+")',
    'field': 'email'
  },
  'schema': {
    'email': {
      'type': 'string',
      'maxlength': 255,
      'regex': '^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$',
      'required': True,
      'unique': True
    }
  }
}

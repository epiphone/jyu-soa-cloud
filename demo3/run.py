"""
App starter script.
"""
import os

from eve import Eve
# from eve_swagger import swagger
from flask import jsonify


app = Eve(settings='settings.py')
# app.register_blueprint(swagger)

# Error handler:
def handle_error(e):
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


# Use env variable for port in production, run in debug mode locally:
if __name__ == '__main__':
  if 'PORT' in os.environ:
    app.run(host='0.0.0.0', port=int(os.environ.get('PORT')))
  else:
    app.run(port=5000, debug=True, use_reloader=True)

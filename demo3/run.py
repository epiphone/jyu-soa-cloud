"""
App starter script.
"""
import os

from eve import Eve


if 'PORT' in os.environ:
  port = int(os.environ.get('PORT'))
  host = '0.0.0.0'
else:
  port = 5000
  host = '127.0.0.1'

app = Eve()
print('host=', host, 'port=', port)

@app.route('/test')
def test():
  return 'testinsssg'

if __name__ == '__main__':
  app.run(host=host, port=port)

# A simple analytics REST API

## Dependencies

- Python 3
- pipenv
- MongoDB

## Setup

- `pipenv install`

## Run locally

- `python run.py` (or with auto-reloading etc: ``FLASK_DEBUG=1 FLASK_APP=run.py flask run`)

## Deploy

- `git subtree push --prefix demo3 heroku master`

## API docs

TODO

GET     /events
GET     /events/:id
GET     /events?type=:type
POST    /events/

GET     /types/
GET     /types/:id
POST    /types/
PATCH   /types/:id

## Sources

https://github.com/heroku/python-getting-started
https://github.com/pyeve/eve-demo

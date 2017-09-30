"""REST resources module index"""
from resources.alarms import alarms, user_alarms
from resources.categories import categories
from resources.events import events, category_events
from resources.users import users


DOMAIN = {
  'alarms': alarms,
  'categories': categories,
  'category_events': category_events,
  'events': events,
  'users': users,
  'user_alarms': user_alarms
}

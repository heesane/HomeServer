## Broker settings.
broker_url = 'amqp://guest:guest@localhost:5672//'

# List of modules to import when the Celery worker starts.
imports = ('myapp.tasks',)

## Using the database to store task state and results.
result_backend = 'db+sqlite:///results.db'

# using serializer name
accept_content = ['json']

# or the actual content-type (MIME)
accept_content = ['application/json']

task_annotations = {'tasks.add': {'rate_limit': '10/s'}}
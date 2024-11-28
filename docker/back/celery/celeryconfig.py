from kombu import Exchange, Queue

task_queues = (
    Queue('normal', Exchange('normal',type='direct'), routing_key='normal'),
)

task_routes = {
    'tasks.add': {'queue': 'normal', 'routing_key': 'normal'},
}

## Broker settings.
broker_url = 'amqp://guest:guest@localhost:5672//'
result_backend = 'redis://'
## Using the database to store task state and results.


# using serializer name
accept_content = ['json']

# or the actual content-type (MIME)
accept_content = ['application/json']
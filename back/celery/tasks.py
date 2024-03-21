from celery import Celery

app = Celery('tasks', broker='amqps://guest:guest@localhost:5672//', backend='fastapi://')

@app.task
def add(x,y):
    return x+y

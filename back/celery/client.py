# client.py
from tasks import add, app

if __name__ == "__main__":
  result = add.delay(4, 4)

  print(result.ready())
  print(result.ready())
  print(result.ready())
  print(result.ready())
  print(result.get(timeout=1))
  print(result.get(timeout=1))
  print(result.get(timeout=1))
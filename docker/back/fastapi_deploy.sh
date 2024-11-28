cd fastapi
docker exec fastapi bash -c "alembic revision --autogenerate && alembic upgrade head"
exit 0
```
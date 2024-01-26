## Auto Run Docker Compose
echo "Auto Run Docker Compose"
# echo 사용 X

docker-compose -f ./back/docker-compose.yml up -d
docker-compose -f ./database/docker-compose.yml up -d
docker-compose -f ./devops/docker-compose.yml up -d
docker-compose -f ./front/docker-compose.yml up -d
docker-compose -f ./s3/docker-compose.yml up -d


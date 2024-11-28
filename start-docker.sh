#!/bin/bash

# Author : Heesane
# Description: This script will run the Docker Container

FILE_PATH="/home/$USER/server/docker"

sudo docker compose -f "$FILE_PATH/mq/docker-compose.mq.yml" up -d
sudo docker compose -f "$FILE_PATH/database/docker-compose.database.yml" up -d
sudo docker compose -f "$FILE_PATH/devops/docker-compose.devops.yml" up -d
sudo docker compose -f "$FILE_PATH/logging/docker-compose.logging.yml" up -d elasticsearch kibana logstash filebeat
sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.mq.yml" up -d rabbitmq
sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.kafka-prod.yml" up -d
sudo docker compose -f "$FILE_PATH/back/docker-compose.back.yml" up -d 
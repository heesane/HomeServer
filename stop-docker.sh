#!/bin/bash

# Author : Heesane
# Description: This script will run the Docker Container

FILE_PATH="/home/$USER/server/docker"

sudo docker compose -f "$FILE_PATH/mq/docker-compose.mq.yml" down
sudo docker compose -f "$FILE_PATH/database/docker-compose.database.yml" down
sudo docker compose -f "$FILE_PATH/devops/docker-compose.devops.yml" down
sudo docker compose -f "$FILE_PATH/logging/docker-compose.logging.yml" down elasticsearch kibana logstash filebeat
sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.mq.yml" down rabbitmq
sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.kafka-prod.yml" down
sudo docker compose -f "$FILE_PATH/back/docker-compose.back.yml" down 
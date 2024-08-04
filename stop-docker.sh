#!/bin/bash

# Author : Heesane
# Description: This script will run the Docker Container

FILE_PATH="/home/$USER/server"

sudo docker compose -f "$FILE_PATH/back/docker-compose.back.yml" down
sudo docker compose -f "$FILE_PATH/front/docker-compose.front.yml" down
sudo docker compose -f "$FILE_PATH/devops/docker-compose.monitoring.yml" down
sudo docker compose -f "$FILE_PATH/mq/docker-compose.mq.yml" down
sudo docker compose -f "$FILE_PATH/database/docker-compose.database.yml" down
sudo docker compose -f "$FILE_PATH/devops/docker-compose.devops.yml" down
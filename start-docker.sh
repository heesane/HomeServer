#!/bin/bash

# Author : Heesane
# Description: This script will run the Docker Container

FILE_PATH="/home/$USER/server"

sudo docker compose -f "$FILE_PATH/back/docker-compose.back.yml" up -d --build
sudo docker compose -f "$FILE_PATH/mq/docker-compose.mq.yml" up -d --build
sudo docker compose -f "$FILE_PATH/database/docker-compose.database.yml" up -d --build
sudo docker compose -f "$FILE_PATH/devops/docker-compose.devops.yml" up -d --build
#!/bin/bash

FILE_PATH="/home/$USER/server/docker"

if [ $# -lt 1 ]; then
    echo "사용법: $0 <parameter 1> example) back"
    exit 1
fi

if [ "$1" = "mq" ]; then
    sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.mq.yml" up -d --build
elif [ "$1" = "database" ]; then
    sudo docker compose -f "$FILE_PATH/database/docker-compose.database.yml" up -d --build
elif [ "$1" = "devops" ]; then
    sudo docker compose -f "$FILE_PATH/devops/docker-compose.devops.yml" up -d --build
elif [ "$1" = "logging" ]; then
    sudo docker compose -f "$FILE_PATH/logging/docker-compose.logging.yml" up -d --build elasticsearch kibana logstash filebeat
elif [ "$1" = "message_queue" ]; then
    sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.mq.yml" up -d --build rabbitmq
elif [ "$1" = "kafka" ]; then
    sudo docker compose -f "$FILE_PATH/message_queue/docker-compose.kafka-prod.yml" up -d --build
elif [ "$1" = "back" ]; then
    sudo docker compose -f "$FILE_PATH/back/docker-compose.back.yml" up -d --build
else
    echo "이미 등록된 서비스가 아님 : $1"
    echo "등록된 서비스 : mq, database, devops, logging, message_queue, kafka, back "
    exit 1
fi

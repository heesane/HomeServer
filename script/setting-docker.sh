#!/bin/bash

# Update Package & Install Dependencies
sudo apt-get update
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

# Add Docker’s official GPG key
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Add Docker Repository
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

# Update Package & Install Docker
sudo apt-get update
sudo apt-get install -y docker-ce

# Start Docker Service
sudo systemctl start docker
sudo systemctl enable docker

# Current User Add to Docker Group
sudo usermod -aG docker $USER

# Installation of Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# 설치 확인
docker --version
docker-compose --version

echo "Docker & Docker Compose Installation Complete."
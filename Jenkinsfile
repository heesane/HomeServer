pipeline {
    agent any
    
    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.back.yml'
        SPRING_DATASOURCE_PASSWORD="${env.SPRING_DATASOURCE_PASSWORD}"
        SPRING_DATASOURCE_URL="${env.SPRING_DATASOURCE_URL}"
        SPRING_DATASOURCE_USERNAME="${env.SPRING_DATASOURCE_USERNAME}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/heesane/HomeServer.git'
            }
        }

        stage('Test') {
            steps {
                script {
                    sh "docker --version"
                    sh "docker compose --version"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "cd back"
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} build"
                }
            }
        }

        stage('Deploy') {
            when {
                anyOf {
                    branch 'main'
                    branch 'master'
                    branch 'server'
                }
            }
            steps {
                script {
                    sh "cd back && docker compose -f ${DOCKER_COMPOSE_FILE} up -d"
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
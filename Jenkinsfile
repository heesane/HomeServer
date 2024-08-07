pipeline {
    agent any
    
    environment {
        DOCKER_COMPOSE_FILE = 'back/docker-compose.back.yml'
        SPRING_DATASOURCE_PASSWORD="${env.SPRING_DATASOURCE_PASSWORD}"
        SPRING_DATASOURCE_URL="${env.SPRING_DATASOURCE_URL}"
        SPRING_DATASOURCE_USERNAME="${env.SPRING_DATASOURCE_USERNAME}"
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                git branch: 'main', url: 'https://github.com/heesane/HomeServer.git'
            }
        }

        stage('Get Commit Message') {
            steps {
                script {
                    def gitCommitMessage = sh(
                        script: "git log -1 --pretty=%B",
                        returnStdout: true
                    ).trim()
                    echo "Commit Message: ${gitCommitMessage}"
                    env.GIT_COMMIT_MESSAGE = gitCommitMessage
                }
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
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} build"
                }
            }
        }

        stage('Deploy') {
            when {
                anyOf {
                    branch 'main'
                    branch 'local'
                    branch 'server'
                }
            }
            steps {
                script {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} up -d"
                }
            }
        }
    }

    post {
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
        success {
            slackSend (
                message: "성공: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL}). 최근 커밋: '${env.GIT_COMMIT_MESSAGE}'",
            )
        }
        failure {
            slackSend (
                message: "실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL}). 최근 커밋: '${env.GIT_COMMIT_MESSAGE}'",
            )
        }
    }
}
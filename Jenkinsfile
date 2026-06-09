pipeline {
    agent any

    stages {

        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Nehaa1408/Jenkins.git'
            }
        }

        stage('Build Movie Service') {
            steps {
                dir('movieservice/movie-service') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('movieservice/movie-service') {
                    sh 'docker build -t movie-service:${BUILD_NUMBER} .'
                }
            }
        }
    }
}
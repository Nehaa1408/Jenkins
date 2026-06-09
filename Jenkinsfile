pipeline {
    agent any

    stages {

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
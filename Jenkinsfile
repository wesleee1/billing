pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Compose') {
            steps {
                sh '''
                docker compose down || true
                docker compose up --build -d
                '''
            }
        }
    }

    post {
        success {
            echo 'Application deployed successfully.'
        }
    }
}
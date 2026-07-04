pipeline {
    agent any

    environment {
        IMAGE_NAME = "billing-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        CONTAINER_NAME = "billing-container"
    }

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

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f ${CONTAINER_NAME} || true
                docker run -d \
                  --name ${CONTAINER_NAME} \
                  -p 8080:8080 \
                  ${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}
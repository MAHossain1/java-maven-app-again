pipeline {
    agent any

    tools {
        maven 'maven-3.6'
    }

    stages {
        stage('Build Jar') {
            steps {
                echo 'Building JAR..'
                sh 'mvn package'
            }
        }
        stage('Build the Docker Image') {
            steps {
                echo 'building the docker image..'
                withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh 'docker build -t arman04/java-maven-app:jma-3.0 .'
                    sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    sh 'docker push arman04/java-maven-app:jma-3.0'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
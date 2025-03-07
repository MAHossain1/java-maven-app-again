def checkout() {
    checkout scmGit(
        branches: [[name: '*/main']], // Change if needed
        userRemoteConfigs: [[url: 'https://github.com/MAHossain1/jenkins-pipeline-app.git']]
    )
}

def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId: 'docker_hub_repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh 'docker build -t arman04/java-maven-app:jma-2.1 .'
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh 'docker push arman04/java-maven-app:jma-2.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
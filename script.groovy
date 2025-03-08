
def buildJar() {
    echo 'Building JAR..'
   sh 'mvn package -U'
} 

def buildImage() {
    echo 'building the docker image..'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh 'docker build -t arman04/java-maven-app:jma-3.1.0 .'
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh 'docker push arman04/java-maven-app:jma-3.1.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
def gv

pipeline {
    agent any

    tools {
        maven 'maven'
    }

       parameters {
        // string(name: 'VERSION', defaultValue: '1.0', description: 'Please enter the version of the application')
        choice(name: 'VERSION', choices: ['1.1.0', '2.1.0', '2.2.0'], description: 'Please select the version of the application')
        booleanParam(name: 'ExecuteTests', defaultValue: true, description: 'Please select the flag')
    }

    stages {
        
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }


        stage('test') {
            when {
                expression {
                    params.ExecuteTests
                }
            }

            steps {
                script {
                    echo "testing the application version ${params.VERSION}"
                    
                }
            }
        }

        // stage('Increment version') {
        //     steps {
        //         script {
        //             echo "Incrementing the version of the application"
        //             sh 'mvn build-helper:parse-version versions:set \
        //                 -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        //                 versions:commit'
        //             def matcher = readFile('pom.xml') =~ '<version>(.*)</version>'
        //             def version = matcher[0][1]
        //             env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        //         }
        //     }
        // }



        // stage('Build Jar') {
        //     steps {
        //         script {
        //             gv.buildJar()
        //         }
        //     }
        // }
        // stage('Build the Docker Image') {
        //     steps {
        //       script {
        //         gv.buildImage()
        //       }
        //     }
        // }
        // stage('Deploy') {
        //     steps {
        //         script {
        //             def dockerCmd = "docker run -d -p 8080:8080 arman04/java-maven-app:1.1.2-2"
        //             sshagent(['ec2-server-key']) {
        //                 sh "ssh -o StrictHostKeyChecking=no ec2-user@13.235.23.129 ${dockerCmd}"
        //             }
        //         }
        //     }
        // }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying docker image on EC2 using docker-compose.yml"
                    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
                    sshagent(['ec2-server-key']) {
                        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ec2-user@13.127.254.117:/home/ec2-user/"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.235.23.129 ${dockerComposeCmd}"
                    }
                }
            }
        }

        // 
        // stage('Commit version update') {
        //     steps {
        //         script {
        //             sh 'sudo chown -R jenkins:jenkins .'
        //             withCredentials([usernamePassword(credentialsId: 'githubPATjenkins', passwordVariable: 'PAT', usernameVariable: 'USERNAME')]) {
        //                 sh 'git config user.email "jenkins@example.com"'
        //                 sh 'git config user.name "Jenkins"'
        //                 sh 'git status'
        //                 sh 'git branch'
        //                 sh 'git config --list'
        //                 sh "git remote set-url origin https://${USERNAME}:${PAT}@github.com/MAHossain1/java-maven-app-again.git"
        //                 sh 'git add .'
        //                 sh '''
        //                     if git status --porcelain | grep .; then
        //                         git commit -m "Incrementing the version of the application"
        //                     else
        //                         echo "No changes to commit"
        //                     fi
        //                 '''
        //                 sh 'git push origin HEAD:main || echo "Push failed—check branch or remote"'
        //             }
        //         }
        //     }
        // }


        // stage('Commit version update') {
        //     steps {
        //         script {
        //             withCredentials([usernamePassword(credentialsId: 'githubPATjenkins', passwordVariable: 'PAT', usernameVariable: 'USERNAME')]) {
        //                 sh 'git config --global user.email "jenkins@example.com"'
        //                 sh 'git config --global user.name "Jenkins"'
        //                 sh 'git status'
        //                 sh 'git branch'
        //                 sh 'git config --list'
        //                 sh "git remote set-url origin https://${USERNAME}:${PAT}@github.com/MAHossain1/java-maven-app-again.git"
        //                 sh 'git add .'
        //                 sh '''
        //                     if git status --porcelain | grep .; then
        //                         git commit -m "Incrementing the version of the application"
        //                     else
        //                         echo "No changes to commit"
        //                     fi
        //                 '''
        //                 sh 'git push origin HEAD:main || echo "Push failed—check branch or remote"'
        //             }
        //         }
        //     }
        // }
    }
}
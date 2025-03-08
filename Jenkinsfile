def gv

pipeline {
    agent any

    tools {
        maven 'maven-3.6'
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

        stage('Increment version') {
            steps {
                script {
                    echo "Incrementing the version of the application"
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.*)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }



        stage('Build Jar') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('Build the Docker Image') {
            steps {
              script {
                gv.buildImage()
              }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
pipeline {
    environment {
        PATH = "$PATH:/usr/bin"
    }

    agent any

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-jdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3-jdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Testing....'
                sh 'mvn test'
            }
//             post {
//                 always {
//                     junit 'target/surefire-reports/*.xml'
//                 }
//             }
        }
        stage('Build docker image') {
            agent any
            steps {
                sh 'sh cmd.sh'
            }
        }
        stage('Push docker image to Docker Hub') {
            steps {
                echo 'Pushing to Docker Hub....'
//                 sh 'mvn dockerfile:push'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
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
            steps {
                sh 'mvn test'
//                 echo 'Testing....'
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
//                 sh "/usr/bin/docker-compose up --build -d"
                sh 'docker build -t fund-manager.war .'
            }
        }
        stage('Push docker image to Docker Hub') {
            steps {
//                 sh 'mvn dockerfile:push'
                echo 'Pushing to Docker Hub....'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
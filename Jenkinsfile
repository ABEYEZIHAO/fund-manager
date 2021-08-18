pipeline {
    environment {
        PATH = "$PATH:/usr/bin"
    }

    agent {
        docker {
            image 'maven:3-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
//                 sh 'mvn test'
                echo 'Testing....'
            }
//             post {
//                 always {
//                     junit 'target/surefire-reports/*.xml'
//                 }
//             }
        }
        stage('Build docker image') {
            steps {
//                 sh 'mvn dockerfile:build'
                echo "PATH is: $PATH"
                sh "sudo /usr/bin/docker-compose up --build -d"
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
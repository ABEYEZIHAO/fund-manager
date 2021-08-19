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


// stage ('Build docker image') { //here you can check how you can build even docker images inside container
//     agent {
//         docker {
//             image 'maven:latest'
//             args '-v /root/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock' //here we expose docker socket to container. Now we can build docker images in the same way as on host machine where docker daemon is installed
//         }
//     }
//     steps {
//         sh 'mvn -Ddocker.skip=false -Ddocker.host=unix:///var/run/docker.sock docker:build' //example of how to build docker image with pom.xml and fabric8 plugin
//     }
// }
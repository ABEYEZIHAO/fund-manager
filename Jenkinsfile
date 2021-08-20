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
                sh 'docker stop $(docker ps -a -q)'
                sh 'docker rm $(docker ps -a -q)'
                sh 'docker network rm $(docker network ls -q)'

                sh 'docker build -t fund-manager .'
                sh 'docker network create fundsys'
                sh 'docker run -d --name fund_sql --network fundsys --network-alias mysql -e MYSQL_ROOT_PASSWORD=reven2010 -e MYSQL_DATABASE=fundsys mysql:8.0.26'
                sh 'docker run -dp 8090:8090 -w /app -v "$(pwd):/app" --name fund_app --network fundsys -e MYSQL_HOST=mysql -e MYSQL_USER=root -e MYSQL_PASSWORD=reven2010 -e MYSQL_DB=fundsys fund-manager'
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
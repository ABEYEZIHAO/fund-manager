def projectName = "fund-manager"
def appName = "fund-manager-app"
def version = "0.0.1"
def dockerImageTag_app = "${appName}:${version}"
def mysqlName = "mysql-db"
def dockerImageTag_mysql = "${mysqlName}:${version}"

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
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build docker image') {
            agent any
            steps {
                sh "docker build -f Dockerfile-mysql -t ${dockerImageTag_mysql} ."
                sh "docker build -f Dockerfile-app -t ${dockerImageTag_app} ."
            }
        }
        stage('Run docker container') {
            agent any
            steps {
//                 sh 'sh cmd.sh'
            }
        }
        stage('Push docker image to Docker Hub') {
            steps {
                echo 'Pushing to Docker Hub....'
            }
        }
        stage('Deploy to Openshift') {
            agent any
            steps {
                sh "oc login https://devopsapac45.conygre.com:8443 --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project ${projectName} || oc new-project ${projectName}"
                sh "oc delete all --selector app=${appName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc delete all --selector app=${mysqlName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app -e MYSQL_ROOT_PASSWORD=secret ${dockerImageTag_mysql}"
                sh "oc new-app -e spring_datasource_url=jdbc:mysql://${mysqlName}:3306/fundsys ${dockerImageTag_app}"
                sh "oc expose svc/${appName}"
                sh "oc status"
            }
        }
    }
}
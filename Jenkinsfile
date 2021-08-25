def projectName = 'fund-manager'
// def version = "0.0.${currentBuild.number}"
// def dockerImageTag = "${projectName}:${version}"
def dockerImageTag = "${projectName}"

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
                sh 'docker build -t fund-manager .'
            }
        }
        stage('Run docker container') {
            agent any
            steps  {
                sh 'sh cmd.sh'
            }
        }
        stage('Push docker image to Docker Hub') {
            steps {
                echo 'Pushing to Docker Hub....'
//                 sh 'mvn dockerfile:push'
            }
        }
        stage('Deploy to Openshift') {
            agent any
            steps {
                sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project ${projectName} || oc new-project ${projectName}"
                sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app ${dockerImageTag}"
                sh "oc expose svc/${projectName}"
                sh "oc status"
            }
        }
    }
}
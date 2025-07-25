pipeline {
    agent any
    tools {
    maven 'maven_3_9_9'
    }
    stages {
    stage('Build') {
                steps {
                Checkout([$class:'GitSCM', branches:[[name:'*/master']], extensions:[], userRemoteConfigs:[[url:'https://github.com/AymenFarhani/Spring_Boot_Jenkins']]])
                bat "mvn clean install"
                }
        }
        stage ('Build docker image') {
        steps {
        script {
        bat 'docker build -t aymendockerhub/Spring_Boot_Jenkins .'
        }
        }
        }
    }

}
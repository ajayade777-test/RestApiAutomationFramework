pipeline {
    agent any
    tools {
        maven 'Maven_3.9.14'   // use the Maven tool you configured
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ajayade777-test/RestApiAutomationFramework.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
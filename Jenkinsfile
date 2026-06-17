pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                bat 'if exist allure-results rmdir /s /q allure-results'
                bat 'if exist allure-report rmdir /s /q allure-report'
            }
        }

        stage('Run Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            allure([
                results: [[path: 'allure-results']]
            ])
        }
    }
}

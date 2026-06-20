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
                bat 'if exist target\\allure-results rmdir /s /q target\\allure-results'
                bat 'if exist allure-report rmdir /s /q allure-report'
            }
        }

        stage('Run Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Copy Results') {
            steps {
                bat 'if not exist C:\\AutomationReports mkdir C:\\AutomationReports'
                bat 'xcopy target\\allure-results C:\\AutomationReports\\allure-results\\ /E /I /Y'
            }
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                results: [[path: 'target/allure-results']]
            ])

            archiveArtifacts(
                artifacts: 'target/allure-results/**',
                fingerprint: true
            )
        }
    }
}
pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            allure results: [[path: 'target/allure-results']]
        }
    }
}

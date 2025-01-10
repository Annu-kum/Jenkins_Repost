pipeline {
    agent any

    environment {
        TOMCAT_USER = 'tomcat'
        TOMCAT_PASS = 'tomcat'
        TOMCAT_URL = 'http://192.168.29.180:7272/manager/text'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/Annu-kum/Jenkins_Repost.git'
            }
        }
        stage('Build Project') {
            steps {
                script {
                    // Use Gradle wrapper to build the project
                    sh './gradlew clean build'
                }
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Deploy WAR to Tomcat using cURL
                    def warFile = 'build/libs/NewPRJ.war'
                    sh """
                    curl --upload-file ${warFile} \
                        --user ${TOMCAT_USER}:${TOMCAT_PASS} \
                        ${TOMCAT_URL}/deploy?path=/NewPRJ
                    """
                }
            }
        }
    }
    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}


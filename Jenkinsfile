pipeline {
    agent any

    environment {
        TOMCAT_USER = 'tomcat'
        TOMCAT_PASS = 'tomcat'
        TOMCAT_URL = 'http://localhost:7272/manager/html'
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
                    // Use Gradle wrapper to build the project on Windows
                    bat '.\\gradlew.bat clean build'
                }
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Path to the WAR file
                    def warFile = 'build\\libs\\NewPRJ.war'

                    // Ensure WAR file exists before deployment
                    if (fileExists(warFile)) {
                        // Deploy WAR to Tomcat using cURL
                        bat """
                        curl --upload-file ${warFile} ^
                            --user ${TOMCAT_USER}:${TOMCAT_PASS} ^
                            ${TOMCAT_URL}/deploy?path=/NewPRJ
                        """
                    } else {
                        error "WAR file not found at ${warFile}"
                    }
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

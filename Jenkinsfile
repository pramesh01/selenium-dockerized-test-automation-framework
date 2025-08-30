pipeline{
    agent any
    stages{
        stage('building jar'){
            steps{
                bat "mvn clean package -DskipTests"
                bat "mvn clean test-compile"
                bat "mvn dependency:copy-dependencies"
            }  }
        stage('build image'){
            steps{
                bat "docker build -t pramesh11/selenium-docker-framework ."
            } 
             }
        stage('push image'){
              environment{
                DOCKER_HUB=credentials('login_credentials')
            }
            steps{
              bat "docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%"
              bat "docker push pramesh11/selenium-docker-framework:latest"
              bat "docker tag pramesh11/selenium-docker-framework:latest   pramesh11/selenium-docker-framework:${env.BUILD_NUMBER}"
              bat "docker push pramesh11/selenium-docker-framework:${env.BUILD_NUMBER}"
  } 
    } 
     } 
     post{
        always{
            bat "docker logout"
        }
     }
     }

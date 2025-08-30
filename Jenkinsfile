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
            steps{
              bat "docker push pramesh11/selenium-docker-framework"
  } 
    } 
     } 
     }

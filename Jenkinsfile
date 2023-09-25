pipeline {
 agent any
  parameters{
     string(name: 'BRANCH', defaultValue: 'main', description:' anythings')
   }
 tools {
 maven 'Maven'
 }
 environment {
     SONAR_URL = "http://localhost:9000"
   }
 stages {
 stage("Source") {
 steps {
 git branch: 'main', url: 'https://github.com/mvffUnderCover/Projet_gl_2023.git'
 }
 }
 stage("Parallel stage"){
  parallel{
      stage("Maven version"){
          steps{
              bat "mvn --version"
          }
        }
        stage("Build") {
         steps {
         bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
         }
         }
  }

 }

 stage("SonarQube Analysis") {
 steps {
 bat 'mvn sonar:sonar -Dsonar.host.url=$SONAR_URL'
 }
 }
 stage('Approve Deployment') {
 input {
 message "Do you want to proceed for deployment?"
 }
 steps {
 bat 'echo "Deploying into Server"'
 }
 }
 }
 post {
 aborted {
 echo "Sending message to agent"
 }
 success {
       emailext(
         subject: "Build Successful: ${currentBuild.fullDisplayName}",
         body: "The build was successful. No further action is required.\n \n Build URL: $BUILD_URL",
         recipientProviders: [culprits(), developers()],
         replyTo: "cmakhtar497@gmail.com",
         to: "seydi3369@gmail.com"
       )
     }
     failure {
       emailext(
         subject: "Build Failed: ${currentBuild.fullDisplayName}",
         body: "The build has failed. Please investigate and take necessary action. \n \n Build URL: $BUILD_URL",
         recipientProviders: [culprits(), developers()],
         replyTo: "cmakhtar497@gmail.com",
         to: "seydi3369@gmail.com"
       )
     }


 }
}

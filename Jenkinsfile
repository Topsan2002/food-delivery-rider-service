pipeline {
     agent any
     environment {
          AWS_REGION = 'ap-southeast-1'  // Replace with your region
          AWS_ACCOUNT_ID = credentials('AWS_ACCOUNT_ID')
          ECR_REPO_NAME = 'food-delivery-rider-service'  // Replace with your ECR repository name
//           IMAGE_TAG = "${env.BUILD_ID}"  // Or use 'latest' or any other tag
          IMAGE_TAG = "latest"
          WORKSPACE = "/var/lib/jenkins/workspace/food-delivery-rider-service"
     }
     stages {
             stage('Clone Repo') {
                 steps {
                     git url: 'https://github.com/Topsan2002/food-delivery-rider-service.git',
                         branch:'master'
                 }
             }

             stage('Build Docker Image') {
                 steps {
                     script {
                         dockerImage = docker.build("${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}")
                     }
                 }
             }
             stage('Login to AWS ECR') {
                 steps {
                     script {
                         // Login to ECR
                         sh '''
                         aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
                         '''
                     }
                 }
             }
             stage('Push Docker Image to ECR') {
                 steps {
                     script {
                         // Push the Docker image to ECR
                         dockerImage.push()
                     }
                 }
             }
             stage('Deploy or Run Docker Container') {
                         environment {
                             WORKSPACE = '/var/lib/jenkins/workspace/food-delivery-compose'

                         }
                         steps {
                             script {
                                 def composeFile = "/var/jenkins_home/workspace/food-delivery/docker-compose/docker-compose.yml"
                                 def serviceName = "food-delivery-rider-service"


                                 echo "Stopping and removing any existing containers"
                                 sh "docker rm -f ${serviceName} || true"

                                             echo "Cleaning up unused Docker resources"
                                             sh "docker system prune -af"
                                             sh "docker volume prune -f"

                                             echo "Pulling the latest image for service '${serviceName}'"
                                             sh "docker-compose -f ${composeFile} pull ${serviceName}"

                                             echo "Recreating the Docker container for service '${serviceName}'"
                                             sh "docker-compose -f ${composeFile} up -d --force-recreate --no-deps ${serviceName}"
                             }
                         }
                     }
         }
}
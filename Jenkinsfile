pipeline{
	agent any
	environment {
		SONAR_RUNNER_HOME = "C:\\Users\\bengy\\SonarQube\\sonar-scanner-cli-7.0.1.4817\\sonar-scanner-7.0.1.4817"
    }
	tools { 
        maven 'Maven' 
        jdk 'Java JDK 17'
    }
	stages{
		stage("clean"){
			steps{
				echo "Start Clean"
				bat "mvn clean"
			}
		}
		stage("test"){
			steps{
				echo "Start Test"
				bat "mvn test"
			}
		}
		stage("build"){
			steps{
				echo "Start Build"
				bat "mvn install -DskipTests"
			}
		}
		stage("Docker build"){
			steps{
				echo "Start Docker Build"
				bat "docker build -t bengyin/inventoryproject:latest ."
			}
		}
		stage("SonarQube analysis") {
			steps{
				echo "Start SonarQube Analysis"
				withSonarQubeEnv('SonarQube') {
					bat "${env.SONAR_RUNNER_HOME}\\bin\\sonar-scanner.bat -Dsonar.projectKey=InventoryProject -Dsonar.java.binaries=target/classes"
				}
			}
		}
/*
		stage('Debug Branch') {
			steps {
				script {
					echo "Current branch: ${env.GIT_BRANCH}"
					echo "Job name: ${env.JOB_NAME}"
				}
			}
		}
		stage("Deploy to Tomcat") {
			when {
				branch 'origin/main'
			}
			steps {
				script {
					def warFile = "target/*.war"
					def tomcatUser = "tomcat"
					def tomcatPassword = "password"
					def tomcatUrl = "http://localhost:8091"

					echo "Deploying WAR file to Tomcat..."
                    
					bat """
					curl -u ${tomcatUser}:${tomcatPassword} -T ${warFile} \
					${tomcatUrl}/manager/text/deploy?path=/InventoryProject&update=true
					"""
				}
			}
		} */
		stage('Deploy to Tomcat') {
			steps {
				script {
					// Find the WAR file
					def warFile = 'target/inventory-project.war'
					/*
					echo "WAR File: ${warFile}"
					*/
					
					// Tomcat Manager URL and credentials
					def tomcatUrl = 'http://localhost:8091/manager/text'
					def tomcatUser = 'tomcat'
					def tomcatPassword = 'password'

					// Deploy the WAR file using curl
					bat """
					curl -v -u ${tomcatUser}:${tomcatPassword} \
					-T ${warFile} \
					${tomcatUrl}/deploy?path=/InventoryProject
					"""
				}
			}
		}
	}
} 
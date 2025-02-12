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
		stage("Deploy to Tomcat") {
			when {
				branch 'main'
			}
			steps {
				script {
					def warFile = "**/*.war"
					def tomcatUser = "tomcat"
					def tomcatPassword = "password"
					def tomcatUrl = "http://localhost:8091"

					echo "Deploying WAR file to Tomcat..."
                    
					bat """
					curl -u ${tomcatUser}:${tomcatPassword} -T ${warFile} \
					${tomcatUrl}/manager/text/deploy?path=/InventoryProject/UserServlet/dashboard&update=true
					"""
				}
			}
		}
	}
} 


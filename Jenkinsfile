pipeline{
    agent any
        
    stages {
    
      stage("SCM"){
            steps{
               echo "Realizando el SCM"
             checkout scm
			}
       }
    
        stage("Build"){
            steps{
               echo "Realizando el Build"
               script{
               echo "****MAVEN BUILD****"
                 sh "mvn clean package -P dev -DskipTests "
                 
               }
            }
       }
      
       stage("Ejecución de test en paralelo"){
          parallel{
              
            stage ("Test unit") {
                steps{
                echo "Realizando test unit"
               	  script {
                   
                   echo "******MAVEN BUILD****"
                   sh "mvn clean package"
			  
                  }      
                } 
            post {
                
                	success{
                	    junit 'target/surefire-reports/**/*.xml'
                	}
                }
  
           }    
            stage("Construccion de reportes con  Jacoco") {
             steps {
                jacoco(
                    execPattern: 'target/**/*.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src',
                    exclusionPattern: '**/*Test*.class'
                )
                publishHTML([allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: false,
                    reportDir: 'target/jacoco-report/',
                    reportFiles: 'index.html',
                    reportName: 'Code Coverage Report',
                    reportTitles: 'Code Coverage Report'
                ])
                junit(
                    allowEmptyResults: true,
                    testResults: '**/TEST-*.xml'
                )
            }
          }
         
						

            stage ("Test integración/componente") {
                steps{
                echo "Realizando test componentes"
                }
            }

        /* stage("Quality Test"){
            environment {
             def scannerHome = tool 'SonarQubeScanner'
            }
            steps{
             script {
            echo "** SONARQUBE **"
            withSonarQubeEnv('SonarQubeServer') {
                sh "mvn  sonar:sonar -Dsonar.projectKey=com.telefonica.b2b.fidelity:b2b-fidelity-2 "
                }
            }
             }
            }*/
		stage('SonarQube analysis') {
            steps{
                script{
                    def scannerHome= tool 'SonarQubeScanner'
                    withSonarQubeEnv('SonarQubeServer') { // You can override the credential to be used
                      sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=com.telefonica.b2b.fidelity:b2b-fidelity -Dsonar.sources=. -Dsonar.java.binaries=."
                    }
                }
            }
        }  
  
             stage ("Security Scan") {
                steps{
                echo "Realizando test"
                }
            } 
            
           }
           
       }
        
      /*  stage("Artefact o Imageness"){
            steps{
                echo "Realizando el  Artifact en Artifactory o Docker"
            }
        }
          stage("Deploy Dev" ){
            steps{
                echo "Realizando Deploy en el ambiente desarrollo"
            }
        }*/
    
      }
    } 
 

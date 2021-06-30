pipeline{
    agent{
        node{
            label 'nodos'
        }
    }
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
                 bat "mvn clean package -P dev -DskipTests "
                 
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
                   bat "mvn clean package -P dev"
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

         stage("Quality Test"){
            environment {
             def scannerHome = tool 'SonarQubeScanner'
            }
            steps{
             script {
            echo "** SONARQUBE **"
            withSonarQubeEnv('SonarQube') {
                bat "mvn clean verify sonar:sonar -Dsonar.projectKey=com.telefonica.b2b.fidelity:b2b-fidelity -Dsonar.login=3fa08acd9f227f20f9fbab9f62ff60444d83b5dc"
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
 

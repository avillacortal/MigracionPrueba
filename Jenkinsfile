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
                 bat "mvn clean package -P dev -DskipTests"
                 
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
                   bat "mvn package"
                  }      
                } 
                post {
                
                	success{
                	    junit 'target/surefire-reports/**/*.xml'
                	}
                }
  
          }    
        
          stage('Build Reports'){
           
                        steps {
                             jacoco(
                             	 execPattern: 'target/**/*.exec',
                             	 classPattern: 'target/classes',
                             	sourcePattern: 'src',
                             	 exclusionPattern: '**/*Test*.class'
                          
                             )
                             
                           publishHTML (target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'coverage',
                            reportFiles: 'index.html',
                            reportName: "RCov Report"
                            ]) 
                           junit(
                           
                           allowEmptyResults: true,
                           testResults: '**TEST/-*.xml'
                           
                           )
                        
                  }
						
						
			}

            stage ("Test integración/componente") {
                steps{
                echo "Realizando test componentes"
                }
            } 
            stage ("Code  quality") {
                steps{
                echo "Realizando test UI"
                }
            }
            
            
             
            stage ("Security Scan") {
                steps{
                echo "Realizando test"
                }
            } 
            
           }
           
       }
        
        stage("Artefact o Imagenes"){
            steps{
                echo "Realizando el  Artifact en Artifactory o Docker"
            }
        }
          stage("Deploy Dev" ){
            steps{
                echo "Realizando Deploy en el ambiente desarrollo"
            }
        }
    
      }
    }  

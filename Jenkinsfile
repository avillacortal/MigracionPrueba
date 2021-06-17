pipeline{
    agent{
        node{
            label 'nodos'
        }
    }
    stages {
        stage("Build"){
            steps{
               echo "Realizando el Build"
            }
        }
         
       stage("Ejecución de test en paralelo"){
          parallel{
              
            stage ("Test unit") {
                steps{
                echo "Realizando test unit"
                
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
    

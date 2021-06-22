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
          
           checkout SCM

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
  

#!/usr/bin/env groovy

pipeline {

    agent any
   
    options { disableConcurrentBuilds() }
   
   
    stages {
    
    	 stage('Permissions') {
            steps {
                sh 'chmod 775 *'
            }
        }
        
        stage('Compile stage') {
            steps {
                sh "mvn clean compile" 
        	}
   		 }

         stage('testing ') {
             steps {
                sh "mvn test"
     		   }
    		}
    		
		stage('install') {
             steps {
                sh "mvn install"
     		   }
    		}
		
		stage('Update Docker UAT image') {
           
            steps {
                sh '''
					docker login -u "mkefi" -p "09152798Km"
                    docker build --no-cache -t maktoubbackend:latest .
                    docker tag maktoubbackend:latest mkefi/maktoub:latest
                    docker push mkefi/maktoub:latest
					docker rmi maktoubbackend:latest
                '''
       			 }
        	}
        	
        stage('Update UAT container') {
           
            steps {
                sh '''
				docker login -u "mkefi" -p "09152798Km"
                    docker pull mkefi/maktoub:latest 
                    docker stop maktoubbackend
                    docker rm maktoubbackend                   
                    docker run -p 9300:9300 --name maktoubbackend --network dbconnexion --restart=always -t -d mkefi/maktoub:latest                                      
                '''
            }
        }
		
	}
  }
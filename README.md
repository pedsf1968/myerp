#ERP test project
##Travis-ci
[![Build Status](https://travis-ci.org/pedsf1968/myerp.svg?branch=master)](https://travis-ci.org/pedsf1968/myerp)

##SonarCloud
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pedsf1968_myerp&metric=alert_status)](https://sonarcloud.io/dashboard?id=pedsf1968_myerp)

#Objectif
Implement unitary and integration tests for code improvement and debbuging.
This project use Travis-Ci for continuous integration pipeline.
The result can be see in the link below :
https://travis-ci.org/github/pedsf1968/myerp

The code analyse is made by Sonar.
The result can be see in the link below :
https://sonarcloud.io/dashboard?id=pedsf1968_myerp
 


#Profiles
##Profile production
Production profile use a PostgreSQL Docker database. 
Launch containers with docker-compose command below :
docker-compose up -d 

###Launch test
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P production

###Launch sonar analyse
Using Maven command verify with the tag -P to specify the profile and sonar:sonar to start sonar analyse. 
mvn clean verify sonar:sonar  -P production


##Profile test-consumer
Use in memory H2 database

###Launch test 
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P test-consumer

##Profile test-business
Use in memory H2 database

##sonar
Use in memory H2 database

###Launch test
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P sonar

###Launch sonar analyse
Using Maven command verify with the tag -P to specify the profile and sonar:sonar to start sonar analyse. 
mvn clean verify sonar:sonar  -P sonar


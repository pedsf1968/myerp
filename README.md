#ERP test project
##Travis-ci
[![Build Status](https://travis-ci.org/pedsf1968/myerp.svg?branch=master)](https://travis-ci.org/pedsf1968/myerp)

##SonarCloud
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pedsf1968_myerp&metric=alert_status)](https://sonarcloud.io/dashboard?id=pedsf1968_myerp)


#Profiles
##production
Use PostgreSQL Docker database

###Launch test with 
mvn clean test -P production

###Launch sonar analyse with
mvn clean verify sonar:sonar  -P production


##test-consumer
Use in memory H2 database

###Launch test with 
mvn clean test -P test-consumer

##test-business
Use in memory H2 database

##sonar
Use in memory H2 database

###Launch test with 
mvn clean test -P sonar

###Launch sonar analyse with
mvn clean verify sonar:sonar  -P sonar

#TEST
## with production PostgreSQL database
mvn clean 
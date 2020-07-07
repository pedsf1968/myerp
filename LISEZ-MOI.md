# ERP test project
## GitHub repository
https://github.com/pedsf1968/myerp.git
Branche MASTER
Répertoire racine du projet /src

## Travis-ci 
https://travis-ci.org/github/pedsf1968/myerp

## Codecov
https://codecov.io/gh/pedsf1968/myerp

## SonarCloud 
https://sonarcloud.io/dashboard?id=pedsf1968_myerp

# Lancement des test
## Préalable
Docker et docker-compose doivent être installés et en fonction.
Aller dans le répertoire racine et lancer la commande ci-dessous pour lancer la base de données :
docker-compose up -d

aller dans le répertoire /src pour lancer les test

## Tests du module test-consumer avec l'option -pl myerp-consumer
### environnement de test 
mvn clean test -pl myerp-consumer -P test-consumer

### environnement de production
mvn clean test -pl myerp-consumer -P production


## Tests du module test-business avec l'option -pl myerp-business
### environnement de test 
mvn clean test -pl myerp-business -P test-business

### environnement de production
mvn clean test -pl myerp-business -P production


## Lancer tous les tests
### environnement de test 
mvn clean test -P sonar

### environnement de production
mvn clean test -P production


## Analyse avec SonarQube
Le résultat de l'analyse est sur :
- https://sonarcloud.io/dashboard?id=pedsf1968_myerp

### environnement de test 
mvn clean verify sonar:sonar

### environnement de production
mvn clean verify sonar:sonar -P production


## Intégration continue
### Travis-ci
Lancement des tests, de la couverture avec cobertura et de l'analyse avec jacoco. 
Les tests sont lancé à chaque push sur GitHub.
Fichier de configuration :
- /src/.travis
Résultat sur globaux sur travis :
- https://travis-ci.org/github/pedsf1968/myerp
Résultat de cobertura sur codecov :
- https://codecov.io/gh/pedsf1968/myerp
Résultat de jacoco sur sonarcloud :
https://sonarcloud.io/dashboard?id=pedsf1968_myerp

### Travis-ci
Lancement de l'analyse avec jacoco. 
Les tests sont lancé à manuellement depuis le container Jenkins.
- Lancer docker-compose up -d depuis le répertoire racine
- Ouvrir un browser sur http:\\localhost:5000
- login : jenkins
- mot de passe : admin
- lancer le job : Myerp
Fichier de configuration :
- ./Jenkins configuration.pdf
- /docker/dev/jenkins
Résultat de jacoco sur sonarcloud :
https://sonarcloud.io/dashboard?id=pedsf1968_myerp




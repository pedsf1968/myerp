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

# Maven lifecycle avec base de données H2 en mémoire
Lancer à partir du répertoire racine

## Tests le profile sonar est activé par défaut
mvn clean test

## Analyse avec SonarQube
mvn clean verify sonar:sonar

Le résultat est consultable sur les liens ci-dessus.

## Tests du module test-consumer
mvn clean test -pl myerp-consumer -P test-consumer

## Tests du module test-business
mvn clean test -pl myerp-business -P test-business


# Maven lifecycle avec base de données PostgreSQL Docker
## Préalable
Docker et docker-compose doivent être installés et en fonction.
Aller dans le répertoire racine et lancer la commande ci-dessous pour lancer la base de données :
docker-compose up -d

Se rendre dans le répertoire racine pour lancer les autres commandes

## Tests
mvn clean test -P production

## Analyse avec SonarQube
mvn clean verify sonar:sonar -P production

Le résultat est consultable sur les liens ci-dessus.

## Tests du module test-consumer
mvn clean test -pl myerp-consumer -P production

## Tests du module test-business
mvn clean test -pl myerp-business -P production

# Docker Jenkins 
## Accès
Le docker-compose doit être lancé pour y avoir accès. Ouvrir un navigateur à l'adresse http:\\localhost:5000
L'identifiant est jenkins et le mot de passe admin.
Le job est Myerp.
La configuration est disponible dans la documentation /doc.






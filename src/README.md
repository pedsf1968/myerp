# ERP test project
## Travis-ci https://travis-ci.org/github/pedsf1968/myerp
[![Build Status](https://travis-ci.org/pedsf1968/myerp.svg?branch=master)](https://travis-ci.org/pedsf1968/myerp)

## SonarCloud https://sonarcloud.io/dashboard?id=pedsf1968_myerp

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pedsf1968_myerp&metric=alert_status)](https://sonarcloud.io/dashboard?id=pedsf1968_myerp)

# Object
Implement unitary and integration tests for code improvement and debbuging.
This project use Travis-Ci for continuous integration pipeline.
The result can be see in the link below :
https://travis-ci.org/github/pedsf1968/myerp

The code analyse is made by Sonar.
The result can be see in the link below :
https://sonarcloud.io/dashboard?id=pedsf1968_myerp

***
# Run test
## Test environment
- Go to myerp/src directory
- Execute : mvn clean verify sonar:sonar
- Open browser on : https://sonarcloud.io/dashboard?id=pedsf1968_myerp

## Production environment
- Go to myerp/docker/dev directory
- Execute : docker-compose up -d


***
# Bugs found 
## Package com.dummy.myerp.model.bean.comptabilite
### Class EcritureComptable
#### getTotalCredit()
Method was returning getDebit() instead of getCredit().
ORIGINAL
```java
vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
```

CORRECTION
```java
vRetour = vRetour.add(vLigneEcritureComptable.getCredit()); 
```
***
#### isEquilibree()
Method was using equals for BigDecimal comparison instead of compareTo().
ORIGINAL
```java
boolean vRetour = this.getTotalDebit().equals(getTotalCredit());
return vRetour;
```

CORRECTION
```java
return this.getTotalDebit().compareTo(getTotalCredit()) == 0;
```
***
## Package com.dummy.myerp.consumer.db.helper
### Class ResultSetHelper
#### getDate()
Method return the current date instead the ResultSet date.
ORIGINAL
```java 
public static Date getDate(ResultSet pRS, String pColName) throws SQLException {
    Date vDate = pRS.getDate(pColName);
    if (vDate != null) {
        vDate = DateUtils.truncate(vDate, Calendar.DATE);
    }
    return vDate;
}
```
        
CORRECTION
```java 
public static Date getDate(ResultSet pRS, String pColName) throws SQLException {
    if (pRS==null || pColName==null) {
        throw new SQLException(ERROR_MESSAGE);
    }     
    return pRS.getDate(pColName);
}
``` 
***
## Package com.dummy.myerp.consumer
### sqlContext.xml
#### SQLinsertListLigneEcritureComptable
Miss comma between debit and credit in column declaration.
ORIGINAL
```xml
<property name="SQLinsertListLigneEcritureComptable">
    <value>
        INSERT INTO myerp.ligne_ecriture_comptable (
            ecriture_id, ligne_id, compte_comptable_numero, libelle, debit
            credit
        ) VALUES (
            :ecriture_id, :ligne_id, :compte_comptable_numero, :libelle, :debit,
            :credit
        )
    </value>
</property>
```
CORRECTION
```xml
<property name="sqlInsertListLigneEcritureComptable">
    <value>
        INSERT INTO myerp.ligne_ecriture_comptable (
            ecriture_id, ligne_id, compte_comptable_numero, libelle, debit,
            credit
        ) VALUES (
            :ecriture_id, :ligne_id, :compte_comptable_numero, :libelle, :debit,
            :credit
        )
    </value>
</property>
```
***
## Package com.dummy.myerp.business.impl.manager
### Class ComptabiliteManagerImpl
#### updateEcritureComptable()
No EcritureComptable rules checking before update.
ORIGINAL
```java
public void updateEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
    TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
    try {
        getDaoProxy().getComptabiliteDao().updateEcritureComptable(pEcritureComptable);
        getTransactionManager().commitMyERP(vTS);
        vTS = null;
    } finally {
        getTransactionManager().rollbackMyERP(vTS);
    }
}
```

CORRECTION
```java
public void updateEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
    this.checkEcritureComptable(pEcritureComptable);
    TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
    try {
        getDaoProxy().getComptabiliteDao().updateEcritureComptable(pEcritureComptable);
        getTransactionManager().commitMyERP(vTS);
        vTS = null;
    } finally {
        getTransactionManager().rollbackMyERP(vTS);
    }
}
```
***
# Modifications
- Change Junit4 to Junit5 with AssertJ and Mockito.
- Use memory H2 database for testing.
- Replace java.util.Date with java.sql.Date
- add profiles production and sonar (see Profile section)

## TODO
### Package package com.dummy.myerp.business.impl.manager
#### Class ComptabiliteManagerImpl
- implement addReference method
- implement missing rules test and separate each rule methods.
- implement general checking method that call the others.
```java
  public void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
        this.checkEcritureComptableUnit(pEcritureComptable);
        this.checkEcritureComptableRG2(pEcritureComptable);
        this.checkEcritureComptableRG3(pEcritureComptable);
        this.checkEcritureComptableRG5(pEcritureComptable);
        this.checkEcritureComptableRG6(pEcritureComptable);
        this.checkEcritureComptableRG7(pEcritureComptable);
    }
```

## test-consumer
Implement test-consumer package as test-business to access database with Class :
- ConsumerTestCase
- SpringRegistry
- TestInitSpring 

## Rowmapper
### Package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite
Add Row Mapper SequenceEcritureComptableRM for table SequenceEcritureComptable.

## DBN
### Package com.dummy.myerp.consumer.dao.impl.db
Add class DBN (DataBase Name) for database culumn name centralisation.

## datasource MAP
### applicationContext.xml
Add TEST datasource for memory database in AbstractDbConsumer MAP.
````xml
<bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
    <property name="targetClass" value="com.dummy.myerp.consumer.db.AbstractDbConsumer"/>
    <property name="targetMethod" value="configure"/>
    <property name="arguments">
        <map>
            <entry value-ref="dataSourceMYERP">
                <key>
                    <value type="com.dummy.myerp.consumer.db.DataSourcesEnum">MYERP</value>
                </key>
            </entry>
            <entry value-ref="dataSourceTEST">
                <key>
                    <value type="com.dummy.myerp.consumer.db.DataSourcesEnum">TEST</value>
                </key>
            </entry>
        </map>
    </property>
</bean>
````
Separate datasource in :
- dataSourceContextPostgresql.xml
- dataSourceContextH2.xml

Add SQL script for H2 schema and data :
- db-shema.sql
- db-data.sql

Set database type property in pom parent
```xml
<profile>
   <id>production</id>
   <properties>
      <myerp.database>MYERP</myerp.database>
   </properties>
</profile>
<profile>
   <id>test-business</id>
   <properties>
      <myerp.database>TEST</myerp.database>
   </properties>
</profile>
<profile>
   <id>test-consumer</id>
   <properties>
      <myerp.database>TEST</myerp.database>
   </properties>
</profile>
<profile>
   <id>sonar</id>
   <properties>
      <spring.profiles.active>sonar</spring.profiles.active>
      <myerp.database>TEST</myerp.database>
   </properties>
</profile>
```

Set database type property in pom child
```xml
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <configuration>
      <systemPropertyVariables>
         <databaseType>${myerp.database}</databaseType>
      </systemPropertyVariables>
   </configuration>
</plugin>
```

Change application context according to database type.
```java
    private static final String CONTEXT_PROD_APPLI_LOCATION
          = "classpath:/com/dummy/myerp/consumer/bootstrapContext.xml";
    private static final String CONTEXT_TEST_APPLI_LOCATION
          = "classpath:/com/dummy/myerp/testconsumer/consumer/bootstrapContext.xml";
    
    private ApplicationContext contextAppli;

    private SpringRegistry() {
        super();
        DataSourcesEnum database = DataSourcesEnum.valueOf(System.getProperty("databaseType"));

        SpringRegistry.LOGGER.debug("[DEBUT] SpringRegistry() - Initialisation du contexte Spring");
        if (database.equals(DataSourcesEnum.MYERP)) {
            this.contextAppli = new ClassPathXmlApplicationContext(SpringRegistry.CONTEXT_PROD_APPLI_LOCATION);
        } else {
            this.contextAppli = new ClassPathXmlApplicationContext(SpringRegistry.CONTEXT_TEST_APPLI_LOCATION);
        }

        SpringRegistry.LOGGER.debug("[FIN] SpringRegistry() - Initialisation du contexte Spring : {}", database);
    }
```

In AbstractDbConsumer change request according to the database type to find next sequence number
```java
public abstract class AbstractDbConsumer {
    private static final String POSTGRES_REQUEST = "SELECT last_value FROM %s";
    private static final String H2_REQUEST = "SELECT %s.NEXTVAL-1 FROM DUAL";

  protected <T> T queryGetSequenceValue(DataSourcesEnum pDataSourcesId,
                                                   String pSeqName, Class<T> pSeqValueClass) {
        String vSeqSQL;
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource(pDataSourcesId));

        DataSourcesEnum database = DataSourcesEnum.valueOf(System.getProperty("databaseType"));

        if(database.equals(DataSourcesEnum.MYERP)) {
            vSeqSQL = String.format(POSTGRES_REQUEST,  pSeqName);
        } else {
            vSeqSQL = String.format(H2_REQUEST,  pSeqName);
        }

        return vJdbcTemplate.queryForObject(vSeqSQL, pSeqValueClass);
    }
}
```

# Profiles
A production profile was added to separate production PostgreSQL database from test environment.
A sonar profile was added also for testing everything and launch Sonar analyse with sonar:sonar parameter in command line.

## Profile production
Production profile use a PostgreSQL Docker database. 
Launch containers with docker-compose command below :
docker-compose up -d 

### Launch test
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P production

### Launch sonar analyse
Using Maven command verify with the tag -P to specify the profile and sonar:sonar to start sonar analyse. 
mvn clean verify sonar:sonar  -P production


## Profile test-consumer
Use in memory H2 database

### Launch test 
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P test-consumer

## Profile test-business
Use in memory H2 database

## Profile sonar
Use in memory H2 database and run all tests.

### Launch test
Using Maven command test with the tag -P to specify the profile. 
mvn clean test -P sonar

### Launch sonar analyse
Using Maven command verify with the tag -P to specify the profile and sonar:sonar to start sonar analyse. 
mvn clean verify sonar:sonar  -P sonar


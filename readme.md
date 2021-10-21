# Pets service

* [Description](#description)
* [Resource components](#Resource-components)
* [How to run on SAP Cloud Foundry](#How-to-run-on-SAP-Cloud-Foundry)
* [How to connect to SAP postgreSQL](#How-to-connect-to-SAP-postgreSQL)
* [How to change log style to JSON](#How-to-change-log-style-to-JSON)
* [How to use debug cloud base application from Intellij Idea](#How-to-use-debug-cloud-base-application-from-Intellij-Idea)
* [](#How-to-use-debug-cloud-base-application-from-Intellij-Idea)


## Description

Restful service that provides management of pets and their owners.
It can be run on SAP Cloud Foundry.

## Resource components

The main resource components supports Pet service:

- Owner
- Pet
  - Dog
  - Cat

### Owner

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `/owner/{id}`      |   `GET`   | get owner by id |
| `/owner`           |   `POST`  | save owner |
| `/owner/{id}`      |   `PUT`   | update owner by id |
| `/owner/{id}`      |  `DELETE` | delete owner by id |
| `/owner/kill{id}`  |   `PUT`   | kill owner by id |
| `/owner/exchange`  |   `POST`  | exchange pets between owners |

Example body for **save** and **update** owner:
 ```
{
  "firstName": "Alexandr",
  "lastName": "Dinaec",
  "passportNumber": "HB2911102",
  "phoneNumber": "+375291110033"
}
 ```
Example body for **exchange** pets between owners:
 ```
{
    "firstOwnerId": 1,
    "secondOwnerId": 2,
    "firstOwnerPetId": 1,
    "secondOwnerPetId": 5
}
 ```

### Pet

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/{id}`      |   `GET`   | get pet by id |
| `pet/all`       |   `GET`   | get all pets |
| `pet/{id}`      |  `DELETE` | delete pet by id |

### Dog

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/dog/{id}`      |   `GET`   | get dog by id |
| `pet/dog`           |   `POST`  | save dog |
| `pet/dog/{id}`      |   `PUT`   | update dog by id |
| `pet/dog/{id}`      |  `DELETE` | delete dog by id |

Example body for **save** and **update** dog:
 ```
{
  "ownerId": "1",
  "isTrained": "true",
  "age": "2",
  "breed": "Jone",
  "name": "Tuz"
}
 ```

### Cat

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/cat/{id}`      |   `GET`   | get cat by id |
| `pet/cat`           |   `POST`  | save cat |
| `pet/cat/{id}`      |   `PUT`   | update cat by id |
| `pet/cat/{id}`      |  `DELETE` | delete cat by id |

Example body for **save** and **update** cat:
 ```
{
  "ownerId": "1",
  "hasWool": "true",
  "age": "2",
  "breed": "Jone",
  "name": "Tuz"
}
 ```

All requests available in [Postman](https://www.getpostman.com/collections/f9fbe3cc98ad34cdc009)      
        
## How to run on SAP Cloud Foundry

For deploy application to the cloud you need manifest.yaml. My manifest.yaml:

```
applications:
  - name: spring-boot-pets
    instances: 1
    memory: 1G
    path: target/leverx-spring-boot-app-0.0.1-SNAPSHOT.jar
    buildpack: https://github.com/cloudfoundry/java-buildpack.git#v4.17.1
    services: 
        - my-logs
        - postgre
```

   1. To start you must register on [SAP](https://www.sap.com/).

   2. Download and install [Cloud Foundry CLI](https://github.com/cloudfoundry/cli)

   3. Open your trial [account](https://cockpit.hanatrial.ondemand.com/trial/#/home/trial), choose Europe(Frankfurt) region,
   then choose trial subaccount and copy API Endpoint.
   
   4. Open idea terminal and set api endpoint:

      ```
         cf api {copied API Endpoint}
      ```
   
   5. Then you must enter your email and password for log into your account:

      ```
         cf login {your email}
      ```
   
      ```
         cf password {password}
      ```

   6. Package project with cloud profile:

      ```
          mvn clean package -Pcloud
      ```
      *If tests don't pass set @ActiveProfiles("prod")*
  
   7. Create necessary services (PostgreSQL with name **posgre** and Application logger with name 
   **my-logs**) on SAP Cloud (name of this service may differ:

      ```
         cf create-service postgresql-db trial postgre
      ```

      ```
         cf create-service application-logs trial my-logs 
      ```
      
        *If postgreSQL doesn't create you must add roles [here](https://cockpit.hanatrial.ondemand.com/trial/#/globalaccount/4c78b525-9f04-4c8b-821e-7017623a5dc3/subaccount/ba456e73-07f0-4170-aa54-d72490a7b420/usersOverview&//userRoleCollections/0/?layout=TwoColumnsBeginExpanded).
   8. Now you can push application on SAP Cloud Foundry.
   
      ```
         cf push
      ```
   
   9. To get link on your service open dev space.

## How to connect to SAP postgreSQL

1. Open a second terminal and enable SSH for application:

   ```
      cf enable-ssh {application name}
   ```

2. Restart application:

   ```
      cf restart {application name}
   ```

3. Create SSH connection (don't close the terminal to open the connection):

   ```
      cf ssh -L localhost:{local port}:{postgreSQL hostname}:{postgreSQL port} {application name} -N
   ```
   
4. Now you can use credential to connect
   
   ```
      url: jdbc:postgresql://localhost:{local port}/{database name from SAP postgreSQL credential}
      username: {username from SAP postgreSQL credential}
      password: {password from SAP postgreSQL credential}
   ```
   
## How to change log style to JSON

   1. *logback-spring.xml* must be located in ```src/main/resources/logback-spring.xml```
   2. File content:
      ```
         <?xml version="1.0" encoding="UTF-8"?>
         <configuration>
               <springProfile name="!cloud">
                  <include resource="org/springframework/boot/logging/logback/base.xml"/>
                  <root level="INFO"/>
                  <logger name="org.springframework.web" level="INFO"/>
               </springProfile>
               <springProfile name="cloud">
                  <appender name="STDOUT-JSON" class="ch.qos.logback.core.ConsoleAppender">
                     <encoder class="com.sap.hcp.cf.logback.encoder.JsonEncoder"/>
                  </appender>
                  <root level="INFO">
                     <appender-ref ref="STDOUT-JSON"/>
                  </root>
                  <logger name="com.sap.cloud.sdk" level="INFO"/>
                  <logger name="package.to.log" level="DEBUG"/>
               </springProfile>
         </configuration>
      ```
   3. Add dependency to a pom.xml: 
      ```
         <dependency>
                    <groupId>com.sap.hcp.cf.logging</groupId>
                    <artifactId>cf-java-logging-support-logback</artifactId>
                    <version>3.4.0</version>
         </dependency>
      ```

## How to use debug cloud base application from Intellij Idea 

   1. Write the following commands in terminal: 
      ```
        cf allow-space-ssh dev
      
        cf enable-ssh {application name}
      
        cf restage spring-boot-pets

      ```
   2. Open a second terminal and write: 
      
      ```
      cf ssh -N -T -L 8000:localhost:8000 {application name}
      ```
   3. Add the following line to the manifest.yaml :
      
      ```
      env: 
        JBP_CONFIG_DEBUG: '{enabled: true}'
      ```
   4. Push alt+shift+f9 (intellij idea) and create new remote JVM debug configuration with port 8000
   5. Start debug

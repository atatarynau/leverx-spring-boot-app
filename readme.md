# Pets service

* [Description](#description)
* [Resource components](#Resource-components)
* [How to run on SAP Cloud Foundry](#How-to-run-on-SAP-Cloud-Foundry)


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
    path: target/leverx-spring-boot-app-0.0.1-SNAPSHOT.war
    buildpack: https://github.com/cloudfoundry/java-buildpack.git#v4.17.1
    services: 
        - my-logs
        - postgre
```

1. To start you must register on [SAP](https://www.sap.com/).

2. Download and install [Cloud Foundry CLI](https://github.com/cloudfoundry/cli)

3. Open your trial [account](https://cockpit.hanatrial.ondemand.com/trial/#/home/trial), choose Europe(Frankfurt) region,
   then choose trial subaccount and copy API Endpoint.
   
3. Open idea terminal and set api endpoint:

   ```
      cf api {copied API Endpoint}
   ```
   
4. Then you must enter your email and password for log into your account:

   ```
      cf login {your email}
   ```
   
   ```
      cf password {password}
   ```

6. Package project with cloud profile:

    ```
       mvn package -Pcloud -Dmaven.test.skip=true
    ```

6. Create necessary services (PostgreSQL with name **posgre** and Application logger with name 
   **my-logs**) on SAP Cloud:

    ```
        cf create-service postgresql-db trial postgre
    ```

    ```
        cf create-service application-logs trial my-logs 
    ```
   
6. Now you can push application on SAP Cloud Foundry.
   
   ```
      cf push
   ```
   
7. To get link on your service open dev space.
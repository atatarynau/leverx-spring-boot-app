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
| `/owner/{id}`      |   `GET`   | return information about owner by id |
| `/owner`           |   `POST`  | save owner |
| `/owner/{id}`      |   `PUT`   | update owner by id |
| `/owner/{id}`      |  `DELETE` | delete owner by id |
| `/owner/kill{id}`  |   `PUT`   | kill owner by id |
| `/owner/exchange`  |   `POST`  | kill owner by id |

### Pet

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/{id}`      |   `GET`   | return information about pet by id |
| `pet/all`       |   `GET`   | get all pets |
| `pet/{id}`      |  `DELETE` | delete pet by id |

### Dog

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/dog/{id}`      |   `GET`   | return information about dog by id |
| `pet/dog`           |   `POST`  | save dog |
| `pet/dog/{id}`      |   `PUT`   | update dog by id |
| `pet/dog/{id}`      |  `DELETE` | delete dog by id |

### Cat

| resource | method | description |
|:-----------------|:------------------|:------------------|
| `pet/cat/{id}`      |   `GET`   | return information about cat by id |
| `pet/cat`           |   `POST`  | save cat |
| `pet/cat/{id}`      |   `PUT`   | update cat by id |
| `pet/cat/{id}`      |  `DELETE` | delete cat by id |

All requests available in [Postman](https://www.getpostman.com/collections/f9fbe3cc98ad34cdc009)

## How to run on SAP Cloud Foundry

1. To start you must register on [SAP](https://www.sap.com/).

2. Download and install [Cloud Foundry CLI](https://github.com/cloudfoundry/cli)

3. Open your trial [account](https://cockpit.hanatrial.ondemand.com/trial/#/home/trial), choose Europe(Frankfurt) region,
   then choose trial subaccount and copy API Endpoint.
   
3. Open idea terminal and write:

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
   
5. Select space by entering number that you can see in the terminal.

6. Package project in war file.
   
6. Now you can push application on SAP Cloud Foundry.
   
   ```
      cf push
   ```
   
7. To get link on your service open dev space.




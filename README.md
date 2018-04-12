# transactions_api
=========================================================

Java version: 1.8
Build tool : Maven
Packaging : jar

Spring components:
Boot, Rest

Please, follow the following instructions to build, test & run the application 

Go to project folder,

Build:
$ mvn clean install

Test: 
$ mvn clean test

Run:
$ mvn spring-boot:run


Endpoints
===============================================
1. /api/v1/transaction/create

Method: POST

Body:
{
"amount": 12.3,
"timestamp": 1478192204000
}

Response: Empty body with either 201 or 204


2. /api/v1/transaction/statistics

Method: GET

Response:
{
"sum": 1000,
"avg": 100,
"max": 200,
"min": 50,
"count": 10
}

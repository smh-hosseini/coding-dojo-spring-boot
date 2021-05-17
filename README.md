Spring Boot Coding Dojo
---
Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database. 
In this implementation, a few problems in the code have been fixed to making it a production ready product.

### Add Dependencies

Dependencies added to the project .
  - Java: 11
  - Spring Boot
      - Data JPA
      - Web
      - Test
  - Docker   
  - Swagger
  - Feign Client
  - Mapstruct 
  - Wiremock (For mocking remote server call in integration tests) 
  - Hibernate
  - Lombok
  - Flyway (database migration)
  - H2 (integration tests)


### Build the application

#### Prerequisites

To be able to use the application, you should have PostgreSQL installed on your machine or run the application with dev profile.
In case of using PostgreSQL you can change the correspondent configs in the application.yml  
Set Environment variable APP_ID to the value tha you can find in application.yml 

#### Build and Run

Use the following command to build the application:

>mvn clean install

To run the application:
>mvn spring-boot:run

If you want to use docker first call

>mvn clean package

To create the artifact Then build the image

> docker build --tag=coding-dojo-server:latest

Now you can run it 

> docker run -p8080:8080 coding-dojo-server:latest

In this case you need to have PostgreSQL installed and running docker compose file

> docker-compose -f docker/docker-compose.yaml

Now you can call the API and fetch the data

Http://localhost:8080/api/weather?city=London
 
### Footnote

It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.
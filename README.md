# Redis Cache and Liquibase Example

This repository is about using Redis cache on Spring Boot. There are separated example of using Redis, first of all i'm  using @Cacheable, @CachePut. The other one is using Redis template.

## Features

- ParameterController using Cacheable
- On WeatherController, there is an example about how to cache web service response
- On Test package, using RedisTemplate and how to access redis data type with Spring Boot
- I'm using liquibase with yaml especially for maintain database schema and build script
- Database is using Postgresql

## How to run
`git clone https://github.com/farhanatsani/spring-boot-redis-cache`

On /src/main/resources there is an application.properties file
Please adjust to your particular postgresql database.
`spring.datasource.url=jdbc:postgresql://localhost:5432/exampledb`
`spring.datasource.username=user`
`spring.datasource.password=password`
`spring.datasource.driver-class-name=org.postgresql.Driver`

`mvn clean install`
Server will run on port 8085
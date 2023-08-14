# IDEA EDU Course

Implemented in the <b>Java Backend Developer</b> Track of hyperskill.org JetBrain Academy.  

Project goal is to do another Spring Boot JPA project - here with Command Line and Runner.

## Technology / External Libraries

- Java 20
- Spring Boot / JPA
- Lombok
- Slf4j
- Tests with Junit-Jupiter and Mockito
- Gradle 8.2.1

## Program description

The application will provide a command line CRUD application that manages a car-sharing service allowing companies to
rent out their cars and find customers.

## Project completion

[//]: # (Project was completed on 14.05.23.)

## Repository Contents

Sources for all project tasks (4 stages) with tests and configurations.

## Progress

10.08.23 Project started. Setup of build and repo with gradle on Kotlin basis.

10.08.23 Stage 1 completed. Just the creation of an H2-file database with a company table. Modelled with JPA entity and 
JPARepository and have Spring JPA to the DDL-creation. Take database-filename from program arguments and programatically
set the `spring.datasource.url` property before running the `SpringApplication`.

14.08.23 Stage 2 completed. Have command line menus provided as Spring beans (`@Controller`) and have a stdin-scanner 
provided as bean. The CommandLineRunner uses a service to connect (query / insert) with the database vie the JPA-repository.
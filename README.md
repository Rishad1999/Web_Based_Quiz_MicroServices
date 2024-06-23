# Quiz Application Microservices

This repository contains the source code for a microservice-based quiz application. The project is composed of multiple services including a `Question Service`, `Quiz Service`, and an `API Gateway`.

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
  - [Question Service](#question-service)
  - [Quiz Service](#quiz-service)
  - [API Gateway](#api-gateway)
- [Contributing](#contributing)
- [License](#license)

## Overview
The quiz application consists of the following microservices:
- **Question Service:** Manages quiz questions.
- **Quiz Service:** Manages quiz creation and submission.
- **API Gateway:** Routes requests to the appropriate microservices.

## Architecture
The architecture of this application leverages Spring Boot for microservices and Spring Cloud for service discovery and routing.

![Architecture Diagram](path_to_architecture_diagram.png)

## Prerequisites
- Java 20
- Maven
- Spring Boot
- Spring Cloud
- Eureka Server (for service discovery)
- Postman (for API testing)

## Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/quiz-application-microservices.git
    cd quiz-application-microservices
    ```

2. Build the projects using Maven:
    ```bash
    mvn clean install
    ```

## Running the Application
1. Start the Eureka Server (Service Discovery):
    ```bash
    cd eureka-server
    mvn spring-boot:run
    ```

2. Start the Question Service:
    ```bash
    cd question-service
    mvn spring-boot:run
    ```

3. Start the Quiz Service:
    ```bash
    cd quiz-service
    mvn spring-boot:run
    ```

4. Start the API Gateway:
    ```bash
    cd api-gateway
    mvn spring-boot:run
    ```

## Endpoints
### Question Service
- **Get all questions:**
  ```http
  GET /question/allQuestions
Get questions by category:
GET /question/category/{category}

Add a new question:
POST /question/add

Edit a question:
POST /question/edit/{id}

Delete a question:
DELETE /question/delete/{id}

Quiz Service
Create a quiz:
POST /quiz/create

Get quiz questions:
GET /quiz/get/{id}

Submit quiz:
POST /quiz/submit/{id}

API Gateway
The API Gateway routes requests to the appropriate microservices.

Route to Quiz Service:
http://localhost:8084/QUIZ-SERVICE/quiz/get/{id}

Route to Question Service:
http://localhost:8084/QUESTION-SERVICE/question/allQuestions

Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or new features.

# Async Data Fetching

## Description

Fetch asynchronous data from an API".
Your task is to write a script or app, which gathers data from two endpoints asynchronously, 
merges the responses and displays them in any way, for example as JSON response from an REST API.
For example you could use these two endpoints:
http://jsonplaceholder.typicode.com/users/1 to obtain a user's data
http://jsonplaceholder.typicode.com/posts?userId=1 to obtain all comments written by that user.

## Notes

### Functional requirements

* Read data from two APIs
* Build REST API
* Async fetching
* Read only
* No state, no database

### Non-Functional requirements

* Check for serverless technologies
* Ensure scalability

### Ideas

* Quarkus, can be extended to serverless but can also stay "simple" as a microservice.
* Maybe Kotlin, I am familiar with this and can code faster.
* Spring Boot might be an overkill for such a simple use case
* Check for reactive solutions over using Futures / Promises and dealing with thread pooling as a developer
* Consider using a rest client / library to prevent implementation overhead connecting to the suggested endpoints
* Possibly use a Stream to deliver even quicker data to the client
  Also beneficial when the backend APIs would support streaming.
  Also beneficial when the client (end-consumer?) has a slower internet connection

#### Implementation Level / Concept

##### Stage 1

* Microservice, One Repository
* REST API without streaming
* Try out and evaluate technologies
* Use reactive libraries for async data processing
* Generate OAS Spec

##### Stage 2

* Add JSON streaming endpoints

##### Stage 3

* Go functional / serverless approaches
* Deploy
* CI/CD
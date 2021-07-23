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


* [Integration Testing with a Local DynamoDB Instance](https://www.baeldung.com/dynamodb-local-integration-tests)

---
## Introduction
* goal
  * multiple ways for a local DynamoDB | our integration tests, to
    * configure it
    * start it
    * stop it
* check [DynamoDB in a Spring Boot Application Using Spring Data](https://www.baeldung.com/spring-data-dynamodb)

---

## Set up

* [DynamoDB Local](https://aws.amazon.com/blogs/aws/dynamodb-local-for-desktop-development/)
  * tool developed by Amazon /
    * supports ALL DynamoDB APIs
    * manipulate
      * NOT the actual DynamoDB tables | production
      * local DynamoDB tables
* TODO:
First, we add the DynamoDB Local dependency to the list of dependencies in our Maven configuration:



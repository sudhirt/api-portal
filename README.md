# api-portal

Central API repository for an organisation.

## Overview

[Spring Boot][boot] makes developing RESTful services easy and  [Swagger][swagger] makes documenting RESTful services easy. 
But different APIs developed by different teams are scattered across different applications. 
There is no simple way to maintain these APIs in a central location accessible to internal development teams.
This portal aims to build an API portal where publishers can publish API documentation for consumers to refer.

[boot]: https://projects.spring.io/spring-boot/

### Features

* Track multiple versions of an API
* Track API history
* Declare and track API deprecation plan
* Import [swagger][swagger] json

[swagger]: https://swagger.io/

### Prerequisites

* [Java 8][java]
* [Maven][mvn]
* [PostgreSQL][postgres]

[java]: http://www.oracle.com/technetwork/java/javase/overview/index.html
[mvn]: https://maven.apache.org/
[postgres]: https://www.postgresql.org/download/


### Run locally

You can run the service locally using the following from the command:

```
$ ./mvnw spring-boot:run
```

### Run tests

This service comes with some rudimentary tests as a good starting
point for writing your own.  Use the following command to execute the
tests using Maven:

```
$ ./mvnw test
```

---

# catalog-service

A Spring Boot application managing books in the catalog. It's a sample service to learn Spring Boot, taken from the book
"Cloud Native Spring in Action" from author Thomas Vitale, published by Manning Publications.

## Setup

The original setup was done using [spring initializr](https://start.spring.io). This generated an already running
skeleton.

## Building

```bash
# build the JAR artifact, should be located in ./build/libs/
./gradlew bootJar
```

## Running

```bash
# most convenient command to run the app during development:
./gradlew bootRun

# to run the built JAR artifact manually:
java -jar build/libs/catalog-service-0.0.1-SNAPSHOT.jar

# to post some data
http POST :9001/books author="Lyra Silverstar" title="" isbn="1234567891" price=9.90
# to get all books
http :9001/books
```

## Containerizing

```bash
./gradlew bootBuildImage
```

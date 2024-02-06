# URL Shortener 
This project is a simple URL shortener implementation in Java using Spring Boot.

The project uses MySQL as the database system. The database schema is managed and migrated using Flyway, ensuring that the database is in sync with the application's expected structure.
The URL shortener supports multiple key generation strategies, allowing users to choose the method that best suits their needs. These strategies include hashing, random number generation, and UUIDs.

1. ### Model (com.urlshortener.demo.model):
   UrlModel: Represents the entity for storing URL data in the database. It includes fields such as id, key, shortenedUrl, originalUrl, and keyGenerationMethod. The key can be generated using different methods, and the UrlModel class provides getters and setters 
   for these attributes.
   
2. ### Repository (com.urlshortener.demo.repository):
   UrlModelRepository: A Spring Data JPA repository interface for interacting with the database. It extends JpaRepository and includes methods for finding UrlModel by original URL, key, and checking the existence of a key.

3. ### Service (com.urlshortener.demo.service):
   UrlKeyGenerationService: An interface defining a method for generating URL keys.
   UrlHashedKeyGenerationService: Implements the UrlKeyGenerationService interface by generating hashed keys using SHA-256.
   UrlRandomNumberKeyGenerationService: Implements the UrlKeyGenerationService interface by generating random number-based keys.
   UrlUuidKeyGenerationService: Implements the UrlKeyGenerationService interface by generating keys using UUID.

4. ### UrlController facilitates the addition of new URLs, retrieval of shortened URLs, and redirection to the original URLs, providing a key component for the URL shortening functionality in application.

## Requirements:

JDK (21)

Maven <3.9.6>

Docker installed on your machine.


## Dependencies:

- Spring Boot 

- Spring Boot Thymeleaf

- Spring Boot Web

- Spring Data JPA

- Flyway Core 

- Flyway Maven Plugin 

- Flyway MYSQL 


## Running locally:

1. ### Clone the Repository

   To clone the repository, use the following commands:

```bash
   git clone <repository-url>
   cd <repository-directory>
```


3. ### **Build the Project:**
   ```bash
   
   To build project use following command:
   mvn package
   
   build docker image:
   docker build -t your-image-name:tag .

   Create and start the Docker containers using Docker Compose:
   docker-compose up

   To stop the Docker containers, run:
   docker-compose down

4. ### Database Migrations with Flyway:
   Flyway will automatically run the database migrations to set up the necessary schema.

5. ### Access the Application
   Once the containers are running and migrations are complete, you can access the URL Shortener application at http://localhost:8080/api/url/add-url.

## Configuration:

The docker-compose.yml file provides environment variables for configuring the application and database. Adjust these variables as needed for your setup.

SPRING_DATASOURCE_URL: JDBC URL for the MySQL database.

SPRING_DATASOURCE_USERNAME: MySQL database username.

SPRING_DATASOURCE_PASSWORD: MySQL database password.

### DockerFile:
```Dockerfile
FROM openjdk:21

WORKDIR /opt
ENV PORT 8080
EXPOSE 8080

COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
```

- The base image is OpenJDK 21.

- The working directory is set to /opt.

- The environment variable PORT is set to 8080.

- Port 8080 is exposed to allow external connections.

- The JAR file from the target directory is copied to /opt/app.jar.

- The entry point runs the Java application with the specified Java options.
   


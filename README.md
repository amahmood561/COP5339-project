# COP5339-project
Grad - class project

setup docker daemon on ur laptop run command to get postgres up java project will setup neccessary tables

docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=project -p 5432:5432 -d postgres

![image](https://user-images.githubusercontent.com/6902671/233868814-acb04043-e8b8-4de1-85f0-2e3e42585d26.png)

The src/main/java directory contains the main Java code of the project. 
The com.example.shopping package is the root package for the application, and it contains sub-packages for controller, model, repository, and service layers.
The src/main/resources directory contains non-Java resources, such as properties files, XML files, and static resources. In this case, it contains only application.properties file.

# COP 5339 Group 1 

Asam Mahmood
Lucas 
Lohith

Project has three workflow seller customer and login workflow 

## Requirements

* Java 8 or later
* Maven 3.6.0 or later

## Building the project
also please run docker
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=project -p 5432:5432 -d postgres

To build the project, navigate to the root directory and run the following command:

```
mvn clean package
```

This will compile the code, run the tests, and create an executable JAR file in the `target` directory.

## Running the application

To run the application, navigate to the root directory and run the following command:

```
java -jar target/project-1.0-SNAPSHOT.jar
```

This will start the application and display the login screen. You can log in as a seller or a customer to browse and purchase items.

## Architecture

This project is built using the Spring Boot framework and follows the Model-View-Controller (MVC) architectural pattern.

* `com.project.cop5339.controller`: contains the controllers that handle HTTP requests and responses.
* `com.project.cop5339.model`: contains the domain model classes.
* `com.project.cop5339.model.repository`: contains the repository classes that handle database access.
* `com.project.cop5339.service`: contains the service classes that encapsulate business logic.

## Testing

The project includes a set of unit tests and integration tests that can be run using the following command:

```
mvn test
```

The tests cover the most important functionality of the application, including creating and deleting items, creating and updating customers and sellers, and adding items to a shopping cart.
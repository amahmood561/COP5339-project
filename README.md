# COP5339-project
Grad - class project

setup docker daemon on ur laptop run command to get postgres up java project will setup neccessary tables

docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=project -p 5432:5432 -d postgres

![image](https://user-images.githubusercontent.com/6902671/233868814-acb04043-e8b8-4de1-85f0-2e3e42585d26.png)

The src/main/java directory contains the main Java code of the project. 
The com.example.shopping package is the root package for the application, and it contains sub-packages for controller, model, repository, and service layers.
The src/main/resources directory contains non-Java resources, such as properties files, XML files, and static resources. In this case, it contains only application.properties file.


#Jokes API
This API is a joke.`

### Project Description
This project is a simple REST API which allows a user to view, create, update and delete jokes.
Each Joke contains the following fields on the database table:

- id (long)
- content (String)
- createdAt (timestamp)
- updatedAt (timestamp)

The database used to store the data is PostgreSQL and a docker file in order to construct it is provided. 

In the ``com.example`` package you can find another 4 additional packages contained the MVC logics as well as the `Application.java``` class containing a main method.
The subpackages included are:
 - model (contains the database entity definitions )
 - repository(encapsulated logic for retrieving, searching & and updating entries from the data source)
 - controller (controls the dataflow into the model)
 - exception (package containing custom exceptions)

### Endpoints

There are in total 8 endpoints:

- DELETE api/v1/jokes/{id}                (deleted a resource with given id)
- GET    api/v1/jokes/{id}                (retrieves a resource with given id)
- GET    api/v1/jokes                     (retrieves all resources)
- GET    api/v1/jokes/search/keyword      (returns all resources whose content contains the given keyword)
- GET    api/v1/jokes/random              (returns a random resource)
- GET    api/v1/jokes/page                (returns a page containing resources)
- POST   api/v1/jokes                     (creates a new resource)
- PUT    api/v1/jokes/{id}                (updates a resource with given id)


### Building the project
In order to get this project to work, you should have Java, Maven and Docker installed on your machine. Once this is done run:

``` mvn clean install ```


### Docker

You can start the postgres db by running

``` docker-compose -f docker-compose.postgresql.yml up -d```

Once you run this command a postgres db will be created on your machine on port ```5433``` with the following data:

```
 - USER=test
 - PASSWORD=test
 - DB=jokesdb
```

### Running the project

You can run the project and play around with the endpoints by running the main method of the ``Application.java```class. You can manually send requests to the endpoints with ```curl```
or ```Postman``` or you can simply visit the following url once you have run the project:

```http://localhost:8080/swagger-ui-jokes.html```

### Improvements
- add service layer to encapsulate business logic
- add dtos for Joke model
- add script to provide test data for the database on start
- deploy api on cloud (AWS or GCP)
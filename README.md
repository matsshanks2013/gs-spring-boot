# gs-spring-boot

This is a spring boot application built with Maven
To run the application, call the Application.java. Run the class as a Java application.
OR from the command line

Go to the path -> gs-spring-boot-master\gs-spring-boot-master\complete\src\main\java\com\dev

Execute : mvn package && java -jar gs-spring-boot-0.1.0.jar
To access the REST services follow the instructions below

GET: http://localhost:8080/shops/   :- This will fetch all the details present in the Shop Model
GET: http://localhost:8080/shops/2 : This will fetch the individual shop details by ID.
POST: http://localhost:8080/shops/ :- This will create the JSON as a Shop Model

Application is not refactored into various packages yet.
Calls Rest services to fetch Shop Details, also updating latitude and longitude accessing Googlle API.
The first commit still requires logger implementation and source code refractoring

Open Issues

Static memory can still be improved by using Spring H2
junit can be improved by using Mockito

# gs-spring-boot

This is a spring boot application built with Maven
To run the application, call the Application.java. Run the class as a Java application.
To access the REST services follow the instructions below

GET: http://localhost:8080/shops/   :- This will fetch all the details present in the Shop Model
GET: http://localhost:8080/shops/2 : This will fetch the individual shop details by ID.
POST: http://localhost:8080/shops/ :- This will create the JSON as a Shop Model

Application is not refactored into various packages yet.
Calls Rest services to fetch Shop Details, also updating latitude and longitude accessing Googlle API.
The first commit still requires logger implementation and source code refractoring
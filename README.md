# Countries Application

This is a Spring Boot application that fetches and displays country information using the Rest Countries API.

## Prerequisites

- Java 21 or later
- Maven 3.8.1 or later
- MySQL (for the database)

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/eleonora2687/countries.git


2. Navigate into the project directory:

    ```bash
    cd countries


3. Set up the database:

    Create a MySQL database named countriesdb.
    Update the src/main/resources/application.properties file with your MySQL username and password.

4. Build and run the project using Maven:
    
    ```bash
    mvn spring-boot:run

5. Now, navigate to http://localhost:8080/fetch-countries
You will see a success message: "Countries fetched and stored successfully!"

6. That' s it! Your app is up and running!
Visit http://localhost:8080/welcome
Welcome to my app!
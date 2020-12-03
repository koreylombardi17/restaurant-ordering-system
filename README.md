# Restaurant Ordering System

This restaurant ordering system is built to manage customer's food orders, and allows for employees to view and change the status of food orders. <br>

#### Customer Functionality: 
* Place food orders 
* View the status of a current order 
* View previous food orders
* Reorder a previous food order

#### Employee Functionality:
* Clock in and out
* View the status of all current food orders
* Change the status of food orders 

#### Technologies Used:
* Java 11
* Spring Boot 2.4
* Apache Mavan 3.6.3
* MySQL 8.0.21
* IntelliJ or any IDE with Maven support
* Windows or Mac
* Internet browser

#### How to Install and Run Software:
Clone restaurant-ordering-system anywhere on your local machine and import the project into IntelliJ or any other IDE. Go into the properties file and change the database configurations to your database's username and password. Make sure you have an empty database setup named restaurant. If your database isn't running on localhost:3306, be sure to change the properties file under spring.datasource.url to whichever port you're using. Now that the project is configured properly, go into Maven plugins amd select spring-boot:run. The application will compile and be placed on localhost:8080. To reach the project's home page, go to your internet browser of choice and navigate to localhost:8080/.

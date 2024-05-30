# Customer
This Spring demo project uses the following libraries and frameworks :
1. Data JPA
2. Spring Web MVC
3. H2 in-memory database
4. Actuator
5. Swagger
6. Thymeleaf

This project aims to implement a simple Spring project that exposes an API with various operations to interact with Customer information stored in H2.

A user-interface is also available to interact with the API and is available at http://localhost:8080/view/customers

# Actuator 
The following endpoints are available for application monitoring :

http://localhost:8080/actuator

# API Swagger 

The swagger documentation for the api endpoints is available at the following URL : http://localhost:8080/swagger-ui.html

# Domain model

The domain model is composed of three entities : Customer, Address and Contact. A Customer can have one address and one contact.

# Sequence diagram

![customer.png](docs/customer.png)



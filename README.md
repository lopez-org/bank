# Banking transaction system :tw-1f4b9:

## Description :fa-file-o:

** What is it? **
Transaction banking systems play a fundamental role in facilitating and streamlining the management of money movements between accounts and financial institutions.

** What does it do? **
These systems represent the backbone of banking operations, enabling individuals, businesses and institutions to conduct a variety of transactions in a secure, efficient and convenient manner.

## Tools :tw-1f527:

- Spring Boot :tw-1f343:
- JPA :tw-1f50c:
- MySQL :tw-1f42c:

## Functionalities that the project supports

### 1.  Accounts
		1.1 Account opening
		1.2 Account deposits
		1.3 Transfers between accounts
		1.4 Consult account
		1.5 Block account


### 2. Pockets
		2.1 Pocket creation
		2.2 Transfers to pockets
		2.3 Pocket query

## Steps to execute the project locally

### how to clone and execute the project

- Copy the url o the repository

![](https://blogs.sap.com/wp-content/uploads/2019/07/2019-07-12_11-18-03.jpg)

- Run this command in Git ➡ git clone (url the repository)

![](https://media.geeksforgeeks.org/wp-content/uploads/20190429235125/git-clone.png)

- Set the environment variables for the database

![](https://i.stack.imgur.com/sbbjc.png)

- Create a database in MySQL with the same name as 
  "application.properties" and set your password to 
  "spring.datasource.password" or the same name as environment variables 

![](https://gustavopeiretti.com/properties-file-spring-boot/spring-boot-property-file-example.png)

## Information for developers

- Uses layered architecture
- Uses DTO design pattern
- Uses CI/CD
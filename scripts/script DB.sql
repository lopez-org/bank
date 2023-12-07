-- Creation and use of the DataBase
create database bank;
use bank;

-- Creation of the Person table with it's attributes
create table person(
id BIGINT PRIMARY KEY,
name VARCHAR(20),
lastName VARCHAR(20),
city VARCHAR(20),
address VARCHAR(30)
);

-- Creation of the User table with it's attributes
create table user(
id BIGINT PRIMARY KEY,
phoneNumber VARCHAR(10),
email VARCHAR(30),
password VARCHAR(15),
FOREIGN KEY (id) REFERENCES person(id)
);

-- Creation of the Account table with it's attributes
create table account(
accountNumber INT PRIMARY KEY,
ownerUser BIGINT UNIQUE,
accountType VARCHAR(10),
balance DOUBLE,
FOREIGN KEY (ownerUser) REFERENCES user(id)
);

-- Creation of the Pocket table with it's attributes
create table pocket(
name VARCHAR(15),
balance DOUBLE,
account_id INT,
FOREIGN KEY (account_id) REFERENCES account(accountNumber)
);

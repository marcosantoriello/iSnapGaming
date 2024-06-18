DROP DATABASE if exists iSnapGaming;
CREATE DATABASE iSnapGaming;
USE iSnapGaming;


CREATE TABLE User(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(30) NOT NULL,
    password varchar(300) NOT NULL,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    dateOfBirth DATE NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE Manager(
    id int NOT NULL,
    cf varchar(16) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES User(id)
);

CREATE TABLE Customer(
    id int NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES User(id)
);

CREATE TABLE Address(
    id int NOT NULL,
    street varchar(50) NOT NULL,
    city varchar(30) NOT NULL,
    postalCode varchar(30) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE CustomerOrder(
    id int NOT NULL,
    status varchar(30) NOT NULL,
    address varchar(30) NOT NULL,
    orderDate DATE NOT NULL,
    totalAmount int NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY(id) REFERENCES Customer(id)
);

CREATE TABLE Product(
    id int NOT NULL,
    name varchar(30) NOT NULL,
    softwareHouse varchar(30) NOT NULL,
    platform varchar(30) NOT NULL,
    price int NOT NULL,
    quantity int NOT NULL,
    category varchar(30) NOT NULL,
    pegi varchar(10) NOT NULL,
    releaseDate DATE NOT NULL,
    imagePath varchar(50) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES CustomerOrder(id)
);
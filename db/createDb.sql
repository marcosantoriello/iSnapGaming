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
    cf varchar(16),

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES User(id)
);

CREATE TABLE OrderManager(
    id int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES Manager(id)
);

CREATE TABLE ProductManager(
    id int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES Manager(id)
);

CREATE TABLE Customer(
    id int NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES User(id)
);

CREATE TABLE Address(
    id int NOT NULL AUTO_INCREMENT,
    customerId int NOT NULL,
    street varchar(50) NOT NULL,
    city varchar(30) NOT NULL,
    postalCode varchar(30) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(customerId) REFERENCES Customer(id) ON DELETE CASCADE
);

CREATE TABLE CustomerOrder(
    id int NOT NULL AUTO_INCREMENT,
    customerId int NOT NULL,
    status varchar(30) NOT NULL,
    address varchar(50) NOT NULL,
    orderDate DATE NOT NULL,
    totalAmount int NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY(customerId) REFERENCES Customer(id)
);

CREATE TABLE Product(
    id int NOT NULL AUTO_INCREMENT,
    prodCode int NOT NULL,
    name varchar(30) NOT NULL,
    softwareHouse varchar(30) NOT NULL,
    platform varchar(30) NOT NULL,
    price int NOT NULL,
    quantity int NOT NULL,
    category varchar(30) NOT NULL,
    pegi varchar(10) NOT NULL,
    releaseYear INT NOT NULL,
    imagePath varchar(50),
    available BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE OrderProduct(
    orderId int NOT NULL,
    productId int NOT NULL,
    quantity int NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (orderId, productId),
    FOREIGN KEY(orderId) REFERENCES CustomerOrder(id),
    FOREIGN KEY(productId) REFERENCES Product(id)
);
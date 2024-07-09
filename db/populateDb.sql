INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('mrossi@isnapgaming.com', 'hvweU48gkereflsll3', 'Mario', 'Rossi', '1990-01-01');
INSERT INTO Customer (id) VALUES (1);
INSERT INTO Manager (id) VALUES (1);
INSERT INTO ProductManager (id) VALUES (1);
INSERT INTO OrderManager (id) VALUES (1);

INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('pippo@isnapgaming.com', 'tuiyui4y5E', 'Pippo', 'Prova', '1990-01-01');
INSERT INTO Customer (id) VALUES (2);
INSERT INTO Manager (id) VALUES (2);
INSERT INTO ProductManager (id) VALUES (2);

INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('abianchi@gmail.com', 'rUmVr7DNi1Y', 'Antonio', 'Bianchi', '1990-01-01');
INSERT INTO Customer (id) VALUES (3);

INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('lverdi@gmail.com', 'x5fhYglVQ7S', 'Luca', 'Verdi', '1990-01-01');
INSERT INTO Manager (id) VALUES (4);
INSERT INTO ProductManager (id) VALUES (4);

INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('pmarchi@gmail.com', '2hOHk4ObrfI', 'Paolo', 'Marchi', '1990-01-01');
INSERT INTO Manager (id) VALUES (5);
INSERT INTO OrderManager (id) VALUES (5);

INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(751, "EA Sports FC 24", "EA Games", "PS5", 55, 10, "SPORTS", "PEGI3", 2023, "products\\751_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(331, "Hell Let Loose", "Team17 Digital Ltd", "PC", 60, 30, "ACTION", "PEGI16", 2019, "products\\331_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(652, "Fortnite", "Epic Games", "PS4", 15, 100, "ACTION", "PEGI12", 2017, "products\\652_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(111, "God of War Ragnarok", "Santa Monica Studio", "PS5", 70, 100, "ADVENTURE", "PEGI18", 2022, "products\\111_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(482, "Red Dead Redemption II", "Rockstar Games", "PS4", 25, 100, "ADVENTURE", "PEGI18", 2019, "products\\482_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(812, "We Were Here Forever", "Total Mayhem Games", "XBOX", 10, 100, "PUZZLE", "PEGI12", 2022, "products\\812_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(157, "Civilization VI", "Firaxis Games", "PC", 7, 100, "PUZZLE", "PEGI12", 2016, "products\\157_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(987, "Uncharted 4: A Thief's End", "Naughty Dog", "PS4", 20, 100, "ACTION", "PEGI16", 2016, "products\\987_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(268, "Euro Truck Simulator 2", "SCS Software", "PS4", 15 , 100, "SIMULATION", "PEGI3", 2012, "products\\268_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(556, "Death Stranding", "Sony Interactive Entertainment", "PS5", 55 , 100, "ACTION", "PEGI18", 2019, "products\\556_1.jpg", true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath, available) values(395, "Resident Evil Village", "Capcom", "PS5", 40 , 100, "HORROR", "PEGI18", 2021, "products\\395_1.jpg", true);

INSERT INTO customerorder(customerId, status, address, orderDate, totalAmount) VALUES (3, "TO_BE_MANAGED", "Via Roma", '2024-01-01', 60);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (1, 1, 3, 20);

INSERT INTO customerorder(customerId, status, address, orderDate, totalAmount) VALUES (3, "TO_BE_MANAGED", "Via Roma", '2024-02-02', 100);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (2, 1, 1, 50);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (2, 3, 1, 50);

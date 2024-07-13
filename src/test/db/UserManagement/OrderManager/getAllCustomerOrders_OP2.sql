INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('abianchi@gmail.com', 'hvweU48gkereflsll3', 'Antonio', 'Bianchi', '1990-01-01');
INSERT INTO Customer (id) VALUES (1);

INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, available) values(751, "Fifa23", "EA Games", "PC", 55, 10, "SPORTS", "PEGI3", 2023, true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, available) values(331, "Hell Let Loose", "Team17 Digital Ltd", "PC", 60, 30, "ACTION", "PEGI16", 2019, true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, available) values(652, "Fortnite", "Epic Games", "PS4", 15, 100, "ACTION", "PEGI12", 2017, true);
INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, available) values(652, "Cod WWII", "Activision", "PC", 30, 75, "SHOOTER", "PEGI18", 2017, true);

INSERT INTO customerorder(customerId, status, address, orderDate, totalAmount) VALUES (1, "TO_BE_MANAGED", "Via Roma", '2024-01-01', 60);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (1, 1, 3, 20);

INSERT INTO customerorder(customerId, status, address, orderDate, totalAmount) VALUES (1, "TO_BE_MANAGED", "Via Roma", '2024-02-02', 100);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (2, 1, 1, 50);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (2, 3, 1, 50);
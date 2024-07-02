INSERT INTO user (username, password, firstName, lastName, dateOfBirth) VALUES ('abianchi@gmail.com', 'hvweU48gkereflsll3', 'Antonio', 'Bianchi', '1990-01-01');
INSERT INTO Customer (id) VALUES (1);

INSERT INTO product(prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, available) values(751, "Fifa23", "EA Games", "PC", 55, 10, "SPORTS", "PEGI3", 2023, true);

INSERT INTO customerorder(customerId, status, address, orderDate, totalAmount) VALUES (1, "SHIPPED", "Via Roma", '2024-01-01', 60);
INSERT INTO orderproduct(orderId, productId, quantity, price) VALUES (1, 1, 3, 20);

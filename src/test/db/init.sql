DROP database if exists iSnapGaming;

CREATE DATABASE iSnapGaming;
DROP USER IF EXISTS 'testClient'@'localhost';
CREATE USER 'testClient'@'localhost' IDENTIFIED BY 'testClient';
GRANT ALL ON *.* TO 'testClient'@'localhost';
FLUSH PRIVILEGES;
USE iSnapGaming;
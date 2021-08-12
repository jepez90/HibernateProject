-- configuration database for hibernate_test project

-- creates database hibernete_test
CREATE DATABASE IF NOT EXISTS hibernate_test;

-- creates a database user user_test
CREATE USER IF NOT EXISTS 'user_test'@'localhost' IDENTIFIED BY 'admin';

-- add privileges to the user hibernate_test
GRANT ALL PRIVILEGES ON hibernate_test.* TO 'user_test'@'localhost';
GRANT SELECT ON performance_schema.* TO 'user_test'@'localhost';

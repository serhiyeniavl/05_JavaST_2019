CREATE DATABASE `car_rent_system_db` DEFAULT CHARACTER SET utf8;

CREATE USER 'manual_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE ON `car_rent_system_db`.* TO 'manual_user'@'localhost';



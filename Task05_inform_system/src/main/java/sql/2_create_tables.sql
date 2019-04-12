USE `car_rent_system_db`;

CREATE TABLE `Users`
(
  `ID`       int         NOT NULL AUTO_INCREMENT,
  `login`    varchar(30) NOT NULL UNIQUE,
  `password` varchar(50) NOT NULL,
  `role`     varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `Orders`
(
  `ID`          int       NOT NULL AUTO_INCREMENT,
  `car_id`      int       NOT NULL,
  `user_id`     int       NOT NULL,
  `issue_date`  TIMESTAMP NOT NULL,
  `return_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `Addresses`
(
  `ID`     int         NOT NULL AUTO_INCREMENT,
  `city`   varchar(30) NOT NULL,
  `street` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `Black_list`
(
  `locked_user`   int         NOT NULL AUTO_INCREMENT,
  `passport_data` varchar(30) NOT NULL UNIQUE,
  PRIMARY KEY (`locked_user`)
);

CREATE TABLE `Cars`
(
  `ID`               int         NOT NULL AUTO_INCREMENT,
  `vin_code`         varchar(30) NOT NULL UNIQUE,
  `brand_name`       varchar(30) NOT NULL,
  `description`      varchar(50),
  `reg_number`       varchar(10) NOT NULL UNIQUE,
  `year_made`        int     NOT NULL,
  `run`              int         NOT NULL,
  `last_maintenance` int        NOT NULL,
  `rent_price`       int     NOT NULL,
  `mechanic_code`    int,
  `class_auto`       char        NOT NULL,
  `image_path`       varchar(50),
  PRIMARY KEY (`ID`)
);

CREATE TABLE `User_data`
(
  `user_id`         int         NOT NULL AUTO_INCREMENT,
  `fname`           varchar(30) NOT NULL,
  `lname`           varchar(30) NOT NULL,
  `passport_data`   varchar(30) NOT NULL UNIQUE,
  `orders_quantity` int         NOT NULL,
  `address_id`      int         NOT NULL UNIQUE,
  PRIMARY KEY (`user_id`)
);

ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_fk0` FOREIGN KEY (`car_id`) REFERENCES `Cars` (`ID`);

ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_fk1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`ID`);

ALTER TABLE `Black_list`
  ADD CONSTRAINT `Black_list_fk0` FOREIGN KEY (`locked_user`) REFERENCES `Users` (`ID`);

ALTER TABLE `Black_list`
  ADD CONSTRAINT `Black_list_fk1` FOREIGN KEY (`passport_data`) REFERENCES `User_data` (`passport_data`);

ALTER TABLE `User_data`
  ADD CONSTRAINT `User_data_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users` (`ID`);

USE `car_rent_system_db`;

CREATE TABLE `User_data` (
                           `user_id` int(11) NOT NULL AUTO_INCREMENT,
                           `fname` varchar(30) NOT NULL,
                           `lname` varchar(30) NOT NULL,
                           `passport_id` int(30) NOT NULL UNIQUE,
                           `address` varchar(40) NOT NULL,
                           PRIMARY KEY (`user_id`)
);

CREATE TABLE `Car_info` (
                          `car_id` int(11) NOT NULL AUTO_INCREMENT,
                          `vin_code` varchar(30) NOT NULL UNIQUE,
                          `reg_number` varchar(10) NOT NULL UNIQUE,
                          `run` int NOT NULL,
                          `last_maintenance` smallint,
                          PRIMARY KEY (`car_id`)
);

CREATE TABLE `Users` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `login` varchar(30) NOT NULL UNIQUE,
                       `password` varchar(255) NOT NULL,
                       `role` tinyint NOT NULL,
                       PRIMARY KEY (`id`)
);

CREATE TABLE `Orders` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `car_id` int NOT NULL,
                        `user_id` int NOT NULL,
                        `status` VARCHAR(40) NOT NULL,
                        `issue_date` TIMESTAMP default null,
                        `return_date` TIMESTAMP default null,
                        `real_return_date` TIMESTAMP default null,
                        `final_price` smallint NOT NULL default 0,
                        PRIMARY KEY (`id`)
);

CREATE TABLE `Black_list` (
                            `user_id` int NOT NULL AUTO_INCREMENT,
                            `reason` varchar(30) NOT NULL,
                            `lock_date` TIMESTAMP NOT NULL,
                            `unlock_date` TIMESTAMP NOT NULL,
                            PRIMARY KEY (`user_id`)
);

CREATE TABLE `Cars` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `description` varchar(255),
                      `year_made` smallint NOT NULL,
                      `brand_name` varchar(30) NOT NULL,
                      `rent_price` smallint NOT NULL,
                      `class_auto` char NOT NULL,
                      `image_path` varchar(1024) NOT NULL,
                      PRIMARY KEY (`id`)
);

CREATE TABLE `Passport` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `serie` varchar(2) NOT NULL,
                          `number` int NOT NULL UNIQUE,
                          `id_number` varchar(20) NOT NULL UNIQUE,
                          `issue_date` DATE NOT NULL,
                          `end_date` DATE NOT NULL,
                          PRIMARY KEY (`id`)
);


ALTER TABLE `Orders` ADD CONSTRAINT `Orders_fk1` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `Orders` ADD CONSTRAINT `Orders_fk0` FOREIGN KEY (`car_id`) REFERENCES `Cars`(`id`);

ALTER TABLE `Black_list` ADD CONSTRAINT `Black_list_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `User_data` ADD CONSTRAINT `User_data_fk0`
  FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`) ON DELETE cascade ON UPDATE CASCADE;

ALTER TABLE `Car_info` ADD CONSTRAINT `Car_info_fk0`
  FOREIGN KEY (`car_id`) REFERENCES `Cars`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `Passport` ADD CONSTRAINT `Passport_fk0`
  FOREIGN KEY (`id`) REFERENCES `User_data`(`passport_id`) ON DELETE CASCADE ON UPDATE CASCADE;


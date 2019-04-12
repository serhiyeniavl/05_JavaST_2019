USE `car_rent_system_db`;


INSERT INTO Users(login, password, role)
VALUES ('marshal@gmail.com', 'qwerty007', 'USER'),
       ('neadrd@gmail.com', 'qwerty008', 'MANAGER'),
       ('hach@mail.ru', 'qwerty009', 'USER');

INSERT INTO Addresses(city, street)
VALUES ('Minsk', 'Kurchatova 8'),
       ('Minsk', 'Dzerjinskogo 11'),
       ('Minsk', 'Kurchatova 5');

INSERT INTO Cars(vin_code, brand_name, description, reg_number, year_made, run,
                 last_maintenance, rent_price, mechanic_code, class_auto,
                 image_path)
VALUES ('470132ABf89', 'Audi', '', '5859AB-7', '2017', '12356', '2018', '40',
        '41567', 'C', ''),
       ('412356ANgf9', 'Citroen', '', '5983AB-7', '2016', '36954', '2018', '30',
        '74235', 'B', '');

INSERT INTO User_data (user_id, fname, lname, passport_data, orders_quantity,
                       address_id)
VALUES ('2', 'Andrey', 'Svirid', 'AB381284', '0', '2');

INSERT INTO User_data (fname, lname, passport_data, orders_quantity, address_id)
VALUES ('Dmitry', 'Ivanov', 'HK381263', '0', '3'),
       ('Kirill', 'Petrov', 'AB381574', '0', '4');
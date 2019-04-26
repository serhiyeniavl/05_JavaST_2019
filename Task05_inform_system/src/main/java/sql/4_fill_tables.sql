USE `car_rent_system_db`;


INSERT INTO Users(login, password, role)
VALUES ('marshal@gmail.com', '$2a$12$W/cFqbDwELpvG0HpwIagXOXMo9iA.KCpVwlA3A50rpRR9nHTBaif2', '1'),
       ('neadrd@gmail.com', '$2a$12$zrFEei3e62gzWxERGqtquuUmijcmYy7KuRatDEL.CmK.wnewQW4Za', '2'),
       ('hach@mail.ru', '$2a$12$RLr65fglUuCn4tuMEp5IUuyZjmZ4NLi4ujzK7sUzb1jfCE.GKI22y', '1');

INSERT INTO User_data (user_id, fname, lname, passport_id,
                       address)
VALUES ('2', 'Andrey', 'Svirid', '1', 'Minsk, Dzerjinskogo 11');

INSERT INTO User_data (user_id, fname, lname, passport_id, address)
VALUES ('3', 'Dmitry', 'Ivanov', '2', 'Minsk, Kurchatova 8'),
       ('4', 'Kirill', 'Petrov', '3', 'Minsk, Kurchatova 5');

INSERT INTO Passport(serie, number, id_number, issue_date, end_date) VALUES
('AB', '4959395', 'FGNBNR63cH3', '2015-08-23', '2025-08-23'),
('AB', '5839392', 'FFBCNR63cH3', '2016-09-23', '2026-09-23'),
('HK', '4435674', 'FVDDGB39jN2', '2014-02-21', '2024-02-21');

INSERT INTO Cars(vin_code, brand_name,  reg_number, rent_price, class_auto,
                 image_path)
VALUES ('452829faJF3', 'Audi', '5859AB-7', '40', 'C', 'audi'),
       ('412356ANgf9', 'Citroen',  '4829HR-2', '30','B', 'citroen');

INSERT INTO Orders(CAR_ID, USER_ID, ISSUE_DATE, RETURN_DATE, REAL_RETURN_DATE, FINAL_PRICE) VALUES
('1', '2', '2019-04-24 15:00:00', '2019-04-25 17:00:00', '2019-04-25 13:25:00', '85');

INSERT INTO Orders(CAR_ID, USER_ID, ISSUE_DATE, RETURN_DATE, FINAL_PRICE) VALUES
('2', '3', '2019-04-24 11:00:00', '2019-04-27 19:00:00', '65');

INSERT INTO Car_info(description, year_made, run, last_maintenance) VALUES
('', '2017', '123456', '2018'),
('','2016','456745', '2018');

INSERT INTO Black_list(user_id, reason, lock_date, unlock_date) VALUES
('4', 'Didn\'t return a car in time', '2019-04-23', '2019-05-10');



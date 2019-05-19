USE `car_rent_system_db`;


INSERT INTO Users(email, password, role)
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
('AB', '4959395', '4851723C758PB3', '2015-08-23', '2025-08-23'),
('AB', '5839392', '5913484C354PD2', '2016-09-23', '2026-09-23'),
('HK', '4435674', '5943856C451PD2', '2014-02-21', '2024-02-21');

INSERT INTO Cars(description, year_made,  brand_name,  rent_price, class_auto,
                 image_path)
VALUES ('The Audi A4 is a line of compact executive cars C class auto,
which provide for you extra comfort during your drive.', '2017', 'Audi A4',  '10', 'C', 'audiA4.jpg'),
       ('VW Polo is an ideal ratio between price and comfort. If you need relatively
inexpensive car and do not want to look uncomfortable - this is your choice.',
        '2016', 'Volkswagen Polo',   '8','B', 'vwPolo.jpg');

INSERT INTO Car_info(vin_code, reg_number, run) VALUES
('1AJTU42KTIB584931', '5859AB-7', '123456'),
('1ITUV57RYFM493048', '4829HR-2', '456745');

INSERT INTO Black_list(user_id, reason, lock_date, unlock_date) VALUES
('4', 'Didn\'t return a car in time', '2019-04-23', '2019-05-10');
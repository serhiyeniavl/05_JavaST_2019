USE `car_rent_system_db`;

# passwords for everyone user : qwer
INSERT INTO Users(email, password, role)
VALUES ('marshal@gmail.com', '$2a$10$CruzOrrcTRJft0hKsJ/M5.X5Qpl1wg8SWfwsXvqkpjeLuqn1H.RGy', '1'),
       ('neadrd@gmail.com', '$2a$10$H8bl2VPqp3lzCB91zxaG8.SEZJrEl/tjq1wdaRcQEVY0FR/cToTsy', '2'),
       ('hac@mail.ru', '$2a$10$XLBHhzsZY6O6J4nmRJYimeuBkWRiZV0cdqeVahPdHFPwbqWpJEPmC', '1'),
       ('serg@mail.ru', '$2a$10$aYxqeodOQJembvHYWAwSTeaDUMqrNJAiimdXUAmrR8.UurrBMRdLe', '1');

INSERT INTO User_data (user_id, fname, lname, passport_id,
                       address)
VALUES ('2', 'Andrey', 'Svirid', '1', 'Minsk, Dzerjinskogo 11');

INSERT INTO User_data (user_id, fname, lname, passport_id, address)
VALUES ('3', 'Dmitry', 'Ivanov', '2', 'Minsk, Kurchatova 8/20'),
       ('4', 'Kirill', 'Petrov', '3', 'Minsk, Kurchatova 5/53'),
       ('5', 'Anton', 'Kuprevich', '4', 'Minsk, Komsomoltcev 28/7');


INSERT INTO Passport(serie, number, id_number, issue_date, end_date) VALUES
('AB', '4959395', '4851723C758PB3', '2015-08-23', '2025-08-23'),
('AB', '5839392', '5913484C354PD2', '2016-09-23', '2026-09-23'),
('HK', '4435674', '5943856C451PD2', '2014-02-21', '2024-02-21'),
('HK', '2948576', '3444356C456PD2', '2016-07-22', '2026-07-22');

INSERT INTO Cars(description, year_made,  brand_name,  rent_price, class_auto,
                 image_path)
VALUES ('The Audi A4 is a line of compact executive cars C class auto,
which provide for you extra comfort during your drive.', '2017', 'Audi A4',  '10', 'C', 'audiA4.jpg'),
       ('VW Polo is an ideal ratio between price and comfort. If you need relatively
inexpensive car and do not want to look uncomfortable - this is your choice.',
        '2016', 'Volkswagen Polo', '8','B', 'vwPolo.jpg'),
       ('Mercedes-Benz E-class AMG sedan will allow you to fill superiority
       through out your drive. Best choice for Mercedes fans.',
        '2019', 'Mercedes-Benz E-class', '25','E', 'm-benz-e-class-amg.jpg');

INSERT INTO Car_info(vin_code, reg_number, run) VALUES
('1AJTU42KTIB584931', '5859AB-7', '123456'),
('1ITUV57RYFM493048', '4829HR-2', '456745'),
('1FJBM57RYFM84958', '4952HR-7', '456745');

INSERT INTO Black_list(user_id, reason, lock_date, unlock_date) VALUES
('4', 'Didn\'t return a car in time.', '2019-05-22', '2019-05-25');
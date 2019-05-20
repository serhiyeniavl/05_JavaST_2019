USE `car_rent_system_db`;

INSERT INTO Users(email, password, role)
VALUES ('admin@admin', '$2a$10$X0vJrFoo6eUebIm8VjTogu3xO28Rkv359MZrAu.PycpsLnOLh3NU2', '3');
# admin password : neadmin

INSERT INTO User_data(user_id, fname, lname, passport_id, address) VALUES
('1', 'Vladislav', 'Sergienya', '-1', 'Minsk, Pobediteley 7');

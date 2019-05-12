SELECT login, password
from Users
WHERE login = 'hach@mail.ru'
  AND password = '$2a$12$RLr65fglUuCn4tuMEp5IUuyZjmZ4NLi4ujzK7sUzb1jfCE.GKI22y';
# login into system

SELECT login, fname, lname, address
from Users
       JOIN User_data on Users.id = User_data.user_id;


SELECT login,
       fname,
       lname,
       address,
       serie,
       number,
       id_number,
       issue_date,
       end_date
from Users,
     User_data,
     Passport
where User_data.user_id = Users.id
  and User_data.passport_id = Passport.id;
# all user date

SELECT login,
       fname,
       lname,
       issue_date,
       return_date,
       real_return_date,
       final_price,
       reg_number,
       brand_name
from Users,
     User_data,
     Orders,
     Cars
WHERE User_data.user_id = Users.id
  and Users.id = Orders.user_id
  and Orders.car_id = Cars.id;
# all orders

SELECT login, fname, lname, issue_date, return_date
FROM Users
       JOIN User_data Ud on Users.id = Ud.user_id
       JOIN Orders O on Users.id = O.user_id
WHERE O.real_return_date IS NULL;
# all active orders

SELECT login, serie, number, id_number, reason, lock_date, unlock_date
from Users
       join Black_list Bl on Users.id = Bl.user_id
       join User_data Ud on Users.id = Ud.user_id
       join Passport P on Ud.passport_id = P.id;
# see all black list with additional info for managers

SELECT login, fname, reason, unlock_date
from Users
       join User_data Ud on Users.id = Ud.user_id
       left join Black_list Bl on Users.id = Bl.user_id
# see all users with ban info

SELECT login, fname, reason, unlock_date
from Users
       join User_data Ud on Users.id = Ud.user_id
       left join Black_list Bl on Users.id = Bl.user_id
where Bl.unlock_date > CURDATE();
# see current blocked users

SELECT COUNT(Orders.user_id), Users.id, login, fname, lname
from Orders
       join Users ON Orders.user_id = Users.id
       join User_data Ud on Users.id = Ud.user_id
WHERE Orders.real_return_date is not null
group by login
HAVING COUNT(Orders.user_id) > 3
#see all users who has more 3 orders to give them a discount

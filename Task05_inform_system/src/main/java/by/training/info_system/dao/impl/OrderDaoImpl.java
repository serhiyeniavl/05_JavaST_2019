package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.OrderDao;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.CarInfo;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.entity.status.OrderStatus;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class OrderDaoImpl extends AbstractDao implements OrderDao {
    @Override
    public Optional<List<Order>> readCurrentOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findOverdue() {
        return Optional.empty();
    }

    @Override
    public boolean create(final Order entity) {
        String sql = "INSERT INTO Orders (`car_id`, `user_id`, `status`)" +
                " VALUES (?,?,?)";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, entity.getCar().getId());
            statement.setLong(2, entity.getUser().getId());
            statement.setString(3, OrderStatus.NOT_CONFIRMED.getValue());

            int query = statement.executeUpdate();
            if (query < 1) {
                throw new SQLException("Error when trying to insert order");
            }
        } catch (SQLException e) {
            log.error("Cannot execute prepared statement", e);
            return false;
        } finally {
            closePreparedStatement(statement);
        }
        return true;
    }

    @Override
    public Optional<Order> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> getAll() {
        String sql = "SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number,"
                + " login, role, fname, lname, serie, number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "JOIN Users U on Orders.user_id = U.id "
                + "JOIN User_data Ud on U.id = Ud.user_id "
                + "JOIN Passport P on Ud.passport_id = P.id";
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Passport passport = Passport.builder()
                        .serie(resultSet.getString("serie"))
                        .number(resultSet.getInt("number"))
                        .build();
                User user = User.builder()
                        .login(resultSet.getString("login"))
                        .role(Role.fromValue(resultSet.getInt("role")))
                        .userData(
                                UserData.builder()
                                        .fName(resultSet.getString("fname"))
                                        .lName(resultSet.getString("lname"))
                                        .passport(passport)
                                        .build()
                        )
                        .build();
                Car car = Car.builder()
                        .brandName(resultSet.getString("brand_name"))
                        .imagePath(resultSet.getString("image_path"))
                        .rentPrice(resultSet.getShort("rent_price"))
                        .carInfo(
                                CarInfo.builder()
                                        .regNumber(resultSet.getString("reg_number"))
                                        .build()
                        )
                        .build();

                Order order = Order.builder()
                        .user(user)
                        .car(car)
                        .status(OrderStatus.fromValue(resultSet.getString("status")))
                        .build();
                order.setId(resultSet.getLong("id"));
                long finalPrice = resultSet.getLong("final_price");
                if (finalPrice != 0) {
                    order.setFinalPrice(finalPrice);
                }
                Object issueDate = resultSet.getObject("issue_date");
                if (issueDate != null) {
                    order.setIssueDate(resultSet.getTimestamp("issue_date")
                            .toLocalDateTime());
                    order.setReturnDate(resultSet.getTimestamp("return_date")
                            .toLocalDateTime());
                }
                Object realReturnDate = resultSet.getObject("real_return_date");
                if (realReturnDate != null) {
                    order.setRealReturnDate(resultSet.getTimestamp("real_return_date")
                            .toLocalDateTime());
                }
                orders.add(order);
            }
            return Optional.of(orders);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(final Order entity) {
        return false;
    }

    @Override
    public boolean delete(final long id) {
        return false;
    }

}

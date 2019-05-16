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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Integer countOrders() {
        String sql = "SELECT COUNT(id) FROM Orders";
        return countOrders(sql);
    }

    @Override
    public Integer countOrders(final long userId) {
        String sql = String.format("SELECT COUNT(id) FROM Orders WHERE user_id = %s", userId);
        return countOrders(sql);
    }

    @Override
    public Optional<List<Order>> findOverdue() {
        return Optional.empty();
    }

    @Override
    public Integer create(final Order entity) {
        String sql = "INSERT INTO Orders (`car_id`, `user_id`, `status`)" +
                " VALUES (?,?,?)";
        PreparedStatement statement = createPreparedStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        int orderId = 0;
        ResultSet resultSet = null;
        try  {
            statement.setLong(1, entity.getCar().getId());
            statement.setLong(2, entity.getUser().getId());
            statement.setString(3, OrderStatus.NOT_CONFIRMED.getValue());

            int query = statement.executeUpdate();
            if (query < 1) {
                throw new SQLException("Error when trying to insert order");
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Cannot execute prepared statement", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Error when close result set", e);
                }
            }
            closePreparedStatement(statement);
        }
        return orderId;
    }

    @Override
    public Optional<Order> get(long id) {
        String sql = "SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number,"
                + " email, role, fname, lname, serie, number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "JOIN Users U on Orders.user_id = U.id "
                + "JOIN User_data Ud on U.id = Ud.user_id "
                + "JOIN Passport P on Ud.passport_id = P.id "
                + "WHERE Orders.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement");
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Passport passport = Passport.builder()
                    .serie(resultSet.getString("serie"))
                    .number(resultSet.getInt("number"))
                    .build();
            User user = User.builder()
                    .email(resultSet.getString("email"))
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
            return Optional.of(order);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> getAll() {
        String sql = "SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number,"
                + " email, role, fname, lname, serie, number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "JOIN Users U on Orders.user_id = U.id "
                + "JOIN User_data Ud on U.id = Ud.user_id "
                + "JOIN Passport P on Ud.passport_id = P.id";
        return getAll(sql);
    }

    @Override
    public boolean update(final Order entity) {
        String sql = "UPDATE Orders SET status = ?, issue_date = ?,"
                + " return_date = ?, real_return_date = ?, final_price = ? "
                + "WHERE Orders.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        Timestamp issueDate
                = entity.getIssueDate() != null ? Timestamp.valueOf(entity.getIssueDate())
                : null;
        Timestamp returnDate
                = entity.getReturnDate() != null ? Timestamp.valueOf(entity.getReturnDate())
                : null;
        Timestamp realReturnDate
                = entity.getRealReturnDate() != null ? Timestamp.valueOf(entity.getRealReturnDate())
                : null;
        Long finalPrice = entity.getFinalPrice();

        try {
            statement.setString(1, entity.getStatus().getValue());
            if (issueDate != null) {
                statement.setTimestamp(2, issueDate);
            } else {
                statement.setNull(2, Types.TIMESTAMP);
            }
            if (returnDate != null) {
                statement.setTimestamp(3, returnDate);
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }
            if (realReturnDate != null) {
                statement.setTimestamp(4, realReturnDate);
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }
            if (finalPrice != null) {
                statement.setLong(5, entity.getFinalPrice());
            } else {
                statement.setNull(5, Types.SMALLINT);
            }
            statement.setLong(6, entity.getId());
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try {
            int query = statement.executeUpdate();
            if (query < 1) {
                throw new SQLException("Error when trying to update order");
            }
            return true;
        } catch (SQLException e) {
            log.error("Cannot update order status", e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    @Override
    public boolean update(final long id, final OrderStatus status) {
        String sql = "UPDATE Orders SET status = ? WHERE Orders.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, status.getValue());
            statement.setLong(2, id);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try {
            int query = statement.executeUpdate();
            if (query < 1) {
                throw new SQLException("Error when trying to update order");
            }
            return true;
        } catch (SQLException e) {
            log.error("Cannot update order status", e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    @Override
    public boolean delete(final long id) {
        return false;
    }

    @Override
    public boolean isActive(final long userId) {
        String sql = "SELECT car_id, user_id, status FROM Orders "
                + "WHERE user_id = ? AND status NOT IN (?,?)";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, userId);
            statement.setString(2, OrderStatus.COMPLETED.getValue());
            statement.setString(3, OrderStatus.DENIED.getValue());
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    @Override
    public Optional<List<Order>> findOrders(final long userId,
                                            final int page,
                                            final int recordsPerPage) {
        String sql = "SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "WHERE Orders.user_id = ? "
                + "LIMIT ? OFFSET ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, userId);
            statement.setInt(2, recordsPerPage);
            statement.setInt(3, (page - 1) * recordsPerPage);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
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
                }
                Object returnDate = resultSet.getObject("return_date");
                if  (returnDate != null) {
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
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> findOrder(final long userId,
                                     final long orderId) {
        String sql = "SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "WHERE Orders.user_id = ? AND Orders.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, userId);
            statement.setLong(2, orderId);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
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
                }
                Object returnDate = resultSet.getObject("return_date");
                if  (returnDate != null) {
                    order.setReturnDate(resultSet.getTimestamp("return_date")
                            .toLocalDateTime());
                }
                Object realReturnDate = resultSet.getObject("real_return_date");
                if (realReturnDate != null) {
                    order.setRealReturnDate(resultSet.getTimestamp("real_return_date")
                            .toLocalDateTime());
                }
                return Optional.of(order);
            }
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findOrders(long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> getAll(final int page, final int ordersPerPage) {
        String sql = String.format("SELECT Orders.id, status, Orders.issue_date, return_date, real_return_date,"
                + "final_price, brand_name, rent_price, image_path, reg_number,"
                + " email, role, fname, lname, serie, number"
                + " FROM Orders JOIN Cars C on Orders.car_id = C.id "
                + "JOIN Car_info Ci on C.id = Ci.car_id "
                + "JOIN Users U on Orders.user_id = U.id "
                + "JOIN User_data Ud on U.id = Ud.user_id "
                + "JOIN Passport P on Ud.passport_id = P.id "
                + "LIMIT %s OFFSET %s", ordersPerPage, (page-1) * ordersPerPage);
        return getAll(sql);
    }

    private Optional<List<Order>> getAll(final String sql) {
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Passport passport = Passport.builder()
                        .serie(resultSet.getString("serie"))
                        .number(resultSet.getInt("number"))
                        .build();
                User user = User.builder()
                        .email(resultSet.getString("email"))
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
                }
                Object returnDate = resultSet.getObject("return_date");
                if  (returnDate != null) {
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

    private Integer countOrders(final String sql) {
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return 0;
    }
}

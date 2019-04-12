package by.training.info_system.dao;

import by.training.info_system.dao.helper.DaoHelper;
import by.training.info_system.db_connection.ConnectionPool;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserDaoImpl implements Dao<User> {
    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "SELECT fname, lname, passport_data, orders_quantity, role"
            + " FROM Users INNER JOIN User_data ON Users.ID = User_data.user_id";

    private Connection connection;

    private DaoHelper helper;

    public UserDaoImpl() {
        helper = new DaoHelper();
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public Optional<User> get(long id) {
        connection = ConnectionPool.getInstance().getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
//
//        } catch (SQLException e) {
//            log.error("SQL exception when find all users", e);
//        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        List<User> users = new ArrayList<>();
        connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                UserData userData = new UserData();
                userData.setFName(resultSet.getString("fname"));
                userData.setLName(resultSet.getString("lname"));
                userData.setPassportData(resultSet.getString("passport_data"));
                userData.setOrdersQuantity(resultSet.getInt("orders_quantity"));
                user.setRole(Role.fromValue(resultSet.getString("role")));
                user.setUserData(userData);
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException e) {
            log.error("SQL exception when find all users", e);
        } finally {
            helper.close(resultSet);
            helper.close(statement);
            helper.close(connection);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(long id, User newEntity) {
        return false;
    }

    @Override
    public boolean update(User user, User newEntity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}

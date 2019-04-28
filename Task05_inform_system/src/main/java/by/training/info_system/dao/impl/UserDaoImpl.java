package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.UserDao;
import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.util.PasswordHasher;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserDaoImpl extends AbstractDao implements UserDao {

    public boolean create(final User entity) {
        return false;
    }

    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional<List<User>> getAll() {
        String sql = "SELECT login, role, fname, lname, address, serie, number,"
                + " id_number, issue_date, end_date FROM Users "
                + "JOIN User_data ON Users.id=User_data.user_id "
                + "JOIN Passport ON User_data.passport_id=Passport.id";
        List<User> users = new ArrayList<>();
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Passport passport = Passport.builder()
                        .serie(resultSet.getString("serie"))
                        .number(resultSet.getInt("number"))
                        .idNumber(resultSet.getString("id_number"))
                        .issueDate(resultSet.getDate("issue_date"))
                        .endDate(resultSet.getDate("end_date"))
                        .build();
                UserData userData = UserData.builder()
                        .fName(resultSet.getString("fname"))
                        .lName(resultSet.getString("lname"))
                        .address(resultSet.getString("address"))
                        .passport(passport)
                        .build();
                Role role = Role.fromValue(resultSet.getInt("role"));
                User user = User.builder()
                        .login(resultSet.getString("login"))
                        .role(role)
                        .userData(userData)
                        .build();
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return Optional.empty();
    }

    public Optional<User> findUserToSignIn(final String email) {
        String sql = "SELECT login, password, role FROM Users WHERE login = ?;";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            log.error("Cannot set argument in prepared statement.", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            Role role = Role.fromValue(resultSet.getInt("role"));
            User user = User.builder()
                    .role(role)
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .build();
            return Optional.of(user);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return Optional.empty();
    }

    public boolean update(final long id, final User newEntity) {
        return false;
    }

    public boolean update(final User user, final User newEntity) {
        return false;
    }

    public boolean delete(final long id) {
        return false;
    }

    public boolean delete(final User entity) {
        return false;
    }
}

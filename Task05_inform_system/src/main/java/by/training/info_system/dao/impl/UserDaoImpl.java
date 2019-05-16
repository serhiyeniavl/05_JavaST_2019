package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.UserDao;
import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
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

    public Integer create(final User entity) {
        String sql1 = "INSERT INTO Users (`id`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO Passport (`id`, `serie`, `number`, `id_number`, `issue_date`, `end_date`)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO User_data (`user_id`, `fname`, `lname`, `passport_id`, `address`)" +
                " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement1 = createPreparedStatement(sql1,
                Statement.RETURN_GENERATED_KEYS);
        PreparedStatement statement2 = createPreparedStatement(sql2);
        PreparedStatement statement3 = createPreparedStatement(sql3);

        Integer userId = 0;

        try {
            int lastUserId = getLastPosition("Users", "id");
            if (lastUserId == -1) {
                throw new SQLException("Cannot count all users");
            }
            statement1.setInt(1, lastUserId);
            statement1.setString(2, entity.getEmail());
            statement1.setString(3, entity.getPassword());
            statement1.setInt(4, entity.getRole().value());

            int lastPassportId = getLastPosition("Passport", "id");
            if (lastPassportId == -1) {
                throw new SQLException("Error when try to calc last id");
            }
            statement2.setInt(1, lastPassportId);
            statement2.setString(2, entity.getUserData()
                    .getPassport().getSerie());
            statement2.setInt(3, entity.getUserData()
                    .getPassport().getNumber());
            statement2.setString(4, entity.getUserData()
                    .getPassport().getIdNumber());
            statement2.setDate(5, java.sql.Date.valueOf(
                    entity.getUserData().getPassport().getIssueDate()));
            statement2.setDate(6, java.sql.Date.valueOf(
                    entity.getUserData().getPassport().getEndDate()));

            statement3.setInt(1, lastUserId);
            statement3.setString(2, entity.getUserData()
                    .getFName());
            statement3.setString(3, entity.getUserData()
                    .getLName());
            statement3.setInt(4, lastPassportId);
            statement3.setString(5, entity.getUserData()
                    .getAddress());

            int query1 = statement1.executeUpdate();
            if (query1 < 1) {
                throw new SQLException("Cannot execute insertion into users db");
            } else {
                ResultSet resultSet = statement1.getGeneratedKeys();
                resultSet.next();
                userId = resultSet.getInt(1);
                resultSet.close();
            }
            int query2 = statement3.executeUpdate();
            if (query2 < 1) {
                throw new SQLException("Cannot execute insertion into users_data db");
            }
            int query3 = statement2.executeUpdate();
            if (query3 < 1) {
                throw new SQLException("Cannot execute insertion into passport db");
            }
        } catch (SQLException e) {
            log.error("Cannot create a prepared statement", e);
            return userId;
        } finally {
            closePreparedStatement(statement1);
            closePreparedStatement(statement2);
            closePreparedStatement(statement3);
        }
        return userId;
    }

    public Optional<User> get(final long id) {
        String sql = "SELECT email, password, role, fname, lname, address, serie, number, "
                + "id_number, issue_date, end_date FROM Passport "
                + "JOIN User_data Ud on Passport.id = Ud.passport_id "
                + "JOIN Users U on Ud.user_id = U.id WHERE U.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, String.valueOf(id));
        } catch (SQLException e) {
            log.error("Cannot set argument in prepared statement.", e);
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                User user = createUser(resultSet);
                user.setPassword(resultSet.getString("password"));
                user.setId(id);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(final long id, final String password) {
        String sql = "UPDATE Users SET password = ? WHERE id = ?";
        return update(sql, password, id);
    }

    @Override
    public boolean updateEmail(final long id, final String email) {
        String sql = "UPDATE Users SET email = ? WHERE id = ?";
        return update(sql, email, id);
    }

    private boolean update(final String sql, final String param1,
                           final long param2) {
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, param1);
            statement.setLong(2, param2);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement.", e);
        }
        try {
            int query = statement.executeUpdate();
            if (query < 1) {
                throw new SQLException("Cannot update password");
            }
            return true;
        } catch (SQLException e) {
            log.error("Error when trying to update password", e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.empty();
    }

    public Optional<List<User>> getAll(final int pageNum,
                                       final int recordsPerPage) {
        String sql = String.format("SELECT Users.id, email, role, fname, lname, address, serie, number,"
                + " id_number, issue_date, end_date FROM Users "
                + "JOIN User_data ON Users.id=User_data.user_id "
                + "JOIN Passport ON User_data.passport_id=Passport.id "
                + "LIMIT %s OFFSET %s", recordsPerPage, (pageNum - 1) * recordsPerPage);
        return Optional.of(getAll(sql));
    }

    @Override
    public Integer countUsers() {
        String sql = "SELECT COUNT(id) FROM Users WHERE role != 3";
        return count(sql);
    }

    @Override
    public Integer countUsersInBlackList() {
        String sql = "SELECT COUNT(id) FROM Black_list";
        return count(sql);
    }

    @Override
    public Integer countManagers() {
        String sql = "SELECT COUNT(id) FROM Users WHERE role = 2";
        return count(sql);
    }

    @Override
    public Integer countCustomers() {
        String sql = "SELECT COUNT(id) FROM Users WHERE role = 1";
        return count(sql);
    }

    private Integer count(final String sql) {
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

    public boolean update(final User entity) {
        return false;
    }

    @Override
    public boolean update(final long id, final Role role) {
        String sql = "UPDATE Users SET role = ? WHERE id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setInt(1, role.value());
            statement.setLong(2, id);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try {
            int query = statement.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            log.error("Error when tyring to update role", e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    public boolean delete(final long id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        return delete(sql, id);
    }

    @Override
    public boolean deleteFromBlackList(final long id) {
        String sql = "DELETE FROM Black_list WHERE user_id = ?";
        return delete(sql, id);
    }

    public Optional<User> read(final String email) {
        String sql = "SELECT id, email, password, role, fname"
                + " FROM Users "
                + "JOIN User_data ON Users.id=User_data.user_id "
                + "WHERE email = ?;";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            log.error("Cannot set argument in prepared statement.", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return Optional.empty();
            }
            UserData userData = UserData.builder()
                    .fName(resultSet.getString("fname"))
                    .build();
            Role role = Role.fromValue(resultSet.getInt("role"));
            User user = User.builder()
                    .email(resultSet.getString("email"))
                    .role(role)
                    .password(resultSet.getString("password"))
                    .userData(userData)
                    .build();
            user.setId(resultSet.getLong("id"));
            return Optional.of(user);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> readFullInfo(final String email) {
        String sql = "SELECT U.id, email, password, role, fname, lname, address, serie, number, "
                + "id_number, issue_date, end_date FROM Passport "
                + "JOIN User_data Ud on Passport.id = Ud.passport_id "
                + "JOIN Users U on Ud.user_id = U.id WHERE U.email = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            log.error("Cannot set argument in prepared statement.", e);
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                User user = createUser(resultSet);
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getLong("id"));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<BlackListNode>> readBlackList() {
        String sql = "SELECT reason, lock_date, unlock_date," +
                " id, email, role, fname, lname FROM Black_list " +
                "JOIN Users U on Black_list.user_id = U.id " +
                "JOIN User_data Ud on U.id = Ud.user_id";
        return readBlackList(sql);
    }

    private Optional<List<BlackListNode>> readBlackList(final String sql) {
        List<BlackListNode> usersBlackList = new ArrayList<>();
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = User.builder()
                        .email(resultSet.getString("email"))
                        .role(Role.fromValue(resultSet.getInt("role")))
                        .userData(
                                UserData.builder()
                                        .fName(resultSet.getString("fname"))
                                        .lName(resultSet.getString("lname"))
                                        .build()
                        )
                        .build();
                user.setId((long) resultSet.getInt("id"));
                BlackListNode node = BlackListNode.builder()
                        .user(user)
                        .reason(resultSet.getString("reason"))
                        .lockDate(resultSet.getDate("lock_date").toLocalDate())
                        .unlockDate(resultSet.getDate("unlock_date").toLocalDate())
                        .build();
                usersBlackList.add(node);
            }
            return Optional.of(usersBlackList);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<BlackListNode>> readBlackList(final int page,
                                                       final int recordsPerPage) {
        String sql = String.format("SELECT reason, lock_date, unlock_date," +
                " id, email, role, fname, lname FROM Black_list " +
                "JOIN Users U on Black_list.user_id = U.id " +
                "JOIN User_data Ud on U.id = Ud.user_id "
                + "LIMIT %s OFFSET %s", recordsPerPage, (page - 1) * recordsPerPage);
        return readBlackList(sql);
    }

    @Override
    public Optional<User> findByPassportNumber(final Integer number,
                                               final String idNumber) {
        String sql = "SELECT email, role, fname, lname, address, serie, number,"
                + " id_number, issue_date, end_date FROM Users "
                + "JOIN User_data ON Users.id=User_data.user_id "
                + "JOIN Passport ON User_data.passport_id=Passport.id " +
                "WHERE number = ? OR id_number = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setInt(1, number);
            statement.setString(2, idNumber);
        } catch (SQLException e) {
            log.error("Cannot set argument in prepared statement.", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(createUser(resultSet));
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closePreparedStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAllWithDiscount() {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findManagers(final int page,
                                             final int recordsPerPage) {
        String sql = String.format("SELECT Users.id, email, role, fname, lname, address, serie, number,"
                        + " id_number, issue_date, end_date FROM Users "
                        + "JOIN User_data ON Users.id=User_data.user_id "
                        + "JOIN Passport ON User_data.passport_id=Passport.id "
                        + "WHERE role = 2 LIMIT %s OFFSET %s", recordsPerPage,
                (page - 1) * recordsPerPage);
        return Optional.of(getAll(sql));
    }

    @Override
    public Optional<List<User>> findCustomers(final int page,
                                              final int recordsPerPage) {
        String sql = String.format("SELECT Users.id, email, role, fname, lname, address, serie, number,"
                        + " id_number, issue_date, end_date FROM Users "
                        + "JOIN User_data ON Users.id=User_data.user_id "
                        + "JOIN Passport ON User_data.passport_id=Passport.id "
                        + "WHERE role = 1 LIMIT %s OFFSET %s", recordsPerPage,
                (page - 1) * recordsPerPage);
        return Optional.of(getAll(sql));
    }

    private List<User> getAll(final String sql) {
        List<User> users = new ArrayList<>();
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = createUser(resultSet);
                user.setId(resultSet.getLong("Users.id"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return new ArrayList<>();
    }

    private boolean delete(final String sql, final long id) {
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        try {
            int query = statement.executeUpdate();
            return query > 0;
        } catch (SQLException e) {
            log.error("Error when tyring to delete user", e);
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    private User createUser(final ResultSet resultSet) {
        try {
            Passport passport = Passport.builder()
                    .serie(resultSet.getString("serie"))
                    .number(resultSet.getInt("number"))
                    .idNumber(resultSet.getString("id_number"))
                    .issueDate(resultSet.getDate("issue_date").toLocalDate())
                    .endDate(resultSet.getDate("end_date").toLocalDate())
                    .build();
            UserData userData = UserData.builder()
                    .fName(resultSet.getString("fname"))
                    .lName(resultSet.getString("lname"))
                    .address(resultSet.getString("address"))
                    .passport(passport)
                    .build();
            Role role = Role.fromValue(resultSet.getInt("role"));
            return User.builder()
                    .email(resultSet.getString("email"))
                    .role(role)
                    .userData(userData)
                    .build();
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        }
        return null;
    }

    private int getLastPosition(final String tableName, final String colName) {
        String sql = String.format("SELECT COUNT(%s) FROM %s", colName, tableName);
        int last = -1;
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.next();
            last = resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return last;
    }
}

package by.training.info_system.dao.helper;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class DaoHelper {
    public void close(final Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close statement", e);
        }
    }

    public void close(final Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close connection", e);
        }
    }

    public void close(final ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close result set", e);
        }
    }
}

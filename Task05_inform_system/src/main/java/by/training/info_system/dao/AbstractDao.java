package by.training.info_system.dao;

import by.training.info_system.dao.connection_pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public abstract class AbstractDao {
    protected static final String RESULT_SET_ERROR = "Result set error: ";
    protected static final String METHOD_DOESNT_SUPPORT
            = "This method doesn't support";

    private Connection connection;


    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    protected void returnConnectionInPool() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Cannot return connection in pool", e);
        }
    }

    protected Statement createStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.error("Error when try to create statement", e);
        }
        return statement;
    }

    protected PreparedStatement createPreparedStatement(final String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.error("Error when try to create prepare statement", e);
        }
        return statement;
    }

    protected PreparedStatement createPreparedStatement(final String sql,
                                                        final int autoGenKeys) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, autoGenKeys);
        } catch (SQLException e) {
            log.error("Error when try to create prepare statement", e);
        }
        return statement;
    }

    protected void closeStatement(final Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close statement", e);
        }
    }

    protected void closePreparedStatement(final PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close prepared statement", e);
        }
    }
}


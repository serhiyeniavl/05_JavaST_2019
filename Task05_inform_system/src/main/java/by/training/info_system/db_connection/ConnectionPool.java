package by.training.info_system.db_connection;

import by.training.info_system.resource.DBConfigurationProvider;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


@Log4j2
public class ConnectionPool {

    private static BasicDataSource dataSource = new BasicDataSource();
    private static ConnectionPool instance;


    private ConnectionPool() {
            setProp();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Error when try to get connection.", e);
        }
        return connection;
    }

    private void setProp() {
//        dataSource.setUrl(DBConfigurationProvider.getProperty("db.url"));
//        dataSource.setUsername(DBConfigurationProvider.getProperty("db.manual_user"));
//        dataSource.setPassword(DBConfigurationProvider.getProperty("db.manual_user_pass"));
        dataSource.setDriverClassName(DBConfigurationProvider.dbDriver);
        dataSource.setUrl(DBConfigurationProvider.dbUrl);
        dataSource.setUsername(DBConfigurationProvider.dbUser);
        dataSource.setPassword(DBConfigurationProvider.dbPassword);
        dataSource.setMinIdle(5);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWaitMillis(10000);
    }

}

package by.training.info_system.db_connection;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Log4j2
public class ConnectionPool {
    private Properties properties;

    private static BasicDataSource dataSource = new BasicDataSource();
    private static ConnectionPool instance;


    private ConnectionPool() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(
                    "src/main/resources/db_connection.properties"));
            setProp();
        } catch (FileNotFoundException e) {
            log.error("Error reading properties file", e);
        } catch (IOException e) {
            log.error("Load property error", e);
        }
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
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.manual_user"));
        dataSource.setPassword(properties.getProperty("db.manual_user_pass"));
        dataSource.setMinIdle(5);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWaitMillis(10000);
    }

}

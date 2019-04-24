package by.training.info_system.resource;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class DBConfigurationProvider {
    private static Properties dbProp;
    private static DBConfigurationProvider instance;


    private DBConfigurationProvider() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("db_connection.properties");
        try {
            dbProp.load(inputStream);
        } catch (IOException e) {
            log.error("Error loading property file.", e);
        }
    }

    public static DBConfigurationProvider getInstance() {
        if (instance == null) {
            dbProp = new Properties();
            instance = new DBConfigurationProvider();
        }
        return instance;
    }

    public String getProperty(final String key) {
        return dbProp.getProperty(key);
    }
}

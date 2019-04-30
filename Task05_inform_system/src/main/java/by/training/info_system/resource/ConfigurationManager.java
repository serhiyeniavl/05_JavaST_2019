package by.training.info_system.resource;

import by.training.info_system.resource.path.URIPath;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigurationManager {
    private static Properties properties;
    private static ConfigurationManager instance;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            properties = new Properties();
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private ConfigurationManager() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("app_config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Error loading property file.", e);
        }
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public String getPagePath(final String uri) {
        return URIPath.valueOf(uri.toUpperCase()).getPath();
    }
}

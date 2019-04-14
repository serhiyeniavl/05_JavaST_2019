package by.training.info_system.resource;

import java.util.ResourceBundle;

public class DBConfigurationProvider {
//    private static final ResourceBundle resource = ResourceBundle.getBundle("resources/db_connection");
//
//    private DBConfigurationProvider() {
//    }
//
//    public static String getProperty(final String key) {
//        return resource.getString(key);
//    }

    public static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    public static final String dbUser = "manual_user";
    public static final String dbPassword = "password";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/car_rent_system_db?useUnicode=true&characterEncoding=UTF8&useSSL=false";
}

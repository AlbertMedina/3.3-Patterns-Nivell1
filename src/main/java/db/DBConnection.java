package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection instance;

    private static final String PROPERTIES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "db.properties";

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    public DBConnection(){
        try {
            Properties properties = loadProperties(PROPERTIES_FILE_PATH);
            String dbName = properties.getProperty("name");
            String dbPort = properties.getProperty("port");
            dbUrl = "jdbc:mysql://localhost:" + dbPort + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
            dbUser = properties.getProperty("user");
            dbPassword = properties.getProperty("password");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}

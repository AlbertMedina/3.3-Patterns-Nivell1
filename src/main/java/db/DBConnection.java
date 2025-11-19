package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            Properties properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(input);

            String dbName = properties.getProperty("name");
            String dbPort = properties.getProperty("port");
            String dbUser = properties.getProperty("user");
            String dbPassword = properties.getProperty("password");

            String url = "jdbc:mysql://localhost:" + dbPort + "/" + dbName + "?useSSL=false&serverTimezone=UTC";

            this.connection = DriverManager.getConnection(url, dbUser, dbPassword);

        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar conexión a la BD", e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


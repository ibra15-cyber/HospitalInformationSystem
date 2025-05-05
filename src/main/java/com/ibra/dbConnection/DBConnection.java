package com.ibra.dbConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String CONFIG_FILE = "config.properties";
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static String url;
    private static String username;
    private static String password;
    private static Connection connection = null;

    static {
        try {
            loadConfig();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static void loadConfig() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            logger.error("Database driver not found" + e.getMessage());
            throw new SQLException("Database driver not found", e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
            logger.error("Error closing database connection: " + e.getMessage());
        }
    }
}

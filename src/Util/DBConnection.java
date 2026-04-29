package Util;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/config.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            Connection com = DriverManager.getConnection(url, user, password);
            return com;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("config.properties file not found!", e);
        }
    }
}

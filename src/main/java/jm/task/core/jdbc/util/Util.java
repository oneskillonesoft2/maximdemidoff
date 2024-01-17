package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединениние успешно...");
        } catch (SQLException e) {
            System.out.println("Соединениние не успешно...");
            throw new RuntimeException(e);

        }

        return conn;
    }
}

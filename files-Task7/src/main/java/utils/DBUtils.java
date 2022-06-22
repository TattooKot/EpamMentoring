package utils;

import java.sql.*;

public class DBUtils {
    private static final String URL = "jdbc:postgresql://localhost/task7";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";


    public PreparedStatement getStatement(String sql){
        try {
            return getConnection().prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw  new RuntimeException();
    }

    private Connection getConnection() {
        try {
            return  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
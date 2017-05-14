package JDBC;

import java.sql.*;

public class JDBC {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/FIRMA?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "kuba";

    private static JDBC instance;
    private Connection connection;

    public void JDBC() {

    }

    public static JDBC getInstance() {
        if (instance == null) {
            instance = new JDBC();
        }
        return instance;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        instance.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return instance.connection;
    }
}
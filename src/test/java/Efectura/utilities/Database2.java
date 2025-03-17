package Efectura.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database2 {

    private static Database2 instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:clickhouse://192.168.129.7:8180/default?compress=0";
//    private final String DB_URL = "jdbc:clickhouse://192.168.129.7:8180/my_database";
    private final String DB_USERNAME = "efectura";
    private final String DB_PASSWORD = "6KQlSamV4D2x7T9179STCK";

    private Database2() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database2();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instance.getConnection();
    }

}

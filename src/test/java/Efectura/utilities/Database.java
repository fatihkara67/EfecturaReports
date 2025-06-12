package Efectura.utilities;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance = null;
    @Getter
    private Connection connection = null;
    private final String DB_URL = "jdbc:sqlserver://10.15.16.152:1433;databaseName=DEV_CCI;encrypt=true;trustServerCertificate=true;";
    //    private final String DB_URL = "jdbc:clickhouse://192.168.129.7:8180/my_database";
    private final String DB_USERNAME = "dev_hero";
    private final String DB_PASSWORD = "6KQlSamV4D2x7T9179STCK";

    private Database() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instance.getConnection();
    }

}

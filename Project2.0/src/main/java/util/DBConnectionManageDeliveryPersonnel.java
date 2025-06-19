package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManageDeliveryPersonnel {

    private static final String URL = "jdbc:mysql://localhost:3306/project2.0";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }


}

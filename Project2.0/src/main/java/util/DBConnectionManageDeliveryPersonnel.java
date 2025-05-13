package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManageDeliveryPersonnel {

    private static final String URL = "jdbc:mysql://localhost:3306/project2.0";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //The returned connection object represent the connection with the database
    public static Connection getConnection() {

        try {
            //DriverManager is a class in JDBC API to manage database drivers and establishes a connection to the database.
            //Establish the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {//If an error occur when connecting with the database the exception is handled by the catch block

            //This prints the error details to the console, which helps with debugging.
            e.printStackTrace();
            return null;

        }

    }


}

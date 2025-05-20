package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleModel {
    private final String DB_URL = "jdbc:mysql://localhost:3306/project2.0";
    private final String USER = "root";
    private final String PASS = "";

    //Insert query
    public void insertSchedule(String scheduleId, String scheduleDate, String deliveryPersonnelId, String parcelId) throws SQLException {
        String query = "INSERT INTO delivery_schedule (shedule_id, schedule_date, delivery_personnel_id, parcel_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, scheduleId);
            stmt.setString(2, scheduleDate);
            stmt.setString(3, deliveryPersonnelId);
            stmt.setString(4, parcelId);

            stmt.executeUpdate();
        }
    }

    //Update query
    public void updateSchedule(String scheduleId, String newDate) throws SQLException {
        String query = "UPDATE delivery_schedule SET schedule_date = ? WHERE shedule_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newDate);
            stmt.setString(2, scheduleId);

            stmt.executeUpdate();
        }
    }

}

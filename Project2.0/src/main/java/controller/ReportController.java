package controller;

import model.ReportEntry;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportController {

    /**
     * Fetches all report entries without filtering.
     */
    public List<ReportEntry> fetchReport() {
        List<ReportEntry> reportList = new ArrayList<>();

        String query = "SELECT p.parcel_id, p.parcel_type, c.email AS senderemail, c.name AS sendername, " +
                "r.email AS receiveremail, r.name AS receivername, dp.name AS delivery_personnel, ds.schedule_date " +
                "FROM parcel p " +
                "JOIN customer c ON p.sender_id = c.id " +
                "JOIN receiver r ON p.receiver_id = r.id " +
                "JOIN delivery_schedule ds ON p.parcel_id = ds.parcel_id " +
                "JOIN delivery_personnel dp ON ds.delivery_personnel_id = dp.id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ReportEntry entry = mapRowToEntry(rs);
                reportList.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    /**
     * Fetches report entries filtered by month name (e.g., "JANUARY").
     */
    public List<ReportEntry> fetchReportByMonth(String monthName) {
        List<ReportEntry> reportList = new ArrayList<>();

        String query = "SELECT p.parcel_id, p.parcel_type, c.email AS senderemail, c.name AS sendername, " +
                "r.email AS receiveremail, r.name AS receivername, dp.name AS delivery_personnel, ds.schedule_date " +
                "FROM parcel p " +
                "JOIN customer c ON p.sender_id = c.id " +
                "JOIN receiver r ON p.receiver_id = r.id " +
                "JOIN delivery_schedule ds ON p.parcel_id = ds.parcel_id " +
                "JOIN delivery_personnel dp ON ds.delivery_personnel_id = dp.id " +
                "WHERE MONTHNAME(ds.schedule_date) = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, monthName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReportEntry entry = mapRowToEntry(rs);
                    reportList.add(entry);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    /**
     * Helper to map a ResultSet row to a ReportEntry.
     */
    private ReportEntry mapRowToEntry(ResultSet rs) throws SQLException {
        ReportEntry entry = new ReportEntry();
        entry.setParcelId(rs.getString("parcel_id"));
        entry.setParcelType(rs.getString("parcel_type"));
        entry.setSenderEmail(rs.getString("senderemail"));
        entry.setSenderName(rs.getString("sendername"));
        entry.setReceiverEmail(rs.getString("receiveremail"));
        entry.setReceiverName(rs.getString("receivername"));
        entry.setDeliveryPersonnel(rs.getString("delivery_personnel"));
        entry.setScheduleDate(rs.getString("schedule_date"));
        return entry;
    }
}

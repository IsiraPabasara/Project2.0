package model;

import util.DBConnectionManageDeliveryPersonnel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Middleman between logic(Model) and the database
//Isolates all the code that interacts with the database
public class DeliveryPersonnelDAO {

    // Add Personnel
    public void addPersonnel(DeliveryPersonnel p) {

        String sql = "INSERT INTO delivery_personnel (delivery_personnel_id,email,name,phone_number,availability_status) VALUES (?,?, ?, ?, ?)";

        //Java try-with-resources block that handles inserting data into a database using JDBC
        //Auto-close resources (No need to close after executing()) -> Connection,PrepareSStatement
        try (Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();//Open the connection to the database from DBConnection class / conn object has a representation to the database

             PreparedStatement stmt = conn.prepareStatement(sql)) {//Prevent the SQL injection too

            stmt.setString(1, p.getDelivery_personnel_id());
            stmt.setString(2,p.getEmail());
            stmt.setString(3, p.getName());
            stmt.setString(4, p.getPhone_number());
            stmt.setString(5 , p.getAvailability_status());

            stmt.executeUpdate();//When er execute an SELECT query we should use stmt.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Get All Personnel
    public List<DeliveryPersonnel> getAllPersonnel() {//returns a List of DeliveryPersonnel objects -> fetch all delivery personnel records from your database and return them as a list.

        //Creates an empty ArrayList to hold the delivery personnel records you’ll fetch from the database.
        List<DeliveryPersonnel> list = new ArrayList<>();

        String sql = "SELECT * FROM delivery_personnel";

        try (Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();//Establish the connection to database
             Statement stmt = conn.createStatement();//Give the connection to stmt object and create the SQL statement object that is needed to send the query to the database
             ResultSet rs = stmt.executeQuery(sql)) {//Virtual table to store the query result

            //Loop through the result set
            while (rs.next()) {//Iterate each row in the table one at a time

                DeliveryPersonnel p = new DeliveryPersonnel();

                p.setDelivery_personnel_id(rs.getString("delivery_personnel_id"));
                p.setEmail(rs.getString("email"));
                p.setName(rs.getString("name"));
                p.setPhone_number(rs.getString("phone_number"));
                p.setAvailability_status(rs.getString("availability_status"));

                list.add(p);//Add the populated DeliveryPersonnel object 'p' to the list

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }


    // Update Personnel

    public void updatePersonnel(DeliveryPersonnel p) {//'p' contains the updated information of the 1 delivery personnel including the id

        String sql = "UPDATE delivery_personnel SET  delivery_personnel_id =?,email = ?,name = ?, phone_number = ?,availability_status = ? WHERE delivery_personnel_id = ?";

        //try-with-resources ensures automatic closing of the connection and statement.
        try (Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1 , p.getDelivery_personnel_id());
            stmt.setString(2,p.getEmail());
            stmt.setString(3 , p.getName());
            stmt.setString(4, p.getPhone_number());
            stmt.setString(5,p.getAvailability_status());
            stmt.setString(6,p.getDelivery_personnel_id());

            stmt.executeUpdate();//Since it is a data-changing query it will return the no. of rows affected

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Delete Personnel

    public void deletePersonnel(String delivery_personnel_id) {

        String sql = "DELETE FROM delivery_personnel WHERE delivery_personnel_id = ?";

        try (Connection conn = DBConnectionManageDeliveryPersonnel.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,delivery_personnel_id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}

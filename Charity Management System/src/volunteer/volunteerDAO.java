package volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DbConnection;
import donor.donorException.InvalidEmailFormatException;
import donor.donorException.InvalidPhoneNumberException;
import volunteer.volunteerException.DuplicateVolunteerIdException;

public class volunteerDAO {

    // Fetch all volunteers
    public List<volunteer> getAllItemTypes() {
        List<volunteer> volunteers = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM volunter");

            while (resultSet.next()) {
                volunteers.add(new volunteer(resultSet.getInt(1), resultSet.getString(2), 
                                             resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return volunteers;
    }

    // Add a new volunteer
    public boolean addvolunteer(volunteer it) throws DuplicateVolunteerIdException, InvalidEmailFormatException, InvalidPhoneNumberException {
        try {
            Connection con = DbConnection.getConnection();
            
            // Check if volunteer ID already exists
            String checkSQL = "SELECT vol_id FROM volunter WHERE vol_id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setInt(1, it.getvol_id());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new DuplicateVolunteerIdException("Volunteer ID already exists: " + it.getvol_id());
            }

            // Validate email format
            if (!it.getemail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new InvalidEmailFormatException("Invalid email format: " + it.getemail());
            }

            // Validate phone number (10 digits)
            if (!it.getph_numbber().matches("\\d{10}")) {
                throw new InvalidPhoneNumberException("Invalid phone number: " + it.getph_numbber() + ". Phone number must be 10 digits.");
            }

            // Insert new volunteer
            String insertSQL = "INSERT INTO volunter (vol_id, vol_name, email, ph_numbber, organization_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, it.getvol_id());
            preparedStatement.setString(2, it.getvol_name());
            preparedStatement.setString(3, it.getemail());
            preparedStatement.setString(4, it.getph_numbber());
            preparedStatement.setInt(5, it.grtorganization_id());

            preparedStatement.executeUpdate();
            return true; 
        } catch (DuplicateVolunteerIdException | InvalidEmailFormatException | InvalidPhoneNumberException e) {
            throw e; // Throw custom exceptions
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update volunteer details
    public boolean updatevolunteer(volunteer it) {
        try {
            Connection con = DbConnection.getConnection();
            
            // Update volunteer record
            String updateSQL = "UPDATE volunter SET vol_name = ?, email = ?, ph_numbber = ?, organization_id = ? WHERE vol_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, it.getvol_name());
            preparedStatement.setString(2, it.getemail());
            preparedStatement.setString(3, it.getph_numbber());
            preparedStatement.setInt(4, it.grtorganization_id());
            preparedStatement.setInt(5, it.getvol_id());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a volunteer by ID
    public boolean deletevolunteer(int vol_id) {
        try {
            Connection con = DbConnection.getConnection();
            
            // Delete volunteer record
            String deleteSQL = "DELETE FROM volunter WHERE vol_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, vol_id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if volunteer ID exists
    public boolean isVolunteerIdExists(int vol_id) {
        try {
            Connection con = DbConnection.getConnection();
            String query = "SELECT vol_id FROM volunter WHERE vol_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, vol_id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

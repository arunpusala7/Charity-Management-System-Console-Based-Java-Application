package donor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DbConnection;
import donor.donorException.DuplicateDonorIdException;
import donor.donorException.InvalidEmailFormatException;
import donor.donorException.InvalidPhoneNumberException;

public class donorDAO {

    // Retrieve all donors (READ operation)
    public List<donor> getAllItemTypes() {
        List<donor> donorList = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM donor");
            while (resultSet.next()) {
                donorList.add(new donor(resultSet.getInt(1), resultSet.getString(2), 
                                        resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donorList;
    }

    // Insert a new donor (CREATE operation)
    public boolean adddonor(donor it) throws DuplicateDonorIdException, InvalidPhoneNumberException, InvalidEmailFormatException {
        try {
            Connection con = DbConnection.getConnection();

            // Check for duplicate donor ID
            String checkSQL = "SELECT COUNT(*) FROM donor WHERE donor_id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setInt(1, it.getdonor_id());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new DuplicateDonorIdException("Donor ID " + it.getdonor_id() + " already exists.");
            }

            // Validate phone number
            if (it.getphone_number().length() != 10 || !it.getphone_number().matches("\\d+")) {
                throw new InvalidPhoneNumberException("Phone number must be exactly 10 digits.");
            }

            // Validate email format
            if (!it.getemail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new InvalidEmailFormatException("Invalid email format.");
            }

            // Insert donor into the database
            String insertSQL = "INSERT INTO donor (donor_id, donor_name, email, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, it.getdonor_id());
            preparedStatement.setString(2, it.getdonor_name());
            preparedStatement.setString(3, it.getemail());
            preparedStatement.setString(4, it.getphone_number());

            preparedStatement.executeUpdate();
            return true;
        } catch (DuplicateDonorIdException | InvalidPhoneNumberException | InvalidEmailFormatException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a donor (DELETE operation)
    public boolean deleteDonor(int donor_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM donor WHERE donor_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, donor_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing donor (UPDATE operation)
    public boolean updateDonor(donor it) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE donor SET donor_name = ?, email = ?, phone_number = ? WHERE donor_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, it.getdonor_name());
            preparedStatement.setString(2, it.getemail());
            preparedStatement.setString(3, it.getphone_number());
            preparedStatement.setInt(4, it.getdonor_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

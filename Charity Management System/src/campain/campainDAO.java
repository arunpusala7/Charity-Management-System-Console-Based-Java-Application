package campain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import campain.campainException.DuplicateCampaignIdException;
import campain.campainException.InvalidDateFormatException;
import config.DbConnection;

public class campainDAO {

    // Retrieve all campaigns (READ operation)
    public List<campain> getAllItemTypes() {
        List<campain> campainList = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM campagin");
            while (resultSet.next()) {
                campainList.add(new campain(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campainList;
    }

    // Insert a new campaign (CREATE operation)
    public boolean addcampain(campain it) throws DuplicateCampaignIdException, InvalidDateFormatException {
        try {
            Connection con = DbConnection.getConnection();
            // Check for duplicate campaign ID
            String checkSQL = "SELECT COUNT(*) FROM campagin WHERE campaign_id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setInt(1, it.getcampaign_id());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new DuplicateCampaignIdException("Campaign ID " + it.getcampaign_id() + " already exists.");
            }

            // Validate date formats
            validateDate(it.getstart_date(), "Start Date");
            validateDate(it.getend_date(), "End Date");

            String insertSQL = "INSERT INTO campagin (campaign_id, title, start_date, end_date, description_campaign, organization_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, it.getcampaign_id());
            preparedStatement.setString(2, it.gettitle());
            preparedStatement.setString(3, it.getstart_date());
            preparedStatement.setString(4, it.getend_date());
            preparedStatement.setString(5, it.getdescription_campaign());
            preparedStatement.setInt(6, it.getorganization_id());

            preparedStatement.executeUpdate();
            return true;
        } catch (DuplicateCampaignIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a campaign (DELETE operation)
    public boolean deletecampain(int campaign_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM campagin WHERE campaign_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, campaign_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing campaign (UPDATE operation)
    public boolean updatecampain(campain it) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE campagin SET title = ?, start_date = ?, end_date = ?, description_campaign = ?, organization_id = ? WHERE campaign_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, it.gettitle());
            preparedStatement.setString(2, it.getstart_date());
            preparedStatement.setString(3, it.getend_date());
            preparedStatement.setString(4, it.getdescription_campaign());
            preparedStatement.setInt(5, it.getorganization_id());
            preparedStatement.setInt(6, it.getcampaign_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate date format
    private void validateDate(String date, String fieldName) throws InvalidDateFormatException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);  
        try {
            sdf.parse(date);  
        } catch (Exception e) {
            throw new InvalidDateFormatException("Invalid date format for " + fieldName + ". Expected format: yyyy-MM-dd");
        }
    }
}

package original_c_m_s;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.DbConnection;
import original_c_m_s.DonationException.DonorNotFoundException;
import original_c_m_s.DonationException.InvalidAmountException;
import original_c_m_s.DonationException.InvalidCampaignException;

public class donationDAO {

    // Method to retrieve all donations (Read)
    public List<donation> getAllItemTypes() {
        List<donation> donations = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM donations");
            
            while (resultSet.next()) {
                donations.add(new donation(resultSet.getInt(1), resultSet.getString(2), 
                                            resultSet.getString(3), resultSet.getInt(4), 
                                            resultSet.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donations;
    }

    // Method to add a donation (Create)
    public boolean adddonation(donation it) throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        try {
            Connection con = DbConnection.getConnection();

            // Check if the campaign ID exists
            String checkCampaignSQL = "SELECT COUNT(*) FROM campagin WHERE campaign_id = ?";
            PreparedStatement checkCampaignStmt = con.prepareStatement(checkCampaignSQL);
            checkCampaignStmt.setInt(1, it.getcampaign_id());
            ResultSet campaignRs = checkCampaignStmt.executeQuery();
            campaignRs.next();
            if (campaignRs.getInt(1) == 0) {
                throw new InvalidCampaignException("Invalid campaign ID: " + it.getcampaign_id());
            }

            // Check if donor ID exists
            String checkDonorSQL = "SELECT COUNT(*) FROM donor WHERE donor_id = ?";
            PreparedStatement checkDonorStmt = con.prepareStatement(checkDonorSQL);
            checkDonorStmt.setInt(1, it.getdonor_id());
            ResultSet donorRs = checkDonorStmt.executeQuery();
            donorRs.next();
            if (donorRs.getInt(1) == 0) {
                throw new DonorNotFoundException("Donor ID " + it.getdonor_id() + " not found or invalid.");
            }

            // Check if amount is greater than 0
            if (Integer.parseInt(it.getamount()) <= 0) {
                throw new InvalidAmountException("The amount must be greater than 0.");
            }

            // Insert donation into the database
            String insertSQL = "INSERT INTO donations (donation_id, amount, donation_date, donor_id, campaign_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, it.getdonation_id());
            preparedStatement.setString(2, it.getamount());
            preparedStatement.setString(3, it.getdonation_date());
            preparedStatement.setInt(4, it.getdonor_id());
            preparedStatement.setInt(5, it.getcampaign_id());

            preparedStatement.executeUpdate();
            return true;

        } catch (DonorNotFoundException | InvalidAmountException | InvalidCampaignException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while adding donation.");
            return false;
        }
    }

    // Method to update a donation (Update)
    public boolean updatedonation(donation it) throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        try {
            Connection con = DbConnection.getConnection();

            // Check if the donation exists
            String checkDonationSQL = "SELECT COUNT(*) FROM donations WHERE donation_id = ?";
            PreparedStatement checkDonationStmt = con.prepareStatement(checkDonationSQL);
            checkDonationStmt.setInt(1, it.getdonation_id());
            ResultSet donationRs = checkDonationStmt.executeQuery();
            donationRs.next();
            if (donationRs.getInt(1) == 0) {
                throw new DonorNotFoundException("Donation ID " + it.getdonation_id() + " not found.");
            }

            // Update donation
            String updateSQL = "UPDATE donations SET amount = ?, donation_date = ?, donor_id = ?, campaign_id = ? WHERE donation_id = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateSQL);
            updateStmt.setString(1, it.getamount());
            updateStmt.setString(2, it.getdonation_date());
            updateStmt.setInt(3, it.getdonor_id());
            updateStmt.setInt(4, it.getcampaign_id());
            updateStmt.setInt(5, it.getdonation_id());

            updateStmt.executeUpdate();
            return true;

        } catch (DonorNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a donation (Delete)
    public boolean deletedonation(int donation_id) {
        try {
            Connection con = DbConnection.getConnection();

            // Check if the donation exists
            String checkDonationSQL = "SELECT COUNT(*) FROM donations WHERE donation_id = ?";
            PreparedStatement checkDonationStmt = con.prepareStatement(checkDonationSQL);
            checkDonationStmt.setInt(1, donation_id);
            ResultSet donationRs = checkDonationStmt.executeQuery();
            donationRs.next();
            if (donationRs.getInt(1) == 0) {
                System.out.println("Donation ID " + donation_id + " not found.");
                return false;
            }

            // Delete donation
            String deleteSQL = "DELETE FROM donations WHERE donation_id = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
            deleteStmt.setInt(1, donation_id);
            deleteStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

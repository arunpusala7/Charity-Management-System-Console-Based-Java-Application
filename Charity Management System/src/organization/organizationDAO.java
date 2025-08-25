package organization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.DbConnection;

public class organizationDAO {
    
    public List<organization> getAllItemTypes() {
        List<organization> organizationList = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM organization");
            
            while (resultSet.next()) {
                organizationList.add(new organization(resultSet.getInt(1), resultSet.getString(2), 
                                                      resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizationList;
    }

    public boolean addorganization(organization org) throws organizationException.DuplicateOrganizationIdException {
        try {
            Connection con = DbConnection.getConnection();
            String insertSQL = "INSERT INTO organization (organization_id, name, description, contact) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, org.getorganization_id());
            preparedStatement.setString(2, org.getname());
            preparedStatement.setString(3, org.getdescription());
            preparedStatement.setString(4, org.getcontact());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new organizationException.DuplicateOrganizationIdException("Organization ID already exists: " + org.getorganization_id());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while adding organization.");
            return false;
        }
    }

    public boolean updateorganization(organization org) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE organization SET name = ?, description = ?, contact = ? WHERE organization_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, org.getname());
            preparedStatement.setString(2, org.getdescription());
            preparedStatement.setString(3, org.getcontact());
            preparedStatement.setInt(4, org.getorganization_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while updating organization.");
            return false;
        }
    }

    public boolean deleteorganization(int organization_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM organization WHERE organization_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, organization_id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while deleting organization.");
            return false;
        }
    }
}

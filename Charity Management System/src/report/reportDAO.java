package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DbConnection;
import report.reportException.DuplicateReportIdException;

public class reportDAO {
	
	public List<report> getAllItemTypes() {
        List<report> report = new ArrayList<report>();
        try {
            // Get a connection from the database
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM report");
            
            // Iterate through the result set and add donations to the list
            while (resultSet.next()) {
            	report.add(new report(resultSet.getInt(1), resultSet.getString(2), 
                                            resultSet.getString(3), resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
	
	public boolean addreport(report it) throws DuplicateReportIdException {
		try {
		Connection con = DbConnection.getConnection();
		String insertSQL = "INSERT INTO report (report_id,report_date,title,organization_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
        preparedStatement.setInt(1, it.getreport_id());
        preparedStatement.setString(2, it.gettitle());
        preparedStatement.setString(3, it.getreport_date());
        preparedStatement.setInt(4, it.getorganization_id());

        preparedStatement.executeUpdate();
        return true;

    } catch (SQLIntegrityConstraintViolationException e) {
        // If a duplicate report ID is found, throw the custom exception
        throw new reportException.DuplicateReportIdException("Report ID already exists: " + it.getreport_id());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error occurred while adding the report.");
        return false;
    }

}
	  // Method to check if report ID exists
    public boolean isReportIdExists(int report_id) {
        try {
            Connection con = DbConnection.getConnection();
            String query = "SELECT report_id FROM report WHERE report_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, report_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If result exists, report ID exists
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to check if organization ID exists
    public boolean isOrganizationExists(int organization_id) {
        try {
            Connection con = DbConnection.getConnection();
            String query = "SELECT organization_id FROM organization WHERE organization_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, organization_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If result exists, organization ID exists
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatereport(report it) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE report SET report_date = ?, title = ?, organization_id = ? WHERE report_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, it.getreport_date());
            preparedStatement.setString(2, it.gettitle());
            preparedStatement.setInt(3, it.getorganization_id());
            preparedStatement.setInt(4, it.getreport_id());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;  // If at least one row is updated, return true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deletereport(int report_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM report WHERE report_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, report_id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;  // If at least one row is deleted, return true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}

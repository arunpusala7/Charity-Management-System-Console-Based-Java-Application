package event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DbConnection;
import event.eventException.DuplicateEventIdException;
import event.eventException.InvalidOrganizationException;
import event.eventException.InvalidDateFormatException;

public class eventDAO {

    // CREATE: Add a new event
    public boolean addevent(event evt) throws DuplicateEventIdException, InvalidOrganizationException, InvalidDateFormatException {
        try {
            Connection con = DbConnection.getConnection();
            
            // Check for duplicate event ID
            String checkQuery = "SELECT e_id FROM evennt WHERE e_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setInt(1, evt.gete_id());
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                throw new DuplicateEventIdException("Event ID already exists!");
            }
            
            // Add the new event
            String insertSQL = "INSERT INTO evennt (e_id, e_title, e_description, event_date, organization_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertSQL);
            ps.setInt(1, evt.gete_id());
            ps.setString(2, evt.gete_title());
            ps.setString(3, evt.gete_description());
            ps.setString(4, evt.getevent_date());
            ps.setInt(5, evt.getorganization_id());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ: Get all events
    public List<event> getAllItemTypes() {
        List<event> events = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM evennt");
            
            while (resultSet.next()) {
                events.add(new event(resultSet.getInt(1), resultSet.getString(2), 
                                            resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    // UPDATE: Update an existing event
    public boolean updateEvent(event evt) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE evennt SET e_title = ?, e_description = ?, event_date = ?, organization_id = ? WHERE e_id = ?";
            PreparedStatement ps = con.prepareStatement(updateSQL);
            ps.setString(1, evt.gete_title());
            ps.setString(2, evt.gete_description());
            ps.setString(3, evt.getevent_date());
            ps.setInt(4, evt.getorganization_id());
            ps.setInt(5, evt.gete_id());
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE: Delete an event by ID
    public boolean deleteEvent(int e_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM evennt WHERE e_id = ?";
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setInt(1, e_id);
            
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

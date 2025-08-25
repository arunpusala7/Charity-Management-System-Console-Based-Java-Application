package expenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DbConnection;

public class expensesDAO {

    // CREATE: Add a new expense
    public boolean addexpenses(expenses it) {
        try {
            Connection con = DbConnection.getConnection();
            String insertSQL = "INSERT INTO expenses (exp_id, amount, exp_date, organization_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, it.getexp_id());
            preparedStatement.setString(2, it.getamount());
            preparedStatement.setString(3, it.getexp_date());
            preparedStatement.setInt(4, it.getorganization_id());

            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while adding the expense.");
            return false;
        }
    }

    // READ: Get all expenses
    public List<expenses> getAllItemTypes() {
        List<expenses> expenses = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM expenses");

            while (resultSet.next()) {
                expenses.add(new expenses(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // UPDATE: Update an existing expense
    public boolean updateexpenses(expenses it) {
        try {
            Connection con = DbConnection.getConnection();
            String updateSQL = "UPDATE expenses SET amount = ?, exp_date = ?, organization_id = ? WHERE exp_id = ?";
            PreparedStatement ps = con.prepareStatement(updateSQL);
            ps.setString(1, it.getamount());
            ps.setString(2, it.getexp_date());
            ps.setInt(3, it.getorganization_id());
            ps.setInt(4, it.getexp_id());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE: Delete an expense by ID
    public boolean deleteexpenses(int exp_id) {
        try {
            Connection con = DbConnection.getConnection();
            String deleteSQL = "DELETE FROM expenses WHERE exp_id = ?";
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setInt(1, exp_id);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

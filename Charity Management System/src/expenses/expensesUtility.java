package expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class expensesUtility {
    List<expenses> expenses = new ArrayList<>();

    // Display all expenses
    public void displayexpensesList() {
        expensesDAO dao = new expensesDAO();
        expenses = dao.getAllItemTypes();
        System.out.format("%-15s %-20s %-32s  %s\n", "Expenses Id", "Amount", "Date", "Organization Id");
        for (expenses it : expenses) {
            System.out.format("%-15s %-20s %-40s %s\n", it.getexp_id(), it.getamount(), it.getexp_date(), it.getorganization_id());
        }
    }

    // Add a new expense
    public boolean addexpenses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Expenses ID: ");
        int exp_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Amount: ");
        String amount = sc.nextLine();
        System.out.println("Enter Date: ");
        String exp_date = sc.nextLine();
        System.out.println("Enter Organization Id: ");
        int organization_id = sc.nextInt();

        expenses it = new expenses(exp_id, amount, exp_date, organization_id);
        expenses.add(it);
        expensesDAO d_DAO = new expensesDAO();
        try {
            return d_DAO.addexpenses(it);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing expense
    public boolean updateexpenses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Expense ID to update: ");
        int exp_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Amount: ");
        String amount = sc.nextLine();
        System.out.println("Enter new Date: ");
        String exp_date = sc.nextLine();
        System.out.println("Enter new Organization Id: ");
        int organization_id = sc.nextInt();

        expenses it = new expenses(exp_id, amount, exp_date, organization_id);
        expensesDAO d_DAO = new expensesDAO();
        return d_DAO.updateexpenses(it);
    }

    // Delete an expense by ID
    public boolean deleteexpenses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Expense ID to delete: ");
        int exp_id = sc.nextInt();

        expensesDAO d_DAO = new expensesDAO();
        return d_DAO.deleteexpenses(exp_id);
    }
}

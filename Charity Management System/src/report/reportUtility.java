package report;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class reportUtility {
    List<report> reportList = new ArrayList<>();

    public void displayreportList() {
        reportDAO dao = new reportDAO();
        reportList = dao.getAllItemTypes();
        System.out.format("%-15s %-20s %-32s  %s\n", "report Id", "Date", "Title", " Organization Id");
        for (report it : reportList) {
            System.out.format("%-15s %-20s %-40s %s\n", it.getreport_id(), it.getreport_date(), it.gettitle(), it.getorganization_id());
        }
    }

    public boolean addreport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Report ID: ");
        int report_id = sc.nextInt();
        sc.nextLine();  // Consume newline left-over
        System.out.println("Enter Date: ");
        String report_date = sc.nextLine();
        System.out.println("Enter Report Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Organization Id: ");
        int organization_id = sc.nextInt();

        report newReport = new report(report_id, title, report_date, organization_id);
        reportDAO reportDAO = new reportDAO();
        try {
            // Check if report ID already exists
            if (reportDAO.isReportIdExists(report_id)) {
                throw new reportException.DuplicateReportIdException("Error: Report ID already exists: " + report_id);
            }
            // Check if organization ID exists
            if (!reportDAO.isOrganizationExists(organization_id)) {
                throw new reportException.OrganizationNotFoundException("Error: Organization ID does not exist: " + organization_id);
            }
            return reportDAO.addreport(newReport);
        } catch (reportException.DuplicateReportIdException | reportException.OrganizationNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatereport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Report ID to update: ");
        int report_id = sc.nextInt();
        sc.nextLine();  // Consume newline

        reportDAO reportDAO = new reportDAO();
        if (!reportDAO.isReportIdExists(report_id)) {
            System.out.println("Error: Report ID does not exist.");
            return false;
        }

        System.out.println("Enter new Date: ");
        String report_date = sc.nextLine();
        System.out.println("Enter new Report Title: ");
        String title = sc.nextLine();
        System.out.println("Enter new Organization Id: ");
        int organization_id = sc.nextInt();

        report updatedReport = new report(report_id, title, report_date, organization_id);

        try {
            return reportDAO.updatereport(updatedReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Missing deletereport() method added here
    public boolean deletereport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Report ID to delete: ");
        int report_id = sc.nextInt();

        reportDAO reportDAO = new reportDAO();
        if (!reportDAO.isReportIdExists(report_id)) {
            System.out.println("Error: Report ID does not exist.");
            return false;
        }

        try {
            return reportDAO.deletereport(report_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

package Main_Driver_Class;
import java.util.Scanner;

import campain.campainUtility;
import donor.donorUtility;
import event.eventUtility;
import expenses.expensesUtility;
import organization.organizationUtility;
import original_c_m_s.DonationException.DonorNotFoundException;
import original_c_m_s.DonationException.InvalidAmountException;
import original_c_m_s.DonationException.InvalidCampaignException;
import original_c_m_s.donationUtility;
import report.reportUtility;
import volunteer.volunteerUtility;

public class MainDriver {
    public static void main(String[] args) throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Charity Management System ---");
            System.out.println("1. Donation Management");
            System.out.println("2. Donor Management");
            System.out.println("3. Event Management");
            System.out.println("4. Expenses Management");
            System.out.println("5. Organization Management");
            System.out.println("6. Volunteer Management");
            System.out.println("7. Campaign Management");
            System.out.println("8. Report Management");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runDonationManagement();
                    break;
                case 2:
                    runDonorManagement();
                    break;
                case 3:
                    runEventManagement();
                    break;
                case 4:
                    runExpensesManagement();
                    break;
                case 5:
                    runOrganizationManagement();
                    break;
                case 6:
                    runVolunteerManagement();
                    break;
                case 7:
                    runCampaignManagement();
                    break;
                case 8:
                    runReportManagement();
                    break;
                case 9:
                    System.out.println("Exiting the system.");
                    scanner.close(); 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Call individual management methods
    private static void runDonationManagement() throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        // Your existing Donation Management code here
        donationUtility donationUtil = new donationUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Donation Management System ---");
            
            System.out.println("1. Add a Donation");
            System.out.println("2. Update a Donation");
            System.out.println("3. Delete a Donation");
            System.out.println("4. View All Donations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
               
                case 1:
                    donationUtil.adddonation();
                    break;
                case 2:
                    donationUtil.updatedonation();
                    break;
                case 3:
                    donationUtil.deletedonation();
                    break;
                case 4:
                    donationUtil.displaydonationList();
                    break;
                case 5:
                    System.out.println("Exiting Donation System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runDonorManagement() {
        // Your existing Donor Management code here
        donorUtility donorUtil = new donorUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Donor Management System ---");
            System.out.println("1. Add Donor");
            System.out.println("2. Update Donor");
            System.out.println("3. Delete Donor");
            System.out.println("4. Display Donors");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    donorUtil.adddonor();
                    break;
                case 2:
                    donorUtil.updateDonor();
                    break;
                case 3:
                    donorUtil.deleteDonor();
                    break;
                case 4:
                    donorUtil.displaydonorList();
                    break;
                case 5:
                    System.out.println("Exiting Donor System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runEventManagement() {
        // Your existing Event Management code here
        eventUtility eventUtil = new eventUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Event Management System ---");
           
            System.out.println("1. Add an Event");
            System.out.println("2. Update an Event");
            System.out.println("3. Delete an Event");
            System.out.println("4. View All Events");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                
                case 1:
                    eventUtil.addevent();
                    break;
                case 2:
                    eventUtil.updateEvent();
                    break;
                case 3:
                    eventUtil.deleteEvent();
                    break;
                case 4:
                    eventUtil.displayeventList();
                    break;
                case 5:
                    System.out.println("Exiting Event System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runExpensesManagement() {
        // Your existing Expenses Management code here
        expensesUtility expensesUtil = new expensesUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Expenses Management System ---");
           
            System.out.println("1. Add an Expense");
            System.out.println("2. Update an Expense");
            System.out.println("3. Delete an Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                
                case 1:
                    expensesUtil.addexpenses();
                    break;
                case 2:
                    expensesUtil.updateexpenses();
                    break;
                case 3:
                    expensesUtil.deleteexpenses();
                    break;
                case 4:
                    expensesUtil.displayexpensesList();
                    break;
                case 5:
                    System.out.println("Exiting Expenses System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runOrganizationManagement() {
        // Your existing Organization Management code here
        organizationUtility organizationUtil = new organizationUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Organization Management System ---");
            
            System.out.println("1. Add an Organization");
            System.out.println("2. Update an Organization");
            System.out.println("3. Delete an Organization");
            System.out.println("4. View All Organizations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
               
                case 1:
                    organizationUtil.addorganization();
                    break;
                case 2:
                    organizationUtil.updateorganization();
                    break;
                case 3:
                    organizationUtil.deleteorganization();
                    break;
                case 4:
                    organizationUtil.displayorganizationList();
                    break;
                case 5:
                    System.out.println("Exiting Organization System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runVolunteerManagement() {
        // Your existing Volunteer Management code here
        volunteerUtility volunteerUtil = new volunteerUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Volunteer Management System ---");
            System.out.println("1. Add Volunteer");
            
            System.out.println("2. Update Volunteer");
            System.out.println("3. Delete Volunteer");
            System.out.println("4. Display Volunteers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    volunteerUtil.addvolunteer();
                    break;
                
                case 2:
                    volunteerUtil.updatevolunteer();
                    break;
                case 3:
                    volunteerUtil.deletevolunteer();
                    break;
                case 4:
                    volunteerUtil.displayvolunteerList();
                    break;
                case 5:
                    System.out.println("Exiting Volunteer System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runCampaignManagement() {
        // Your existing Campaign Management code here
        campainUtility campaignUtil = new campainUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Campaign Management System ---");
            System.out.println("1. Add Campaign");
            System.out.println("2. Update Campaign");
            System.out.println("3. Delete Campaign");
            System.out.println("4. View All Campaigns");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    campaignUtil.addcampain();
                    break;
                case 2:
                    campaignUtil.updateCampaign();
                    break;
                case 3:
                    campaignUtil.deleteCampaign();
                    break;
                case 4:
                    campaignUtil.displaycampainList();
                    break;
                case 5:
                    System.out.println("Exiting Campaign System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void runReportManagement() {
        // Your existing Report Management code here
        reportUtility reportUtil = new reportUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Report Management System ---");
            System.out.println("1. add Reports");
            System.out.println("2. Update Report");
            System.out.println("3. Delete Report");
            System.out.println("4. Display Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reportUtil.addreport();
                    break;
                case 2:
                    reportUtil.updatereport();
                    break;
                case 3:
                    reportUtil.deletereport();
                    break;
                case 4:
                	reportUtil.displayreportList();
                case 5:
                	System.out.println("Exiting Report System.");
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }
}

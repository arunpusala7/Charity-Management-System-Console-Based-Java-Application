package campain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import campain.campainException.DuplicateCampaignIdException;
import campain.campainException.InvalidDateFormatException;

public class campainUtility {
    List<campain> campainList = new ArrayList<campain>();

    // Method to display the menu
    public void displayMenu() {
        System.out.println("1. Insert data");
        System.out.println("2. Delete data");
        System.out.println("3. Update data");
        System.out.println("4. View data");
        System.out.println("5. Exit");
        System.out.println("Choose an option: ");
    }

    // Method to display all campaigns (READ operation)
    public void displaycampainList() {
        campainDAO dao = new campainDAO();
        campainList = dao.getAllItemTypes();
        System.out.format("%-17s %-25s %-48s %-20s %-15s %-15s\n", "campain_id", "title", "Description", "Start Date", "End Date", "Organization ID");
        for (campain it : campainList) {
            System.out.format("%-10d %-25s %-55s %-20s %-24s %-15d\n", it.getcampaign_id(), it.gettitle(), it.getdescription_campaign(), it.getstart_date(), it.getend_date(), it.getorganization_id());
        }
    }

    // Method to add a new campaign (CREATE operation)
    public boolean addcampain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter campain id: ");
        int campain_id = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Start Date (yyyy-MM-dd): ");
        String start_date = sc.nextLine();
        System.out.println("Enter End Date (yyyy-MM-dd): ");
        String end_date = sc.nextLine();
        System.out.println("Enter the Description: ");
        String description_campaign = sc.nextLine();
        System.out.println("Enter Organization Id: ");
        int organization_id = sc.nextInt();

        campain it = new campain(campain_id, title, start_date, end_date, description_campaign, organization_id);
        campainDAO d_DAO = new campainDAO();
        try {
            return d_DAO.addcampain(it);
        } catch (DuplicateCampaignIdException e) {
            System.out.println(e.getMessage()); 
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage()); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }

    // Method to delete a campaign (DELETE operation)
    public boolean deleteCampaign() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the campaign ID to delete: ");
        int campain_id = sc.nextInt();
        
        campainDAO dao = new campainDAO();
        return dao.deletecampain(campain_id);
    }

    // Method to update an existing campaign (UPDATE operation)
    public boolean updateCampaign() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the campaign ID to update: ");
        int campain_id = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter new Title: ");
        String title = sc.nextLine();
        System.out.println("Enter new Start Date (yyyy-MM-dd): ");
        String start_date = sc.nextLine();
        System.out.println("Enter new End Date (yyyy-MM-dd): ");
        String end_date = sc.nextLine();
        System.out.println("Enter new Description: ");
        String description_campaign = sc.nextLine();
        System.out.println("Enter new Organization Id: ");
        int organization_id = sc.nextInt();

        campain updatedCampaign = new campain(campain_id, title, start_date, end_date, description_campaign, organization_id);
        campainDAO dao = new campainDAO();
        return dao.updatecampain(updatedCampaign);
    }
}

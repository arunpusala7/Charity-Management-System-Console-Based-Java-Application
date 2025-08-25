package donor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import donor.donorException.DuplicateDonorIdException;
import donor.donorException.InvalidEmailFormatException;
import donor.donorException.InvalidPhoneNumberException;

public class donorUtility {
    List<donor> donorList = new ArrayList<donor>();

    // Method to display the menu
    public void displayMenu() {
        System.out.println("1. Insert donor data");
        System.out.println("2. Delete donor");
        System.out.println("3. Update donor");
        System.out.println("4. View donor list");
        System.out.println("5. Exit");
        System.out.println("Choose an option: ");
    }

    // Method to display all donors (READ operation)
    public void displaydonorList() {
        donorDAO dao = new donorDAO();
        donorList = dao.getAllItemTypes();
        System.out.format("%-5s %-17s %-30s  %s\n", "donor_id", "donor_name", "email", "phone_number");
        for (donor it : donorList) {
            System.out.format("%-5d %-17s %-30s %s\n", it.getdonor_id(), it.getdonor_name(), it.getemail(), it.getphone_number());
        }
    }

    // Method to add a new donor (CREATE operation)
    public boolean adddonor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter donor id: ");
        int donor_id = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter donor name: ");
        String donor_name = sc.nextLine();
        System.out.println("Enter donor email: ");
        String email = sc.nextLine();
        System.out.println("Enter donor phone number: ");
        String phone_number = sc.nextLine();

        donor newDonor = new donor(donor_id, donor_name, email, phone_number);
        donorDAO dao = new donorDAO();
        try {
            return dao.adddonor(newDonor);
        } catch (DuplicateDonorIdException | InvalidPhoneNumberException | InvalidEmailFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a donor (DELETE operation)
    public boolean deleteDonor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the donor ID to delete: ");
        int donor_id = sc.nextInt();

        donorDAO dao = new donorDAO();
        return dao.deleteDonor(donor_id);
    }

    // Method to update an existing donor (UPDATE operation)
    public boolean updateDonor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter donor id to update: ");
        int donor_id = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter new donor name: ");
        String donor_name = sc.nextLine();
        System.out.println("Enter new donor email: ");
        String email = sc.nextLine();
        System.out.println("Enter new donor phone number: ");
        String phone_number = sc.nextLine();

        donor updatedDonor = new donor(donor_id, donor_name, email, phone_number);
        donorDAO dao = new donorDAO();
        return dao.updateDonor(updatedDonor);
    }
}

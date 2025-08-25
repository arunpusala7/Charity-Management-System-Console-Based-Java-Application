package volunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import donor.donorException.InvalidEmailFormatException;
import donor.donorException.InvalidPhoneNumberException;
import volunteer.volunteerException.DuplicateVolunteerIdException;

public class volunteerUtility {
    List<volunteer> volunteerList = new ArrayList<>();

    // Display all volunteers
    public void displayvolunteerList() {
        volunteerDAO dao = new volunteerDAO();
        volunteerList = dao.getAllItemTypes();
        System.out.format("%-17s %-25s %-48s %-20s %-15s\n", "Volunteer ID", "Name", "Email", "Phone No", "Organization ID");
        for (volunteer it : volunteerList) {
            System.out.format("%-10d %-25s %-55s %-24s %-15d\n", it.getvol_id(), it.getvol_name(), it.getemail(), it.getph_numbber(), it.grtorganization_id());
        }
    }

    // Add a new volunteer
    public boolean addvolunteer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Volunteer ID: ");
        int vol_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Volunteer Name: ");
        String vol_name = sc.nextLine();
        System.out.println("Enter Volunteer Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Volunteer Phone Number: ");
        String ph_numbber = sc.nextLine();
        System.out.println("Enter the Organization ID: ");
        int organization_id = sc.nextInt();

        volunteer it = new volunteer(vol_id, vol_name, email, ph_numbber, organization_id);
        volunteerList.add(it);
        volunteerDAO d_DAO = new volunteerDAO();
        try {
            return d_DAO.addvolunteer(it);
        } catch (DuplicateVolunteerIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidEmailFormatException e) {
            System.out.println(e.getMessage());
        } catch (InvalidPhoneNumberException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // Catch all other exceptions
        }
        return false;
    }

    // Update an existing volunteer
    public boolean updatevolunteer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Volunteer ID to update: ");
        int vol_id = sc.nextInt();
        sc.nextLine();

        volunteerDAO dao = new volunteerDAO();
        if (!dao.isVolunteerIdExists(vol_id)) {
            System.out.println("Error: Volunteer ID does not exist.");
            return false;
        }

        System.out.println("Enter new Volunteer Name: ");
        String vol_name = sc.nextLine();
        System.out.println("Enter new Volunteer Email: ");
        String email = sc.nextLine();
        System.out.println("Enter new Volunteer Phone Number: ");
        String ph_numbber = sc.nextLine();
        System.out.println("Enter new Organization ID: ");
        int organization_id = sc.nextInt();

        volunteer updatedVolunteer = new volunteer(vol_id, vol_name, email, ph_numbber, organization_id);
        return dao.updatevolunteer(updatedVolunteer);
    }

    // Delete a volunteer
    public boolean deletevolunteer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Volunteer ID to delete: ");
        int vol_id = sc.nextInt();

        volunteerDAO dao = new volunteerDAO();
        if (!dao.isVolunteerIdExists(vol_id)) {
            System.out.println("Error: Volunteer ID does not exist.");
            return false;
        }

        return dao.deletevolunteer(vol_id);
    }
}

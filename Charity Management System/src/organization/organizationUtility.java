package organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class organizationUtility {
    List<organization> organizationList = new ArrayList<>();

    public void displayorganizationList() {
        organizationDAO dao = new organizationDAO();
        organizationList = dao.getAllItemTypes();
        System.out.format("%-15s %-40s %-50s  %s\n", "organization_id", "name", "Description", "Contact Mail");
        for (organization org : organizationList) {
            System.out.format("%-15s %-40s %-50s %s\n", org.getorganization_id(), org.getname(), org.getdescription(), org.getcontact());
        }
    }

    public boolean addorganization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Organization ID: ");
        int organization_id = sc.nextInt();
        sc.nextLine();  // consume newline
        System.out.println("Enter Organization Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Organization Description: ");
        String description = sc.nextLine();
        System.out.println("Enter Organization Contact Mail: ");
        String contact = sc.nextLine();

        organization org = new organization(organization_id, name, description, contact);
        organizationList.add(org);
        organizationDAO dao = new organizationDAO();
        try {
            return dao.addorganization(org);
        } catch (organizationException.DuplicateOrganizationIdException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateorganization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Organization ID to update: ");
        int organization_id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.println("Enter New Organization Name: ");
        String name = sc.nextLine();
        System.out.println("Enter New Organization Description: ");
        String description = sc.nextLine();
        System.out.println("Enter New Organization Contact Mail: ");
        String contact = sc.nextLine();

        organization org = new organization(organization_id, name, description, contact);
        organizationDAO dao = new organizationDAO();
        return dao.updateorganization(org);
    }

    public boolean deleteorganization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Organization ID to delete: ");
        int organization_id = sc.nextInt();

        organizationDAO dao = new organizationDAO();
        return dao.deleteorganization(organization_id);
    }
}

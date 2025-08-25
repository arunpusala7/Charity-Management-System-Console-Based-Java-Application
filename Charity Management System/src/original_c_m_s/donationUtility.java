package original_c_m_s;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import original_c_m_s.DonationException.DonorNotFoundException;
import original_c_m_s.DonationException.InvalidAmountException;
import original_c_m_s.DonationException.InvalidCampaignException;

public class donationUtility {
    List<donation> donationList = new ArrayList<>();

    // Display all donations
    public void displaydonationList() {
        donationDAO dao = new donationDAO();
        donationList = dao.getAllItemTypes();
        System.out.format("%-5s %-12s %-10s %-10s %s\n", "donation_id", "amount", "donation_date", "donar_id", "campaign_id");
        for (donation it : donationList) {
            System.out.format("%-10s %-15s %-15s %-10s %s\n", it.getdonation_id(), it.getamount(), it.getdonation_date(), it.getdonor_id(), it.getcampaign_id());
        }
    }

    // Add donation
    public boolean adddonation() throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter donation id: ");
        int donation_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Amount: ");
        String amount = sc.nextLine();
        System.out.println("Enter Donation Date : ");
        String donation_date = sc.nextLine();
        System.out.println("Enter Donor ID: ");
        int donor_id = sc.nextInt();
        System.out.println("Enter the Campaign ID:");
        int campaign_id = sc.nextInt();

        donation it = new donation(donation_id, amount, donation_date, donor_id, campaign_id);
        donationDAO d_DAO = new donationDAO();
        return d_DAO.adddonation(it);
    }

    // Update donation
    public boolean updatedonation() throws DonorNotFoundException, InvalidAmountException, InvalidCampaignException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Donation ID to update: ");
        int donation_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Amount: ");
        String amount = sc.nextLine();
        System.out.println("Enter new Donation Date: ");
        String donation_date = sc.nextLine();
        System.out.println("Enter new Donor ID: ");
        int donor_id = sc.nextInt();
        System.out.println("Enter new Campaign ID:");
        int campaign_id = sc.nextInt();

        donation it = new donation(donation_id, amount, donation_date, donor_id, campaign_id);
        donationDAO d_DAO = new donationDAO();
        return d_DAO.updatedonation(it);
    }

    // Delete donation
    public boolean deletedonation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Donation ID to delete: ");
        int donation_id = sc.nextInt();

        donationDAO d_DAO = new donationDAO();
        return d_DAO.deletedonation(donation_id);
    }
}

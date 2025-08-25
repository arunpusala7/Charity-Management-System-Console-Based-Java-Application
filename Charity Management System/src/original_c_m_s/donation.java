package original_c_m_s;

import java.sql.Date;

public class donation {
	
	private int donation_id;
	private String amount;
    private String donation_date;
    private int donor_id;
    private int campaign_id;
    
	public donation(int donation_id, String amount, String donation_date, int donor_id, int campaign_id) {
		this.donation_id = donation_id;
    	this.amount=amount;
        this.donation_date = donation_date;
        this.donor_id = donor_id;
        this.campaign_id = campaign_id;
    	
		// TODO Auto-generated constructor stub
	}

	public int getdonation_id() {
        return donation_id;
    }

   
    public String getamount() {
        return amount;
    }

    
    public String getdonation_date() {
		return donation_date;
    	
    }
    

    public int getdonor_id() {
        return donor_id;
    }

   

    public int getcampaign_id() {
        return campaign_id;
    }

    
}

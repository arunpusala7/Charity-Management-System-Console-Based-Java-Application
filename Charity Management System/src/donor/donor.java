package donor;

public class donor {
	
	private int donor_id;
    private String donor_name;
    private String email;
    private String phone_number;


    public donor(int donor_id, String donor_name,String email,String phone_number) {
        this.donor_id = donor_id;
        this.donor_name = donor_name;
        this.email=email;
        this.phone_number=phone_number;
    }

    public int getdonor_id() {
        return donor_id;
    }
    public void setdonor_id(int donor_id) {
    	this.donor_id=donor_id;
    }


    public String getdonor_name() {
        return donor_name;
    }
    public void setdonor_name(String donor_name) {
    	this.donor_name=donor_name;
    }
    
    public String getemail(){
        return email;
    }
    public void setemail(String email) {
    	this.email=email;
    }
   
    public String getphone_number(){
        return phone_number;
    }
    public void setphone_number(String phone_number) {
    	this.phone_number=phone_number;
    }

}

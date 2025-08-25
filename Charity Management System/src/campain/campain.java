package campain;

public class campain {
	
	private int campaign_id;
    private String title;
    private String start_date;
    private String end_date;
    private String description_campaign;
    private int organization_id;

    public campain(int campaign_id, String title, String start_date, String end_date,String description_campaign, int organization_id) {
    	
    	 this.campaign_id = campaign_id;
         this.title = title;
         this.start_date = start_date;
         this.end_date = end_date;
         this.description_campaign=description_campaign;
         this.organization_id=organization_id;
		// TODO Auto-generated constructor stub
	}
	public int getcampaign_id() {
        return campaign_id;
    }
	 public void setcampaign_id(int campaign_id) {
	        this.campaign_id =campaign_id;
	    }

    public String gettitle() {
        return title;
    }
    public void settitle(String title) {
    	this.title=title;
    }
    public String getstart_date() {
        return start_date;
    }
    public void setstart_date(String start_date) {
    	this.start_date = start_date;
    }

   public String getend_date() {
        return end_date;
    }
   public void setend_date(String end_date) {
	   this.end_date = end_date;
	   
	   
   }
   public String getdescription_campaign(){
        return description_campaign;
    }
   public void setdescription_campaign(String description_campaign) {
	   this.description_campaign=description_campaign;
	   
   }
   public int getorganization_id(){
        return organization_id;
    }
   public void setorganization_id(int organization_id) {
	   this.organization_id=organization_id;
   }
   

}

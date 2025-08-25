package report;

public class report {

	private int report_id;
	private String title;
	   private String report_date;
	   private int organization_id;

	   public report(int report_id,String title,String report_date,int organization_id){
	    this.report_id=report_id;
	    this.title=title;
	    this.report_date=report_date;
	    this.organization_id=organization_id;

	   }
	   public int getreport_id(){
	    return report_id;
	   }
	  
	   public String getreport_date(){
	    return report_date;
	   }
	 
	   public String gettitle(){
	    return title;
	   }
	  
	public int getorganization_id() {
		return organization_id;
	}
}

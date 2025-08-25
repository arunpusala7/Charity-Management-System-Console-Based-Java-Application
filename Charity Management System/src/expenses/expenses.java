package expenses;

public class expenses {
	private int exp_id;
    private String amount;
    private String exp_date;
    private int organization_id;


    public expenses(int exp_id, String amount, String exp_date, int organization_id) {
        this.exp_id = exp_id;
        this.amount = amount;
        this.exp_date = exp_date;
        this.organization_id = organization_id;
    }

    public int getexp_id() {
        return exp_id;
    }
    public void setexp_id(int exp_id) {
    	this.exp_id=exp_id;
    }

    

    public String getamount() {
        return amount;
    }
    public void setamount(String amount) {
    	this.amount=amount;
    }
    

   
    public String getexp_date() {
        return exp_date;
    }
    public void setexp_date(String exp_date) {
    	this.exp_date=exp_date;
    }


    public int getorganization_id() {
        return organization_id;
    }
    public void setorganization_id(int organization_id) {
    	this.organization_id=organization_id;
    }

}

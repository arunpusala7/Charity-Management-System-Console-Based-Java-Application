package volunteer;

public class volunteer {
	private int vol_id;
    private String vol_name;
    private String email;
    private String ph_numbber;
    private int organization_id;

    public volunteer(int vol_id, String vol_name, String email,String ph_numbber,int organization_id) {
        this.vol_id = vol_id;
        this.vol_name = vol_name;
        this.email = email;
        this.ph_numbber=ph_numbber;
        this.organization_id=organization_id;
    }

    public int getvol_id() {
        return vol_id;
    }

    
    public String getvol_name() {
        return vol_name;
    }

    
    public String getemail() {
        return email;
    }

      
    public String getph_numbber(){
        return ph_numbber;
    }
    public int grtorganization_id(){
        return organization_id;
    }
    

}

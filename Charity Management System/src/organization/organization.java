package organization;

public class organization {
	
	private int organization_id;
    private String name;
    private String description;
    private String contact;


    public organization(int organization_id, String name,String description,String contact) {
        this.organization_id = organization_id;
        this.name = name;
        this.description=description;
        this.contact=contact;
    }

    public int getorganization_id() {
        return organization_id;
    }
    public void setorganization_id(int organization_id) {
    	this.organization_id=organization_id;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
    	this.name=name;
    }

    public String getdescription(){
        return description;
    }
    public void setdescription(String description) {
    	this.description=description;
    }
  
    public String getcontact(){
        return contact;
    }
    public void setcontact(String contact) {
    	this.contact=contact;
    }

}

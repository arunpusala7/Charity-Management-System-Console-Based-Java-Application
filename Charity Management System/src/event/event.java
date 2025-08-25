package event;

public class event {
	private int e_id;
    private String e_title;
    private String e_description;
    private String event_date;
    private int organization_id;

    public event(int e_id, String e_title, String e_description, String event_date, int organization_id) {
        this.e_id = e_id;
        this.e_title = e_title;
        this.e_description = e_description;
        this.event_date = event_date;
        this.organization_id = organization_id;
    }

    public int gete_id() {
        return e_id;
    }
    public void sete_id(int e_id) {
    	this.e_id = e_id;
    }
    public String gete_title() {
        return e_title;
    }
    public void sete_title(String e_title) {
    	this.e_title = e_title;
    }
    public String gete_description() {
        return e_description;
    }
    public void sete_description(String e_description) {
    	this.e_description = e_description;
    }
    public String getevent_date() {
        return event_date;
    }
    public void setevent_date(String event_date) {
    	this.event_date = event_date;
    }
    public int getorganization_id() {
        return organization_id;
    }
    public void setorganization_id(int organization_id) {
    	this.organization_id = organization_id;
    }
}

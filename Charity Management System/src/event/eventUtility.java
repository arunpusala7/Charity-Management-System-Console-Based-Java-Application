package event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import event.eventException.DuplicateEventIdException;
import event.eventException.InvalidOrganizationException;
import event.eventException.InvalidDateFormatException;

public class eventUtility {
    List<event> events = new ArrayList<>();

    public void displayeventList() {
        eventDAO dao = new eventDAO();
        events = dao.getAllItemTypes();
        System.out.format("%-5s %-15s %-35s %-15s %-10s\n", "e_id", "e_title", "e_description", "event_date", "organization_id");
        for (event evt : events) {
            System.out.format("%-5d %-15s %-35s %-15s %-10d\n", evt.gete_id(), evt.gete_title(), evt.gete_description(), evt.getevent_date(), evt.getorganization_id());
        }
    }

    public boolean addevent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Event Id:");
        int e_id = sc.nextInt();
        sc.nextLine();
        //sc.nextLine();
        System.out.println("Enter the Event Title:");
        String e_title = sc.nextLine();
        System.out.println("Enter Event Description:");
        String e_description = sc.nextLine();
        System.out.println("Enter Event Date (yyyy-MM-dd):");
        String event_date = sc.nextLine();
        System.out.println("Enter Organization Id:");
        int organization_id = sc.nextInt();

        event evt = new event(e_id, e_title, e_description, event_date, organization_id);
        events.add(evt);
        eventDAO d_DAO = new eventDAO();
        
        try {
            return d_DAO.addevent(evt);
        } catch (DuplicateEventIdException | InvalidOrganizationException | InvalidDateFormatException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an event
    public boolean updateEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Event Id to update:");
        int e_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the new Event Title:");
        String e_title = sc.nextLine();
        System.out.println("Enter the new Event Description:");
        String e_description = sc.nextLine();
        System.out.println("Enter the new Event Date (yyyy-MM-dd):");
        String event_date = sc.nextLine();
        System.out.println("Enter the new Organization Id:");
        int organization_id = sc.nextInt();

        event evt = new event(e_id, e_title, e_description, event_date, organization_id);
        eventDAO dao = new eventDAO();
        
        return dao.updateEvent(evt);
    }

    // Delete an event
    public boolean deleteEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Event Id to delete:");
        int e_id = sc.nextInt();
        
        eventDAO dao = new eventDAO();
        return dao.deleteEvent(e_id);
    }
}

package adventure_time.ui.menus;

public class MenuForTicket implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("TICKETS:");
        System.out.println("1. Return to Main menu");
        System.out.println("2. Add new ticket");
        System.out.println("3. Delete a ticket");
        System.out.println("4. Search a ticket by ID (number)");
        System.out.println("5. Update ticket's data");
        System.out.println("6. Show a list of tour's tickets");
        System.out.println();
    }
}

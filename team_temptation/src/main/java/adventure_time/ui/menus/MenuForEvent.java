package adventure_time.ui.menus;

public class MenuForEvent implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("EVENTS:");
        System.out.println("1. Return to Main menu"); // return to mainMenu
        System.out.println("2. Add new event");
        System.out.println("3. Delete an event");
        System.out.println("4. Search an event");
        System.out.println("5. Update an event's details");
        System.out.println("6. Show events by criteria");
        System.out.println();
    }
}

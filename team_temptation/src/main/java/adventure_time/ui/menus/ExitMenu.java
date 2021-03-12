package adventure_time.ui.menus;

public class ExitMenu implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("ARE YOU SURE?");
        System.out.println("1. Yes!");
        System.out.println("2. Stop! Return to main menu!");
        System.out.println();
    }
}

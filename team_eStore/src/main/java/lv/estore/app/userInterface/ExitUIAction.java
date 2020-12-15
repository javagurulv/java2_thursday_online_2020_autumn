package lv.estore.app.userInterface;

public class ExitUIAction implements UIAction{
    /**
     * Method ot show menu
     */
    public void execute(){
        System.out.println("Application closed.");
        System.exit(0);
    }

}

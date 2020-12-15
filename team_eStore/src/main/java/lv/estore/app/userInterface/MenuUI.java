package lv.estore.app.userInterface;

public class MenuUI implements UIAction{

    /**
     * Method ot show menu
     */
    public void execute(){
        System.out.println("____Welcome to eStore____");
        System.out.println("Choose option by typing a valid number. Type \"exit\" to quit program");
        System.out.println("1 - add product to the store");
        System.out.println("2 - show all products");
        System.out.println("3 - find product by 'Id'");
        System.out.println("4 - find product by 'Name'");
        System.out.println("5 - remove product by 'Id'");
        System.out.println("6 - remove product by 'Name'");
        System.out.println("7 - update product by 'Id'");
        System.out.println("8 - search products by 'Name' and 'Price'");
    }
}

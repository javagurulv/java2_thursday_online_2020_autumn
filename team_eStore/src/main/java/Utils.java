public class Utils {
    /**
     * Method ot show menu
     */
    public static void showMenu(){
        System.out.println("____Welcome to eStore____");
        System.out.println("Choose option by typing a valid number. Type \"exit\" to quit program");
        System.out.println("1 - add product to the store");
        System.out.println("2 - remove product from the store by ID");
        System.out.println("3 - print list of products");
        System.out.println("4 - find product by NAME");
        System.out.println("5 - update product info");
    }

    /**
     * Method to clear screen
     */
    public static void clearScreen() {
        for(int i = 0; i < 80; i++) {
            System.out.print("\b");
        }
    }
}

package internet_store;

import internet_store.console_ui.InternetStoreBusinessSide;
import internet_store.console_ui.InternetStoreCustomerSide;

import java.util.Scanner;

public class MainMenuApplication {

    public static void main(String[] args) {

        InternetStoreBusinessSide internetStoreBusinessSide = new InternetStoreBusinessSide();
        InternetStoreCustomerSide internetStoreCustomerSide = new InternetStoreCustomerSide();

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");
        System.out.println("In order to proceed further, please select your path from the list below: ");
        System.out.println();

        System.out.println("1 - Log In");
        System.out.println("2 - Register");
        System.out.println();

        System.out.println("Please enter you choice here: ");
        int choice = in.nextInt();

        switch (choice) {

            case 1: internetStoreBusinessSide.run();
            break;

            case 2: internetStoreCustomerSide.run();
            break;

            default:
                System.out.println("There is no such option. Please, choose number 1 or 2");
        }
    }
}

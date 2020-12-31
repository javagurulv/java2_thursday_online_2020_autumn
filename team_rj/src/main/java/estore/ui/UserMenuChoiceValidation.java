package estore.ui;

import estore.dependency_injection.DIComponent;

import java.util.Scanner;

@DIComponent
public class UserMenuChoiceValidation {
    public int validateUserMenuChoice(String userStringInput, int menuSize) {
        int choice;
        try {
            choice = Integer.valueOf(userStringInput);
        } catch (Exception e) {
            return -1;
        }
        for (int i = 0; i < menuSize; i++) {
            if (choice == i) {
                return choice;
            }
        }
        return -1;
    }

    public int getUserInputOfMenuItem(int menuSize) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.print("Choice: ");
            String userStringInput = sc.nextLine();
            userInput = validateUserMenuChoice(userStringInput, menuSize);
            if (userInput != -1) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return userInput;
    }
}

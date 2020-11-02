package store.mockSpringClasses;

import store.controller.ItemController;
import store.entity.Item;
import store.entity.types.ItemType;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ThymeLeafMock {

    Scanner scanner = new Scanner(System.in);
    ItemController mainControllerMock = new ItemController();

    public void run(){
        System.out.println("Welcome to the shop & please choose your actions: " +
                "\ntype \"ALL\" to see all available products" +
                "\ntype \"SHIRT\" to see description about shirts" +
                "\ntype \"JACKET\" to see description about jackets" +
                "\ntype \"TROUSERS\" to see description about trousers" +
                "\ntype \"SOCKS\" to see description about socks" +
                "\ntype \"QUIT\" to leave shop:");

        String input;

        do{
            input = scanner.next();
            switch (input){
                case "ALL":
                    parseCompleteListToConsole(mainControllerMock.getAll());
                    break;
                case "SHIRT":
                    parseSingleItemToConsole(mainControllerMock.getItemByType(ItemType.SHIRT));
                    break;
                case "JACKET":
                    parseSingleItemToConsole(mainControllerMock.getItemByType(ItemType.JACKET));
                    break;
                case "TROUSERS":
                    parseSingleItemToConsole(mainControllerMock.getItemByType(ItemType.TROUSERS));
                    break;
                case "SOCKS":
                    parseSingleItemToConsole(mainControllerMock.getItemByType(ItemType.SOCKS));
                    break;
                case "QUIT":
                    System.out.println("Good Bye & see you soon!");
                    break;
                default:
                    System.out.println("Error - Something went wrong, try some valid commands");
                    break;
            }
        } while (!input.equals("QUIT"));
    }

    private void parseCompleteListToConsole(List<Item> listToParse){
        for (Item item : listToParse) {
            System.out.println("We have " + item.getQuantity() + " " + item.getName());
        }
    }

    private void parseSingleItemToConsole(Optional<Item> item){
        item.ifPresent(item1 -> System.out.println(item.get().getDescription()));
    }
}

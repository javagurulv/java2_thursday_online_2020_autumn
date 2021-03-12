package electronic_library.ui;

import electronic_library.ui.book.*;
import electronic_library.ui.reader.*;
import electronic_library.ui.reader_books.AddReaderBooksUICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ApplicationMenu {

    private Map<Integer, UICommand> menuNumberToUICommandMap = new HashMap<>();

    @Autowired
    public ApplicationMenu(List<UICommand> uiCommands) {

        menuNumberToUICommandMap.put(10, findUICommand(uiCommands, AddBookUICommand.class));
        menuNumberToUICommandMap.put(11, findUICommand(uiCommands, DeleteBookByTitleUICommand.class));
        menuNumberToUICommandMap.put(12, findUICommand(uiCommands, DeleteBookByAuthorUICommand.class));
        menuNumberToUICommandMap.put(13, findUICommand(uiCommands, DeleteBookByIdUICommand.class));
        menuNumberToUICommandMap.put(14, findUICommand(uiCommands, GetAllBooksUICommand.class));
        menuNumberToUICommandMap.put(15, findUICommand(uiCommands, FindBookByTitleUICommand.class));
        menuNumberToUICommandMap.put(16, findUICommand(uiCommands, FindBookByAuthorUICommand.class));
        menuNumberToUICommandMap.put(17, findUICommand(uiCommands, FindBookByIdUICommand.class));

        menuNumberToUICommandMap.put(20, findUICommand(uiCommands, AddReaderUICommand.class));
        menuNumberToUICommandMap.put(21, findUICommand(uiCommands, DeleteReaderByFirstNameUICommand.class));
        menuNumberToUICommandMap.put(22, findUICommand(uiCommands, DeleteReaderByLastNameUICommand.class));
        menuNumberToUICommandMap.put(23, findUICommand(uiCommands, DeleteReaderByIdUICommand.class));
        menuNumberToUICommandMap.put(24, findUICommand(uiCommands, DeleteReaderUICommand.class));
        menuNumberToUICommandMap.put(25, findUICommand(uiCommands, GetAllReadersUICommand.class));
        menuNumberToUICommandMap.put(26, findUICommand(uiCommands, FindReaderByFirstNameUICommand.class));
        menuNumberToUICommandMap.put(27, findUICommand(uiCommands, FindReaderByLastNameUICommand.class));
        menuNumberToUICommandMap.put(28, findUICommand(uiCommands, FindReaderByPersonalCodeUICommand.class));
        menuNumberToUICommandMap.put(29, findUICommand(uiCommands, FindReaderByIdUICommand.class));

        menuNumberToUICommandMap.put(30, findUICommand(uiCommands, AddReaderBooksUICommand.class));
        menuNumberToUICommandMap.put(0, findUICommand(uiCommands, ExitUICommand.class));
    }

    public int getMenuNumberFromUser() {
        System.out.println("Please enter menu item number:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void printMenu() {
        System.out.println("\n==============================================================");
        System.out.println("  ELECTRONIC LIBRARY PROGRAM FUNCTIONAL MENU:");
        System.out.println("==============================================================");
        System.out.println("                        B O O K S");
        System.out.println("==============================================================");
        System.out.println("10. Add book to electronic library");
        System.out.println("11. Delete book from electronic library by title");
        System.out.println("12. Delete book from electronic library by author");
        System.out.println("13. Delete book from electronic library by Id");
        System.out.println("14. Print all books in electronic library");
        System.out.println("15. Find all books in electronic library by title");
        System.out.println("16. Find all books in electronic library by author");
        System.out.println("17. Find book by Id in electronic library");
        System.out.println("==============================================================");
        System.out.println("                 R E A D E R'S   R E G I S T R Y");
        System.out.println("==============================================================");
        System.out.println("20. Add reader to reader's registry");
        System.out.println("21. Delete reader by first name from reader's registry");
        System.out.println("22. Delete reader by last name from reader's registry");
        System.out.println("23. Delete reader by Id from reader's registry");
        System.out.println("24. Delete reader from reader's registry");
        System.out.println("25. Print all readers in reader's registry");
        System.out.println("26. Find all readers in reader's registry by first name");
        System.out.println("27. Find all readers in reader's registry by second name");
        System.out.println("28. Find all readers in reader's registry by person code");
        System.out.println("29. Find reader by Id in reader's registry");
        System.out.println("==============================================================");
        System.out.println("                 R E A D E R   B O O K S");
        System.out.println("==============================================================");
        System.out.println("30. Registration of book issuance to the reader");
        System.out.println("==============================================================");
        System.out.println("0. Exit program menu");
        System.out.println("==============================================================");
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        UICommand uiCommand = menuNumberToUICommandMap.get(selectedMenu);
        if (uiCommand != null) {
            uiCommand.execute();
        } else {
            System.out.println("\nMenu item " + selectedMenu + " don't exist, please enter menu item number:\"");
        }
    }

    private UICommand findUICommand(List<UICommand> uiCommands, Class uiCommandClass) {
        return uiCommands.stream()
                .filter(uiCommand -> uiCommand.getClass().equals(uiCommandClass))
                .findFirst()
                .get();
    }
}

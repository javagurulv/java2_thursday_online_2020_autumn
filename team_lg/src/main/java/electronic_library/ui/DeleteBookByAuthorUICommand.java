package electronic_library.ui;

import electronic_library.core.requests.DeleteBookByAuthorRequest;
import electronic_library.core.responses.DeleteBookByAuthorResponse;
import electronic_library.core.services.DeleteBookByAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteBookByAuthorUICommand implements UICommand {

    @Autowired
    private DeleteBookByAuthorService deleteBookByAuthorService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting book from electronic library by author : ");
        System.out.print("Please enter book author : ");
        String bookAuthor = scanner.nextLine();
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest(bookAuthor);
        DeleteBookByAuthorResponse response = deleteBookByAuthorService.deleteBookByAuthorResponse(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isBookRemoved()) {
                System.out.println("\nBook with author = " + bookAuthor + " has been successfully deleted from to electronic library!");
            } else {
                System.out.println("\nThe book with author = " + bookAuthor + " has not been removed from electronic library.");
            }
        }
    }
}
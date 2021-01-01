package team_VK.application.ui.search_book_and_make_booking_menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.core.services.search_book_and_make_booking_menu_services.GetBooksListService;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.util.Scanner;
@Component
public class GetBooksListUIAction implements UIActions {

    @Autowired
    private GetBooksListService getBooksListService;
    @Autowired private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter sorting criteria: \n 1. By Title \n 2. By Author \n 3.By ID ");
        int sortingCriteria = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter number of books per page: \n 1. 20/page \n 2. 40/page \n 3. 100/page ");
        int pagingCriteria = scanner.nextInt();


        GetBookListRequest request = new GetBookListRequest(sortingCriteria, pagingCriteria);
        GetBookListResponse response = getBooksListService.getBooksList(request);

        if (!response.havesError()) {
            System.out.println("Please see books list above");
        } else {
            errorsPrinter.execute (response);
        }


        System.out.println();

    }
}

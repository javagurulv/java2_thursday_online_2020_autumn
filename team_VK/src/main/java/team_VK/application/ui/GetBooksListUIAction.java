package team_VK.application.ui;

import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.core.services.GetBooksListService;

import java.util.Scanner;

public class GetBooksListUIAction implements UIActions {

    private final GetBooksListService getBooksListService;

    public GetBooksListUIAction(GetBooksListService getBooksListService) {
        this.getBooksListService = getBooksListService;
    }

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
            response.getErrorList().forEach(coreError -> System.out.println(coreError.toString()));
        }


        System.out.println();

    }
}

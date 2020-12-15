package lv.estore.app.userInterface;

import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.core.services.iService;

import java.util.Scanner;

public class SearchUIAction implements UIAction{

    private iService searchService;

    public SearchUIAction(final iService searchService) {
        this.searchService = searchService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type orderBy: ");
        String orderBy = scanner.nextLine();
        System.out.println("Type direction");
        String direction = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, direction);
        System.out.println("Type page number");
        Integer pageNumber = new Integer(scanner.nextLine());
        System.out.println("Type page size");
        Integer pageSize = new Integer(scanner.nextLine());


        SearchRequest searchRequest = new SearchRequest(name, ordering, 1, 10);
        searchService.execute(searchRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}

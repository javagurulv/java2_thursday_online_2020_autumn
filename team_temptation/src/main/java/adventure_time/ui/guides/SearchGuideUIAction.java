package adventure_time.ui.guides;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.guides.SearchGuideRequest;
import adventure_time.core.responses.guides.SearchGuideResponse;
import adventure_time.core.services.guides.SearchGuideService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchGuideUIAction implements UIAction {

    @Autowired
    private final SearchGuideService searchGuideService;

//    public SearchGuideUIAction(SearchGuideService searchGuideService) {
//        this.searchGuideService = searchGuideService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Guide name: ");
        String guideName = scanner.nextLine();
        System.out.println("Enter Guide email: ");
        String guideEmail = scanner.nextLine();

        System.out.println("Enter orderBy (Name||Email): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASC||DESC): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        SearchGuideRequest request = new SearchGuideRequest(guideName, guideEmail, ordering, paging);
        SearchGuideResponse response = searchGuideService.execute(request);

        if (response.hasError()) {
            System.out.println("Your request is not correct cause of:");
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Here are list of Guides under your request:");
            response.getGuides().forEach(System.out::println);
        }
    }
}
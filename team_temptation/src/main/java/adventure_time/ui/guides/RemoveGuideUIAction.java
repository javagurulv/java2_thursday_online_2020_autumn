package adventure_time.ui.guides;


import adventure_time.core.requests.guides.RemoveGuideRequest;
import adventure_time.core.responses.guides.RemoveGuideResponse;
import adventure_time.core.services.guides.RemoveGuideService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveGuideUIAction implements UIAction {

    @Autowired
    private RemoveGuideService removeGuideService;

//    public RemoveGuideUIAction(RemoveGuideService removeGuideService) {
//        this.removeGuideService = removeGuideService;
//    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting...");
        System.out.println("Choose the way of the guide deletion: by the name (press N) or by the ID-number (press I):");

        RemoveGuideRequest request;

        if (scanner.nextLine().equals("N")) {
            System.out.println("Enter an guide name: ");
            request = new RemoveGuideRequest(scanner.nextLine(),  "byName");
        } else {
            System.out.println("Enter an guide ID: ");
            request = new RemoveGuideRequest(toLong(scanner.nextLine()), "byId");
        }

        RemoveGuideResponse response = removeGuideService.removeGuide(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.print("The guide \"" + whatIsTheValueOFThisWay(request));
            System.out.println(endOfMessage(response));
        }
    }

    private String endOfMessage(RemoveGuideResponse response) {
        return response.isSuccessRemoval()
                ? "\" removed from list."
                : "\" not found.";
    }

    private static String whatIsTheValueOFThisWay(RemoveGuideRequest request) {
        return request.getDeletionWay().equals("byName")
                ? request.getGuideName()
                : request.getGuideId().toString();
    }

    private Long toLong (String byId) {
        return byId.isBlank()
                ? 0L
                : Long.parseLong(byId);
    }
}

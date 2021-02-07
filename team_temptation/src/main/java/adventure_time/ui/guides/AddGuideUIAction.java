package adventure_time.ui.guides;

import adventure_time.core.requests.guides.AddGuideRequest;
import adventure_time.core.responses.guides.AddGuideResponse;
import adventure_time.core.services.guides.AddGuideService;
import adventure_time.ui.UIAction;

import java.util.Scanner;

public class AddGuideUIAction implements UIAction {

    private final AddGuideService addGuideService;

    public AddGuideUIAction(AddGuideService addGuideService) {
        this.addGuideService = addGuideService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter guide name: ");
        String guideName = scanner.nextLine();

        System.out.println("Enter guide email: ");
        String guideEmail = scanner.nextLine();

        System.out.println("Enter guide phone: ");
        String guidePhone = scanner.nextLine();

        AddGuideRequest request = new AddGuideRequest(guideName, guideEmail, guidePhone);
        AddGuideResponse response = addGuideService.addGuide(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Guide " + guideName + " was added to list.");
        }

    }
}
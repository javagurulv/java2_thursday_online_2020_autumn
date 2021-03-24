package adventure_time.ui.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.core.services.events.RemoveEventService;
import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveEventUIAction implements UIAction {

    @Autowired
    private RemoveEventService removeEventService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting...");
        System.out.println("Enter an event ID:");

        RemoveEventRequest request = new RemoveEventRequest(scanner.nextLong());

        RemoveEventResponse response = removeEventService.removeEvent(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.print("The event was deleted");
        }

        System.out.println();
    }
}

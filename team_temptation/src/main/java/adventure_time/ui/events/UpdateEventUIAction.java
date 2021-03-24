package adventure_time.ui.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.requests.events.UpdateEventRequest;
import adventure_time.core.responses.events.UpdateEventResponse;
import adventure_time.core.services.events.UpdateEventService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@Component
public class UpdateEventUIAction implements UIAction {

    @Autowired
    private UpdateEventService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID number of the event: ");
        Long eventId = Long.parseLong(scanner.nextLine());

        UpdateEventRequest requestOne = new UpdateEventRequest(eventId);
        UpdateEventResponse response = service.findById(requestOne);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
            System.out.println();

        } else {
            Events event = response.getEvent();
            System.out.println("Here is the event you want to change: ");
            System.out.println(response.getEvent().toString());
            System.out.println();

            Long id = event.getEventId();

            String eventName = defineValue(event.getEventName(), "name");

            String eventKind = event.getEventKind();
            System.out.println("Would you like to change the event kind (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                EventKind kind = new EventKind();
                for(Map.Entry<Integer, String> item : kind.getEventKind().entrySet()){
                    System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
                }
                System.out.println("Enter a kind of the event:");
                eventKind = scanner.nextLine();
                if (kind.getEventKind().containsKey(parseInt(eventKind))) {
                    eventKind = kind.getEventKind().get(parseInt(eventKind));
                } else {
                    eventKind = kind.getEventKind().get(0);
                }
            }

            Integer durationHours = Integer.parseInt(defineValue(event.getDurationHours().toString(),
                    "duration"));
            Integer maxNumberParticipants = Integer.parseInt(defineValue(event.getMaxNumberParticipants().toString(),
                    "max number of participant"));
            Integer minNumberParticipants = Integer.parseInt(defineValue(event.getMinNumberParticipants().toString(),
                    "min number of participant"));
            String route = defineValue(event.getRoute(), "route");
            String detailDescription = defineValue(event.getDetailsDescription(), "description");

            AddEventRequest requestTwo = new AddEventRequest(eventName, eventKind, durationHours,
                    maxNumberParticipants, minNumberParticipants,
                    route, detailDescription);

            response = service.updateEvent(requestTwo, id);

            if (response.hasError()) {
                response.getErrors().forEach(System.out::println);
            } else {
                System.out.println("The event " + id + " was updated.");
            }
        }
    }

    private String defineValue (String formerValue, String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to change the " + message + " (Y/N)? ");
        if (scanner.nextLine().equals("Y")) {
            System.out.println("Define new " + message + ": ");
            return scanner.nextLine();
        } else {
            return formerValue;
        }
    }
} 

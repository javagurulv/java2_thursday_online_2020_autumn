package adventure_time.ui.events;

import adventure_time.core.services.events.DisplayEventListService;

import adventure_time.core.domain.Events;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@DIComponent
@Component
public class ShowListOfEventsUIAction implements UIAction {

//    @DIDependency
    @Autowired
    private DisplayEventListService displayEventListService;

//    public DisplayEventUIAction(DisplayEventListService displayEventListService) {
//        this.displayEventListService = displayEventListService;
//    }

    @Override
    public void execute() {
        System.out.println("Here is a list of events: ");
        for (Events item : displayEventListService.getEventsList()) {
            System.out.println(item);
        }
        System.out.println("This is the end.");
    }
}

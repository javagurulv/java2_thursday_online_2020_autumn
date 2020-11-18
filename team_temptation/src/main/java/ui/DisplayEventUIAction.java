package ui;

import domain.Events;
import core.services.DisplayEventListService;

public class DisplayEventUIAction implements UIAction {

    private final DisplayEventListService displayEventListService;

    public DisplayEventUIAction(DisplayEventListService displayEventListService) {
        this.displayEventListService = displayEventListService;
    }

    @Override
    public void execute() {
        System.out.println("Here is a list of events: ");
        for (Events item : displayEventListService.getEventsList()) {
            System.out.println(item);
        }
        System.out.println("This is the end.");
    }
}

package adventure_time.ui.events;

import java.util.HashMap;
import java.util.Map;

public class EventKind {

    private final Map<Integer, String> eventKind;

    public EventKind() {
        eventKind = new HashMap<>();
        eventKind.put(0 , "undefined");
        eventKind.put(1, "bike trip");
        eventKind.put(2, "boat trip");
        eventKind.put(3, "walking trip");
        eventKind.put(4, "motorcycle trip");
        eventKind.put(5, "bus trip");

    }

    public Map<Integer, String> getEventKind() {
        return eventKind;
    }
}

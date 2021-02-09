package adventure_time.ui.guides;

import adventure_time.core.services.guides.DisplayGuideListService;
import adventure_time.core.domain.Guides;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayGuideUIAction implements UIAction {

    @Autowired
    private final DisplayGuideListService displayGuideListService;

//    public DisplayGuideUIAction(DisplayGuideListService displayGuideListService) {
//        this.displayGuideListService = displayGuideListService;
//    }

    @Override
    public void execute() {
        System.out.println("Here is a list of guides: ");
        for (Guides item : displayGuideListService.getGuidesList()) {
            System.out.println(item);
        }
        System.out.println("This is the end.");
    }
}

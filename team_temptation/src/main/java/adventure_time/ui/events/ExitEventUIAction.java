package adventure_time.ui.events;

import adventure_time.ui.UIAction;
import org.springframework.stereotype.Component;

@Component
public class ExitEventUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("This is the End, my only Friend!");
        System.exit(0);
    }
}

package adventure_time.ui.guides;

import adventure_time.ui.UIAction;

public class ExitGuideUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("This is the end, my only friend!");
        System.exit(0);
    }
}

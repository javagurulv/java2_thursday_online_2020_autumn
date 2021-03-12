package java2.application_target_list.console_ui.actions;

import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}

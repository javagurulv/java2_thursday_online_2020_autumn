package dental_clinic.console_ui.manipulation;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.responses.manipulation.AddManipulationResponse;
import dental_clinic.core.services.manipulation.AddManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddManipulationUIAction implements UIAction {

    @Autowired
    private AddManipulationService addManipulationService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    public void execute () {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter manipulation's type");
        String manipulationType = in.nextLine();

        Integer price = inputFormatsValidator.inputInteger("Please enter manipulation's price");

        AddManipulationRequest addManipulationRequest = new AddManipulationRequest(manipulationType, price);
        AddManipulationResponse addManipulationResponse = addManipulationService.execute(addManipulationRequest);

        if (addManipulationResponse.hasErrors()) {
            addManipulationResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Manipulation is added");
        }
    }
}

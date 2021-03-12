package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.SearchPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchPatientUIAction implements UIAction {

    @Autowired
    private SearchPatientService searchPatientService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter surname or personal code for search");
        String inputForSearch = in.nextLine();

        System.out.println("Please enter orderBy: name/surname");
        String orderBy = in.nextLine();

        System.out.println("Please enter order direction: ASC/DESC");
        String orderDirection = in.nextLine();
        OrderingDirection orderingDirection = getOrderingDirection(orderDirection);

        Integer pageNumber = inputFormatsValidator.inputIntegerOrNull("Please enter page number");

        Integer pageSize = inputFormatsValidator.inputIntegerOrNull("Please enter page size");

        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest(inputForSearch, orderBy, orderingDirection, pageNumber, pageSize);
        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        if (searchPatientResponse.hasErrors()){
            searchPatientResponse.getErrors().forEach(System.out::println);
        }else{
            searchPatientResponse.getPatients().forEach(System.out::println);
        }
    }

    private OrderingDirection getOrderingDirection (String orderDirection) {
        if (orderDirection.equalsIgnoreCase("ASC")){
            return OrderingDirection.ASC;
        }
        if (orderDirection.equalsIgnoreCase("DESC")) {
            return OrderingDirection.DESC;
        }
        if (orderDirection == null || orderDirection.isEmpty()) {
            return OrderingDirection.NULL;
        }
        return OrderingDirection.NOT_VALID;
    }
}

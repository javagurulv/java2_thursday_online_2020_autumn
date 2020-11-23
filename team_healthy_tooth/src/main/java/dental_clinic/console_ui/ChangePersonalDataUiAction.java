package dental_clinic.console_ui;

import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.services.ChangePersonalDataService;

import java.util.Scanner;

public class ChangePersonalDataUiAction implements UIAction {

    private final ChangePersonalDataService service;

    public ChangePersonalDataUiAction(ChangePersonalDataService service) {
        this.service = service;
    }

    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter patient's id number : ");
        long id = in.nextLong();
        in.nextLine();

        System.out.println("Enter new surname : ");
        String surname = in.nextLine();

        System.out.println("Enter new phone number : ");
        String phone = in.nextLine();

        ChangePersonalDataRequest request = new ChangePersonalDataRequest(
                id, surname, phone
        );

        ChangePersonalDataResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        }
        else {
            System.out.println("Patient's personal data updated!");
        }
    }

}

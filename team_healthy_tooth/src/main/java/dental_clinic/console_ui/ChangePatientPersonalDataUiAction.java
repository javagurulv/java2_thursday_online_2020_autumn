package dental_clinic.console_ui;

import dental_clinic.core.requests.ChangePatientPersonalDataRequest;
import dental_clinic.core.responses.ChangePatientPersonalDataResponse;
import dental_clinic.core.services.ChangePatientPersonalDataService;

import java.util.Scanner;

public class ChangePatientPersonalDataUiAction implements UIAction {

    private final ChangePatientPersonalDataService service;

    public ChangePatientPersonalDataUiAction(ChangePatientPersonalDataService service) {
        this.service = service;
    }

    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter patient's id : ");
        long id = in.nextLong();

        if (service.canFindId(id)) {
            in.nextLine();
            System.out.println("Please enter new name : ");
            String name = in.nextLine();

            System.out.println("Please enter new surname : ");
            String surname = in.nextLine();

            System.out.println("Please enter new phone number : ");
            String phoneNumber = in.nextLine();

            ChangePatientPersonalDataRequest request = new ChangePatientPersonalDataRequest(
                    id, name, surname, phoneNumber);
            ChangePatientPersonalDataResponse response = service.execute(request);

            if (response.hasErrors()) {
                response.getErrors().forEach(System.out::println);
            }
            else {
                System.out.println("Patient's info updated!");
            }
        }
        else {
            System.out.println("Could not find patient with this ID");
        }
    }
}

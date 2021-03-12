package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.patient.AddPatientResponse;
import dental_clinic.core.services.patient.AddPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddPatientUIAction implements UIAction {

    @Autowired
    private AddPatientService addPatientService;

    public void execute(){
        Scanner in  = new Scanner(System.in);
        System.out.println("Please enter name");
        String name = in.nextLine();
        System.out.println("Please enter surname");
        String surname = in.nextLine();
        System.out.println("Please enter phone");
        String phone = in.nextLine();
        System.out.println("Please enter personal code");
        String personalCode = in.nextLine().replace("-", "");

        AddPatientRequest addPatientRequest = new AddPatientRequest(name, surname, phone, personalCode);
        AddPatientResponse response = addPatientService.execute(addPatientRequest);

        if (response.hasErrors()){
            response.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient added");
        }

    }
}

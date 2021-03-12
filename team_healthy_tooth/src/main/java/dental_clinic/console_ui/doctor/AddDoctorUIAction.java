package dental_clinic.console_ui.doctor;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddDoctorUIAction implements UIAction {

    @Autowired
    private AddDoctorService addDoctorService;

    public void execute () {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter doctor's name");
        String name = in.nextLine();

        System.out.println("Please enter doctor's surname");
        String surname = in.nextLine();

        System.out.println("Please enter phone");
        String phone = in.nextLine();

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest(name, surname, phone);

        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        if (addDoctorResponse.hasErrors()) {
            addDoctorResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("To database is added Dr." +
                    addDoctorResponse.getDoctor().getSurname());
        }
    }

}

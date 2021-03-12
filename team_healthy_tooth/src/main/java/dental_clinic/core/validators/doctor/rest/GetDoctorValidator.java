package dental_clinic.core.validators.doctor.rest;

import dental_clinic.core.requests.doctor.GetDoctorRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetDoctorValidator {

    public List<CoreError> validate(GetDoctorRequest getDoctorRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getDoctorRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetDoctorRequest getDoctorRequest) {
        return (getDoctorRequest.getId() == null || getDoctorRequest.getId() < 1)
                ? Optional.of(new CoreError("id", "Database doesn't contain doctor with id " +
                getDoctorRequest.getId()))
                : Optional.empty();
    }
}

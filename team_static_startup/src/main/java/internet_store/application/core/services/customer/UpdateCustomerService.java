package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.UpdateCustomerRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.UpdateCustomerResponse;
import internet_store.application.core.services.customer.validators.UpdateCustomerRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateCustomerService {

    @Autowired
    private JpaCustomerRepository ormCustomerRepository;
    @Autowired private UpdateCustomerRequestValidator validator;

    public UpdateCustomerResponse execute(UpdateCustomerRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCustomerResponse(errors);
        }

        return ormCustomerRepository.findById(request.getId())
                .map(customer -> {
                    customer.setCustomerFirstName(request.getNewFirstName());
                    customer.setCustomerSecondName(request.getNewSecondName());
                    customer.setCustomerPhone(request.getNewPhone());
                    customer.setCustomerEmail(request.getNewEMail());
                    customer.setCustomerAddress(request.getNewAddress());
                    return new UpdateCustomerResponse(customer);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateCustomerResponse(errors);
                });
    }

}

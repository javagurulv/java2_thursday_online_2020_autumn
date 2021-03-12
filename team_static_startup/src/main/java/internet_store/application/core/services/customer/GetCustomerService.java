package internet_store.application.core.services.customer;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.GetCustomerRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.GetCustomerResponse;
import internet_store.application.core.services.customer.validators.GetCustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class GetCustomerService {

    @Autowired
    private JpaCustomerRepository ormCustomerRepository;
    @Autowired private GetCustomerValidator validator;

    public GetCustomerResponse execute(GetCustomerRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetCustomerResponse(errors);
        }
        return ormCustomerRepository.findById(request.getId())
                .map(GetCustomerResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetCustomerResponse(errors);
                });
    }

}

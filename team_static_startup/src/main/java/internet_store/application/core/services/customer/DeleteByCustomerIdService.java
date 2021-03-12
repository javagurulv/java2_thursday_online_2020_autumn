package internet_store.application.core.services.customer;

import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.requests.customer.DeleteByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.customer.DeleteByCustomerIdResponse;
import internet_store.application.core.services.customer.validators.DeleteByCustomerIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteByCustomerIdService {

    @Autowired private JpaCustomerRepository customerRepository;
    @Autowired private DeleteByCustomerIdValidator validator;

    public DeleteByCustomerIdResponse execute(DeleteByCustomerIdRequest customerIdRequest) {
        List<CoreError> errors = validator.validate(customerIdRequest);
        Long id = customerIdRequest.getCustomerId();

        if (!errors.isEmpty()) {
            return new DeleteByCustomerIdResponse(errors);
        } else return new DeleteByCustomerIdResponse(customerRepository.deleteByCustomerId(id) == 1);
    }

}

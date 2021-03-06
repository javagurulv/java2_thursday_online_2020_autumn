package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_3.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductNameValidator {

    public List<CoreError> validate (DeleteByProductNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        String productName = request.getProductName();
        if(productName == null || productName.isEmpty()){
            errors.add(new CoreError("Product Name", "Product Name must not be empty."));
        }

        return errors;
    }
}

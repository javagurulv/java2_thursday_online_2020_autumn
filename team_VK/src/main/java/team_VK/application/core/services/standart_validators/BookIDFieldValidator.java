package team_VK.application.core.services.standart_validators;

import org.springframework.stereotype.Component;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Component
public class BookIDFieldValidator {

    public List<CoreError> validate (String bookIdString){

        List<CoreError> errors = new ArrayList<>();

        IntStream bookIdStringStream = bookIdString.chars();
        boolean     IsNumeric = bookIdStringStream
                .allMatch(value -> (value >= 48) && (value <= 57));
        if(!IsNumeric || bookIdString.equals(null) || bookIdString.equals("")) { CoreError error = new CoreError("bookID", "Field bookID must be numeric");
            errors.add(error);}
        return errors;
    }

}

package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.CodeError;

import java.util.ArrayList;
import java.util.List;

public class LoginRequestValidator {
    public LoginRequestValidator() {
    }

    public List<CodeError> validate(LogInRequest request) {
        List<CodeError> errorList = new ArrayList<>();

     //  if (request.getPerson().getName() == null || request.getPerson().getName().equals("")) {
     //      CodeError error = new CodeError("personsName", "Wrong name!");
     //      errorList.add(error);
     //  }

     //  if (request.getPerson().getSurname() == null || request.getPerson().getSurname().equals("")) {
     //      CodeError error = new CodeError("personsSurname", "Wrong surname!");
     //      errorList.add(error);
     //  }

        return errorList;
    }
}
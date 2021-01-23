package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class LoginRequestValidator extends Validator {
	public LoginRequestValidator () {
	}

	public List<CodeError> validate (LogInRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(request.getUser().getName(), "User name", errorList);
		verifyNameAndSurname(request.getUser().getSurname(), "User surname", errorList);

		return errorList;
	}
}

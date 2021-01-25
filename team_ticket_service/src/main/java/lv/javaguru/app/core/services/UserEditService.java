package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserEditResponse;
import lv.javaguru.app.core.services.validators.EditUserRequestValidator;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class UserEditService {
	@DIDependency
	private UserDatabase userDatabase;
	@DIDependency
	private EditUserRequestValidator validator;


	public UserEditResponse execute (UserEditRequest request) {
		List<CodeError> errorList = validator.validate(request);

		if (!errorList.isEmpty()) {
			return new UserEditResponse(errorList);
		}

		User user = userDatabase.getUserById(request.getId());

		if (user == null) {
			errorList.add(new CodeError("ID", "No user with such ID!"));
			return new UserEditResponse(errorList);
		}

		return new UserEditResponse(user);
	}


	public UserEditResponse executeNameUpdate (UserEditRequest request) {
		String name = request.getNewValue();

		List<CodeError> responseList = validator.validateName(name);

		if (!responseList.isEmpty()) {
			return new UserEditResponse(responseList);
		}
		userDatabase.getUserById(request.getId()).setName(name);

		return new UserEditResponse("Hurrah! Name has been changed");
	}


	public UserEditResponse executeSurnameUpdate (UserEditRequest request) {
		String surname = request.getNewValue();

		List<CodeError> errorList = validator.validateSurname(surname);

		if (!errorList.isEmpty()) {
			return new UserEditResponse(errorList);
		}
		userDatabase.getUserById(request.getId()).setSurname(surname);

		return new UserEditResponse("Hurrah! Surname has been changed");
	}


}

package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.dependency_injection.DIComponent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class EditFlightRequestValidator extends Validator {

	public List<CodeError> validateName (String name) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(name, "User name", errorList);
		return errorList;
	}

	public List<CodeError> validateSurname (String surname) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(surname, "User surname", errorList);
		return errorList;
	}

	public List<CodeError> validateId (Long id) {
		List<CodeError> errorList = new ArrayList<>();

		if (id == null || id < 1)
			errorList.add(new CodeError("Id", "Null or negative ID!"));

		return errorList;
	}

	public List<CodeError> validateCity (String city, String fieldName) {
		List<CodeError> errorList = new ArrayList<>();

		verifyCityAndCountry(city, fieldName, errorList);

		return errorList;
	}

	public List<CodeError> validateCountry (String country, String fieldName) {
		return validateCity(country, fieldName);
	}

	public List<CodeError> validateDate (LocalDate date) {
		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = dateIsNotNullOrEmpty(date, "Departure date");
		if (codeError.isPresent()) {
			errors.add(codeError.get());
			return errors;
		}

		isDateAfter(date, "Departure date").ifPresent(errors::add);

		return errors;
	}

	private Optional<CodeError> dateIsNotNullOrEmpty (LocalDate dateRequest, String field) {
		return (isNullOrEmpty(dateRequest))
				? Optional.of(new CodeError(field, "The string mustn't be empty!"))
				: Optional.empty();
	}

	private Optional<CodeError> isDateAfter (LocalDate request, String field) {
		return (!isDateAfter(request))
				? Optional.of(new CodeError(field, "Date shouldn't be in past!"))
				: Optional.empty();
	}

	private boolean isNullOrEmpty (LocalDate date) {
		return (date == null);
	}

	private boolean isDateAfter (LocalDate date) {
		return date.isAfter(LocalDate.now());
	}


}

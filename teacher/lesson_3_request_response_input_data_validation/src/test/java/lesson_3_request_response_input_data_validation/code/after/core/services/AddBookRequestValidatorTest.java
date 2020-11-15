package lesson_3_request_response_input_data_validation.code.after.core.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import lesson_3_request_response_input_data_validation.code.after.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;

public class AddBookRequestValidatorTest {

	private AddBookRequestValidator validator = new AddBookRequestValidator();

	@Test
	public void success() {
		AddBookRequest request = new AddBookRequest("Title", "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		AddBookRequest request = new AddBookRequest(null, "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "title");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenTitleIsEmptyString() {
		AddBookRequest request = new AddBookRequest("", "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "title");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenAuthorIsNull() {
		AddBookRequest request = new AddBookRequest("Title", null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "author");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenAuthorIsEmptyString() {
		AddBookRequest request = new AddBookRequest("Title", "");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "author");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorsWhenAuthorAndTitleIsNull() {
		AddBookRequest request = new AddBookRequest(null, null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 2);
	}

}
package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AuthorFieldValidatorTest  {

   private AuthorFieldValidator subject = new AuthorFieldValidator();


    @Test
    public void ShouldValidateCorrectBookAuthor() {
// positive functional test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway", 10);

        List<CoreError> errors = subject.validate(addBookRequest.getBookAuthor());
        Assert.assertEquals(0, errors.size());
    }

    @Test
    public void ShouldntValidate_too_short_BookAuthor() {
// negative. bookAuthor less then 3 letter // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Er", 10);

        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor is too short");
        errorsExpected.add(error);

        List<CoreError>  errorsActual = subject.validate(addBookRequest.getBookAuthor());
        Assert.assertEquals(errorsExpected, errorsActual);
    }

    @Test
    public void ShouldntValidate_space_BookAuthor() {
        // negative. bookAuthor contains only Spaces // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "  ", 10);

        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor can't be Space");
        errorsExpected.add(error);

        List<CoreError> errorsActual = subject.validate(addBookRequest.getBookAuthor());
        Assert.assertEquals(errorsExpected, errorsActual);
    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookAuthor() {
// negative. bookAuthor contains illegal character with code 31 // boundary test

    AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway!", 10);

    List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
        errorsExpected.add(error);

        List<CoreError> errorsActual = subject.validate(addBookRequest.getBookAuthor());
        Assert.assertEquals(errorsExpected, errorsActual);
    }


}
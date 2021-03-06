package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.responses.CoreError;

import java.util.List;

public class ClientPersonalCodeFieldValidatorTest {

    ClientPersonalCodeFieldValidator subject = new ClientPersonalCodeFieldValidator();

    @Test
    public void ShouldReturnEmptyListWhenPcIsCorrect() {
        String clientPersonalCode = "321265-16590";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenPcHavesOnlyZeros() {
        String clientPersonalCode = "000000-00000";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenPcHavesOnlyNines() {
        String clientPersonalCode = "999999-99999";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 0);
    }



    @Test
    public  void ShouldReturnNonEmptyListWhenIdIsSpecialSymbol() {
        String clientPersonalCode = "&*%^&%-%^&%*";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode haves illegal format. Should be 999999-99999");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenIdHavesIllegalSymbolInTheMiddle() {
        String clientPersonalCode = "321!65-16590";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode haves illegal format. Should be 999999-99999");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPcIsEmpty() {
        String clientPersonalCode = "";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too short.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPsIsNotInFormat() {
        String clientPersonalCode = "Buz";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too short.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPcLeftSideIsTooShort() {
        String clientPersonalCode = "32136-16590";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too short.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPcRightSideIsTooShort() {
        String clientPersonalCode = "321265-1659";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too short.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPcLeftSideIsTooLong() {
        String clientPersonalCode = "3213654-16590";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too long.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenPcRightSideIsTooLong() {
        String clientPersonalCode = "321265-165904";
        List<CoreError> errors = subject.validate(clientPersonalCode);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientPersonalCode is too long.");
        Assert.assertEquals(errors.get(0).field, "clientPersonalCode");
    }
}
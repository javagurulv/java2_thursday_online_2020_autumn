package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.SearchPatientRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientRequestValidatorTest {

    SearchPatientRequestValidator searchPatientRequestValidator = new SearchPatientRequestValidator();

    @Test
    public void testEmptySearchRequest() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest(null, "name", OrderingDirection.ASC, 1, 10);
        CoreError expectedError = new CoreError("search", "Search request can't be empty");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInput() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("k34", "name", OrderingDirection.ASC, 1, 10);
        CoreError expectedError = new CoreError("search", "Search by surname can contain only letters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPersonalCode() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("45434", "name", OrderingDirection.ASC, 1, 10);
        CoreError expectedError = new CoreError("personal code", "Not valid input for personal code");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderingDirection1() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.NULL, 1, 10);
        CoreError expectedError = new CoreError("search", "Not valid input for ordering parameters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderingDirection2() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.NOT_VALID, 1, 10);
        CoreError expectedError = new CoreError("orderDirection", "Not valid input for orderDirection");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForOrderBy() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "tooth", OrderingDirection.ASC, 1, 10);
        CoreError expectedError = new CoreError("orderBy", "Not valid input for orderBy");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testOrderingBothNull() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", null, OrderingDirection.NULL, 1, 10);

        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.isEmpty());
    }

    @Test
    public void testNotValidInputForPagingOneFilled() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.ASC, 1, null);
        CoreError expectedError = new CoreError("search", "Not valid input for paging parameters");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testPagingNull() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.ASC, null, null);
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.isEmpty());
    }

    @Test
    public void testNotValidInputForPageNumber() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.ASC, -1, 10);
        CoreError expectedError = new CoreError("pageNumber", "Not valid input for page number");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testNotValidInputForPageSize() {
        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Mister", "name", OrderingDirection.ASC, 1, 0);
        CoreError expectedError = new CoreError("pageSize", "Not valid input for page size");
        List<CoreError> errorList = searchPatientRequestValidator.validate(searchPatientRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

}
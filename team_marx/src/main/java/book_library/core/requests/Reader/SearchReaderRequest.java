package book_library.core.requests.Reader;


import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;

public class SearchReaderRequest {

    private String firstName;
    private String lastName;
    private Long personalCode;

    private Ordering ordering;
    private Paging paging;

    public SearchReaderRequest() {
    }

    public SearchReaderRequest(String firstName, String lastName, Long personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public SearchReaderRequest(String firstName, String lastName, Long personalCode, Ordering ordering) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.ordering = ordering;
    }

    public SearchReaderRequest(String firstName, String lastName, Long personalCode, Paging paging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.paging = paging;
    }

    public SearchReaderRequest(String firstName, String lastName, Long personalCode, Ordering ordering, Paging paging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public boolean isFirstNameProvided() {
        return this.firstName != null && !this.firstName.isEmpty();
    }

    public boolean isLastNameProvided() {
        return this.lastName != null && !this.lastName.isEmpty();
    }

    public boolean isPersonalCodeProvided() {
        return this.personalCode != null && !this.personalCode.toString().isEmpty();
    }
}

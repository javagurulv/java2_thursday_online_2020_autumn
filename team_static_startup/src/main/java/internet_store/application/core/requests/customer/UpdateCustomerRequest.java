package internet_store.application.core.requests.customer;

public class UpdateCustomerRequest {
    private Long id;
    private String newFirstName;
    private String newSecondName;
    private String newPhone;
    private String newEMail;
    private String newAddress;

    public UpdateCustomerRequest() { }

    public Long getId() {
        return id;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewSecondName() {
        return newSecondName;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public String getNewEMail() {
        return newEMail;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public void setNewSecondName(String newSecondName) {
        this.newSecondName = newSecondName;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public void setNewEMail(String newEMail) {
        this.newEMail = newEMail;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}

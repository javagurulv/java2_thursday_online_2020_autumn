package dental_clinic.core.requests.manipulation;

public class AddManipulationRequest {

    private String manipulationType;

    private Integer price;

    public AddManipulationRequest() { }

    public AddManipulationRequest(String manipulationType, Integer price) {
        this.manipulationType = manipulationType;
        this.price = price;
    }

    public String getManipulationType() {
        return manipulationType;
    }

    public void setManipulationType(String manipulationType) {
        this.manipulationType = manipulationType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

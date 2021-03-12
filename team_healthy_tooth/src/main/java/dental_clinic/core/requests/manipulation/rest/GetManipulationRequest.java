package dental_clinic.core.requests.manipulation.rest;

public class GetManipulationRequest {

    private Long id;

    public GetManipulationRequest() { }

    public GetManipulationRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

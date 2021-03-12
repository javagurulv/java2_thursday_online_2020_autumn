package dental_clinic.core.requests.patient;

public class GetPersonalDataRequest {

    private Long id;

    public GetPersonalDataRequest() { }

    public GetPersonalDataRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package dental_clinic.core.requests.visit;

public class GetVisitRequest {

    private Long id;

    public GetVisitRequest() { }

    public GetVisitRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

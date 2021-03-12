package dental_clinic.core.requests.plannedVisit;

public class GetPlannedVisitRequest {

    private Long id;

    GetPlannedVisitRequest() { }

    public GetPlannedVisitRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

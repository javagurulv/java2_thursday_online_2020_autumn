package dental_clinic.core.requests.doctor;

public class GetDoctorRequest {

    private Long id;

    public GetDoctorRequest() { }

    public GetDoctorRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

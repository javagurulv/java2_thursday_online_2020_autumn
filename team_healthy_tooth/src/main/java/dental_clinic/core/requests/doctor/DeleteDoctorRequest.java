package dental_clinic.core.requests.doctor;

public class DeleteDoctorRequest {

    private Long id;

    public DeleteDoctorRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package dental_clinic.core.requests.patient;

public class GetSpecificPatientHistoryRequest {

    private Long id;

    public GetSpecificPatientHistoryRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}

package dental_clinic.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ToothInfo {

    private List <Optional <String>> comment = new ArrayList<>();
    private List<ToothStatus> status;

    public ToothInfo (Optional<String> comment, ToothStatus...status){
        this.comment.add(comment);
        this.status = new ArrayList<>(Arrays.asList(status));
    }

    public List <Optional <String>> getComment() {
        return comment;
    }

    public void addComment(Optional <String> comment) {
        this.comment.add(comment);
    }

    public List<ToothStatus> getStatus() {
        return status;
    }

    public void addStatus(ToothStatus status) {
        this.status.add(status);
    }

    @Override
    public String toString() {
        String result = "ToothInfo{ ";
        for (int i = 0; i < comment.size(); i++){
            if (comment.get(i).isPresent()){
                result += " " + comment.get(i) + " ";
            }
            result += "status=" + status +'}';
        }
        return result;
    }
}

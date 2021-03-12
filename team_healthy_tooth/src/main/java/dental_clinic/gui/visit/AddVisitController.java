package dental_clinic.gui.visit;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.services.visit.AddVisitService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class AddVisitController {

    @FXML
    private TextField patientId;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField doctorId;

    @FXML
    private PasswordField toothNumber;

    @FXML
    private TextField manipulationId;

    @FXML
    private TextField toothStatus;

    @FXML
    private PasswordField comment;

    @Autowired
    private AddVisitService addVisitService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Long parsedPatientId = Long.parseLong(patientId.getText());
                Long parseManipulationId = Long.parseLong(manipulationId.getText());
                Long parsedDoctorId = Long.parseLong(doctorId.getText());
                Integer parsedToothNumber = Integer.parseInt(toothNumber.getText());
                ToothStatus parsedToothStatus = inputToothStatus(Integer.parseInt(toothStatus.getText()));
                AddVisitRequest addVisitRequest = new AddVisitRequest(parsedPatientId, parseManipulationId,
                        parsedDoctorId, parsedToothNumber, parsedToothStatus, comment.getText());
                AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
                if (!addVisitResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/addVisit.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/addVisit.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }

    private ToothStatus inputToothStatus(int variant){

        switch (variant) {
            case 1: return ToothStatus.KARIES;
            case 2: return ToothStatus.PLOMBA;
            case 3: return ToothStatus.SAKNE;
            case 4: return ToothStatus.KRONITIS;
            case 5: return ToothStatus.KLAMERS;
            case 6: return ToothStatus.NAV_ZOBA;
            case 7: return ToothStatus.FASETE;
            case 8: return ToothStatus.NONEMAMA_PROTEZE;
            case 9: return ToothStatus.KRONITIS_AR_FAS;
            case 10: return ToothStatus.PLAST_KRONITIS;
            case 11: return ToothStatus.TILTINI;
            case 12: return ToothStatus.HEALTHY;
        }
        return ToothStatus.OTHER;
    }

}


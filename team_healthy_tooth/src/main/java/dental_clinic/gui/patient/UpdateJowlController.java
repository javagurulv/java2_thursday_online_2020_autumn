package dental_clinic.gui.patient;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.patient.UpdatePatientJowlInfoResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.patient.UpdatePatientJowlInfoService;
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

public class UpdateJowlController {

    @FXML
    private TextField patientId;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private PasswordField toothNumber;

    @FXML
    private TextField toothStatus;

    @Autowired
    private UpdatePatientJowlInfoService updatePatientJowlInfoService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Long id = Long.parseLong(patientId.getText());
                Integer number = Integer.parseInt(toothNumber.getText());
                Integer status = Integer.parseInt(toothStatus.getText());
                UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest =
                        new UpdatePatientsJowlInfoRequest(id, number, inputToothStatus(status));
                UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse =
                        updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);
                if (!updatePatientJowlInfoResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/updateJowl.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/updateJowl.fxml"));
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
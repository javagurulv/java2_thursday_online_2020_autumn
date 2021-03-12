package dental_clinic.gui.patient;

import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.patient.AddPatientResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.patient.AddPatientService;
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

public class AddPatientController {

    @FXML
    private TextField name;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField personalCode;

    @FXML
    private PasswordField phone;

    @FXML
    private TextField surname;

    @Autowired
    private AddPatientService addPatientService;

    @FXML
    void initialize() {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            AddPatientRequest addPatientRequest = new AddPatientRequest(name.getText(), surname.getText(),
                    phone.getText(), personalCode.getText());
            AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
            if (!addPatientResponse.hasErrors()) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/addPatient.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain = new Image(iconStream);
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
            Image iconMain = new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }
}

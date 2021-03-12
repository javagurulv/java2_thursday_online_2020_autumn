package dental_clinic.gui.doctor;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.doctor.DeleteDoctorResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.doctor.DeleteDoctorService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class FireDoctorController {

    @FXML
    private TextField id;

    @FXML
    private Button fireButton;

    @FXML
    private Button exitButton;

    @Autowired
    private DeleteDoctorService deleteDoctorService;

    @FXML
    void initialize( ) {
        fireButton.setOnAction(event -> {
            fireButton.getScene().getWindow().hide();
            Parent root = null;
            Long longId;
            try {
                longId = Long.parseLong(id.getText());
                DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(longId);
                DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);
                if (!deleteDoctorResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/fireDoctor.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/fireDoctor.fxml"));
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

}


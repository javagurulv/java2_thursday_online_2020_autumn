package dental_clinic.gui.manipulation;

import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.manipulation.DeactivateManipulationResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.manipulation.DeactivateManipulationService;
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

public class DeactivateManipulationController {

    @FXML
    private TextField id;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @Autowired
    private DeactivateManipulationService deactivateManipulationService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Long parsedId = Long.parseLong(id.getText());
                DeactivateManipulationRequest deactivateManipulationRequest = new DeactivateManipulationRequest(parsedId);
                DeactivateManipulationResponse deactivateManipulationResponse =
                        deactivateManipulationService.execute(deactivateManipulationRequest);
                if (!deactivateManipulationResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/deactivateManipulation.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/deactivateManipulation.fxml"));
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

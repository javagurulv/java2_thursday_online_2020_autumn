package dental_clinic.gui.manipulation;

import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.manipulation.GetManipulationsListService;
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

public class GetManipulationsController {

    @FXML
    private Button exitButton;

    @Autowired
    private GetManipulationsListService getManipulationsService;

    @FXML
    void initialize( ) {

        //GetManipulationsListResponse getManipulationsListResponse = getManipulationsService.execute(new GetManipulationsListRequest());

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

package dental_clinic.gui.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.doctor.FillDoctorsWorkGraphicService;
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

public class FillDoctorsWorkGraphicController {

    @FXML
    private TextField id;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField day;

    @FXML
    private TextField from;

    @FXML
    private TextField to;

    @Autowired
    private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Long longId = Long.parseLong(id.getText());
                Integer parsedDay = Integer.parseInt(day.getText());
                FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest =
                        new FillDoctorsWorkGraphicRequest(longId, parsedDay, from.getText(), to.getText());
                FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse =
                        fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);
                if (!fillDoctorsWorkGraphicResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/fillGraphic.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/fillGraphic.fxml"));
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

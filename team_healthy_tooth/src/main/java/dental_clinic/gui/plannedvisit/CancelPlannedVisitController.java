package dental_clinic.gui.plannedvisit;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.services.planned_visit.CancelPlannedVisitService;
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

public class CancelPlannedVisitController {

    @FXML
    private TextField id;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @Autowired
    private CancelPlannedVisitService cancelPlannedVisitService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Long parseId = Long.parseLong(id.getText());
                CancelPlannedVisitRequest cancelPlannedVisitRequest =
                        new CancelPlannedVisitRequest(parseId);
                CancelPlannedVisitResponse cancelPlannedVisitResponse =
                        cancelPlannedVisitService.execute(cancelPlannedVisitRequest);
                if (!cancelPlannedVisitResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/cancelPlannedVisit.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/cancelPlannedVisit.fxml"));
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

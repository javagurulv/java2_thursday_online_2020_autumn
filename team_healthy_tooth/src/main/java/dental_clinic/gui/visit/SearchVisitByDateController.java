package dental_clinic.gui.visit;

import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.responses.visit.SearchVisitByDateResponse;
import dental_clinic.core.services.visit.SearchVisitByDateService;
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

public class SearchVisitByDateController {

    @FXML
    private TextField dayFrom;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField monthFrom;

    @FXML
    private TextField dayTo;

    @FXML
    private TextField monthTo;

    @Autowired
    private SearchVisitByDateService searchVisitByDateService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                Integer parsedDayFrom = Integer.parseInt(dayFrom.getText());
                Integer parsedDayTo = Integer.parseInt(dayTo.getText());
                Integer parsedMonthFrom = Integer.parseInt(monthFrom.getText());
                Integer parsedMonthTo = Integer.parseInt(monthTo.getText());
                SearchVisitByDateRequest searchVisitByDateRequest =
                        new SearchVisitByDateRequest(parsedDayFrom, parsedDayTo, parsedMonthFrom, parsedMonthTo);
                SearchVisitByDateResponse searchVisitByDateResponse = searchVisitByDateService.execute(searchVisitByDateRequest);
                if (!searchVisitByDateResponse.hasErrors()) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/searchVisitByDate.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/searchVisitByDate.fxml"));
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

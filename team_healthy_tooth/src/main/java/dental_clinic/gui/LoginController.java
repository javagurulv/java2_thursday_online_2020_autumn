package dental_clinic.gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import dental_clinic.core.services.user.ContainsUserServiceGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private ContainsUserServiceGUI containsUserServiceGUI;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label userNameRequired;

    @FXML
    private TextFlow RequiredPassword;

    @FXML
    private Button signInButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize( ) {
        signInButton.setOnAction(event -> {
            Parent root = null;
            if (
                    userName.getText().equals("admin") && password.getText().equals("admin")
                    //containsUserServiceGUI.execute(userName.getText(), password.getText())
            ) {
                try {
                    signInButton.getScene().getWindow().hide();
                    root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    signInButton.getScene().getWindow().hide();
                    if (userName.getText()==null) {userNameRequired.textProperty();};
                    if ( password.getText()==null) {RequiredPassword.getScene();};
                    root = FXMLLoader.load(getClass().getResource("/FXML/scene.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();

            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });

        exitButton.setOnAction(event ->  System.exit(0));
    }
}


package dental_clinic.gui.user;

import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.requests.user.SetRoleRequest;
import dental_clinic.core.requests.user.UsersRoleRequest;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.responses.user.SetRoleResponse;
import dental_clinic.core.responses.user.UsersRoleResponse;
import dental_clinic.core.services.user.SetRoleService;
import dental_clinic.core.services.user.UserRoleService;
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

public class GetUsersRoleController {

    @FXML
    private TextField name;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField roleId;

    @Autowired
    private UserRoleService userRoleService;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            enterButton.getScene().getWindow().hide();
            Parent root = null;
            Long id;
            try {
                id = Long.parseLong(name.getText());
                UsersRoleRequest usersRoleRequest = new UsersRoleRequest(id);
                UsersRoleResponse usersRoleResponse = userRoleService.execute(usersRoleRequest);
                if (!usersRoleResponse.hasErrors()) {
                    try {
                        //roleId = usersRoleResponse.getRole();
                        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/getUsersRole.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException n) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/getUsersRole.fxml"));
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

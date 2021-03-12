package dental_clinic.gui;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class MainMenuController {

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private Button backButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button blockUserButton;

    @FXML
    private Button getUsersButton;

    @FXML
    private Button addRoleButton;

    @FXML
    private Button getRolesButton;

    @FXML
    private Button setRoleButton;

    @FXML
    private Button usersRoleButton;

    @FXML
    private Button addPersonalData;

    @FXML
    private Button changePersonalData;

    @FXML
    private Button getPatientCard;

    @FXML
    private Button getPersonalData;

    @FXML
    private Button getPatientHistory;

    @FXML
    private Button searchPatient;

    @FXML
    private Button updateJowl;

    @FXML
    private Button backButtonPatient;

    @FXML
    private Button addDoctor;

    @FXML
    private Button deleteDoctor;

    @FXML
    private Button fillGraphic;

    @FXML
    private Button getDoctors;

    @FXML
    private Button backButtonDoctor;

    @FXML
    private Button addManipulation;

    @FXML
    private Button deactivateManipulation;

    @FXML
    private Button getManipulations;

    @FXML
    private Button backButtonPatient1;

    @FXML
    private Button addVisit;

    @FXML
    private Button searchVisitByDate;

    @FXML
    private Button searchVisitByPatientId;

    @FXML
    private Button backButtonVisit;

    @FXML
    private Button addPlannedVisit;

    @FXML
    private Button changePlannedVisit;

    @FXML
    private Button cancelPlannedVisit;

    @FXML
    private Button getPalnnedVisitById;

    @FXML
    private Button searchPlannedVisitByDate;

    @FXML
    private Button searchPlannedVisitByPersonalCode;

    @FXML
    private Button backButtonPlannedVisit;


    @FXML
    void initialize() {
        addUserButton.setOnAction(event -> {
            Parent root = null ;
            try {
                addUserButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getUsersButton.setOnAction(event -> {
            Parent root = null ;
            try {
                getUsersButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getUsers.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        blockUserButton.setOnAction(event -> {
            Parent root = null ;
            try {
                blockUserButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/blockUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addRoleButton.setOnAction(event -> {
            Parent root = null ;
            try {
                addRoleButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addRole.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getRolesButton.setOnAction(event -> {
            Parent root = null ;
            try {
                getRolesButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getRoles.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        setRoleButton.setOnAction(event -> {
            Parent root = null ;
            try {
                setRoleButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/setRole.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        usersRoleButton.setOnAction(event -> {
            Parent root = null ;
            try {
                usersRoleButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getUsersRole.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addPersonalData.setOnAction(event -> {
            Parent root = null ;
            try {
                addPersonalData.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addPatient.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        changePersonalData.setOnAction(event -> {
            Parent root = null ;
            try {
                changePersonalData.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/changePersonalData.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getPatientCard.setOnAction(event -> {
            Parent root = null ;
            try {
                getPatientCard.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getPatientCard.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getPersonalData.setOnAction(event -> {
            Parent root = null ;
            try {
                getPersonalData.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getPersonalData.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getPatientHistory.setOnAction(event -> {
            Parent root = null ;
            try {
                getPatientHistory.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getPatientHistory.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        searchPatient.setOnAction(event -> {
            Parent root = null ;
            try {
                searchPatient.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/searchPatient.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        updateJowl.setOnAction(event -> {
            Parent root = null ;
            try {
                updateJowl.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/updateJowl.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        updateJowl.setOnAction(event -> {
            Parent root = null ;
            try {
                updateJowl.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/updateJowl.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addDoctor.setOnAction(event -> {
            Parent root = null ;
            try {
                addDoctor.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addDoctor.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        deleteDoctor.setOnAction(event -> {
            Parent root = null ;
            try {
                deleteDoctor.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/fireDoctor.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getDoctors.setOnAction(event -> {
            Parent root = null ;
            try {
                getDoctors.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getDoctors.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        fillGraphic.setOnAction(event -> {
            Parent root = null ;
            try {
                fillGraphic.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/fillGraphic.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addManipulation.setOnAction(event -> {
            Parent root = null ;
            try {
                addManipulation.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addManipulation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        deactivateManipulation.setOnAction(event -> {
            Parent root = null ;
            try {
                deactivateManipulation.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/deactivateManipulation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getManipulations.setOnAction(event -> {
            Parent root = null ;
            try {
                getManipulations.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getManipulations.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addVisit.setOnAction(event -> {
            Parent root = null ;
            try {
                addVisit.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addVisit.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        searchVisitByDate.setOnAction(event -> {
            Parent root = null ;
            try {
                searchVisitByDate.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/searchVisitByDate.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        searchVisitByPatientId.setOnAction(event -> {
            Parent root = null ;
            try {
                searchVisitByPatientId.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/searchVisitByPatientId.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        addPlannedVisit.setOnAction(event -> {
            Parent root = null ;
            try {
                addPlannedVisit.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addPlannedVisit.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        changePlannedVisit.setOnAction(event -> {
            Parent root = null ;
            try {
                changePlannedVisit.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/changePlannedVisitTime.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        cancelPlannedVisit.setOnAction(event -> {
            Parent root = null ;
            try {
                cancelPlannedVisit.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/cancelPlannedVisit.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        getPalnnedVisitById.setOnAction(event -> {
            Parent root = null ;
            try {
                getPalnnedVisitById.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/getPlannedVisitById.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        searchPlannedVisitByDate.setOnAction(event -> {
            Parent root = null ;
            try {
                searchPlannedVisitByDate.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/searchPlannedVisitByDate.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        searchPlannedVisitByPersonalCode.setOnAction(event -> {
            Parent root = null ;
            try {
                searchPlannedVisitByPersonalCode.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/searchPlannedVisitByPersonalCode.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/scene.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            niceView(root);
        });
    }

    private void niceView(Parent root) {
        InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
        Image iconMain = new Image(iconStream);
        Stage stage = new Stage();
        stage.getIcons().add(iconMain);
        stage.setTitle("Dental Clinic");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    };
}
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Entities.President;
import Services.PresidentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminSignup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_tf;

    @FXML
    private AnchorPane formcontainer;

    @FXML
    private TextField institution_tf;

    @FXML
    private TextField mdp_tf;

    @FXML
    private TextField nom_tf;

    @FXML
    private TextField username_tf;

    PresidentService presidentService = new PresidentService();

    @FXML
    void signup(ActionEvent event) throws SQLException, IOException {
        if (presidentService.test_Email(email_tf.getText()))
        {
            President president = new President();
            president.setEmail(email_tf.getText());
            president.setNom(nom_tf.getText());
            president.setInstitution(institution_tf.getText());
            president.setMdp(mdp_tf.getText());

            presidentService.ajouterPresident(president);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Ajouté");
            alert.setContentText("Président Ajouté");
            alert.show();

            Parent page1 = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verfiy your email address");
            alert.setContentText("Verfiy your email address");
            alert.show();
        }
    }

    @FXML
    void initialize() {
    }

}

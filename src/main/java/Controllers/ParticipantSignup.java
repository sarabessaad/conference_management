package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Entities.President;
import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ParticipantSignup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField institution_tf;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private ComboBox<String> mode_paiment;

    @FXML
    private TextField nom_tf;

    @FXML
    private TextField mdp_tf;
    private UserService userService = new UserService();

    @FXML
    void signup(ActionEvent event) throws SQLException, IOException {
        if (userService.test_Email(email_tf.getText()))
        {
            User user = new User();
            user.setMail(email_tf.getText());
            user.setNom(nom_tf.getText());
            user.setInstitution(institution_tf.getText());
            user.setMdp(mdp_tf.getText());

            userService.ajouterUser(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Ajouté");
            alert.setContentText("User Ajouté");
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

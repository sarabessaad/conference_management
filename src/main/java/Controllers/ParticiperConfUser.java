package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Entities.Participation;
import Entities.User;
import Services.ParticipationConfService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ParticiperConfUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label dateConf;

    @FXML
    private TextField email_inscri;

    @FXML
    private Label fraisConf;

    @FXML
    private TextField institution_inscri;

    @FXML
    private Label lieuConf;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TextField nom_inscri;

    @FXML
    private ChoiceBox<String> paiement_inscri;

    private UserService userService = new UserService();
    private ParticipationConfService participationConfService = new ParticipationConfService();

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signup(ActionEvent event) throws SQLException, IOException {
        Participation participation = new Participation();
        participation.setId_user(Login.id);
        participation.setId_conference(ItemConfUser.conference.getId_conference());
        participation.setMode_paiement(paiement_inscri.getValue());

        participationConfService.ajouterParticipation(participation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Participation Ajouté");
        alert.setContentText("Participation Ajouté");
        alert.show();

        Parent page1 = FXMLLoader.load(getClass().getResource("/homeUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        User user = userService.rechercheUser(Login.id).get(0);

        nomUserProfile.setText(user.getNom());
        nom_inscri.setText(user.getNom());
        email_inscri.setText(user.getMail());
        institution_inscri.setText(user.getInstitution());

        paiement_inscri.getItems().addAll("Chéque", "Virement bancaire", "Bon de commande", "Espèces");
        paiement_inscri.setValue("Chéque");

        dateConf.setText("De " + ItemConfUser.conference.getDate_debut().toString() + " à " + ItemConfUser.conference.getDate_fin().toString());
        lieuConf.setText(ItemConfUser.conference.getLocal() + " | " + ItemConfUser.conference.getVille() + " | " + ItemConfUser.conference.getPays());
        fraisConf.setText(String.valueOf(ItemConfUser.conference.getFrais()));
    }

}

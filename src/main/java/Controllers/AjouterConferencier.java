package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Entities.ConferencierInvite;
import Entities.President;
import Services.ConferencierInviteService;
import Services.PresidentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterConferencier {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conf_name;

    @FXML
    private TextField email_confer;

    @FXML
    private TextField institution_confer;

    @FXML
    private TextField nom_confer;

    @FXML
    private TextField pays_confer;

    @FXML
    private Label pres_name;

    @FXML
    private TextField titre_prse_confer;

    private PresidentService presidentService = new PresidentService();

    ConferencierInviteService conferencierInviteService = new ConferencierInviteService();

    @FXML
    void ajouter(ActionEvent event) throws SQLException, IOException {
        ConferencierInvite conferencierInvite = new ConferencierInvite();
        conferencierInvite.setNom(nom_confer.getText());
        conferencierInvite.setInstitution(institution_confer.getText());
        conferencierInvite.setPays(pays_confer.getText());
        conferencierInvite.setEmail(email_confer.getText());
        conferencierInvite.setTitre_pres(titre_prse_confer.getText());

        conferencierInviteService.ajouterConferencier(conferencierInvite);
        List<ConferencierInvite> conferencierInvites = conferencierInviteService.afficherConferencier();
        int index = conferencierInvites.size();
        conferencierInviteService.affectConferencierToConference(index, ItemAdmin.conference.getId_conference());

        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void conf_info(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConf.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelParticipation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void article_admin(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/articleAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void list_president(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/listParticipantsPresident.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void stat(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/statAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        President president = presidentService.recherchePres(Login.id).get(0);
        pres_name.setText(president.getNom());
        conf_name.setText(ItemAdmin.conference.getTitre());
    }

}

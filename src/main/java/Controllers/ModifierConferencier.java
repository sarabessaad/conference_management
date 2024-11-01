package Controllers;

import java.io.IOException;
import java.net.URL;
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

public class ModifierConferencier {

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
    private ConferencierInviteService conferencierInviteService = new ConferencierInviteService();

    @FXML
    void conf_info(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConf.fxml"));
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
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
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
    void modifier(ActionEvent event) throws IOException {
        ConferencierInvite conferencierInvite = new ConferencierInvite();
        conferencierInvite.setNom(nom_confer.getText());
        conferencierInvite.setEmail(email_confer.getText());
        conferencierInvite.setPays(pays_confer.getText());
        conferencierInvite.setInstitution(institution_confer.getText());
        conferencierInvite.setTitre_pres(titre_prse_confer.getText());

        conferencierInviteService.modifierConferencier(conferencierInvite, ConfInvitesController.selectedConferencier.getId_conferencier());

        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
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

        nom_confer.setText(ConfInvitesController.selectedConferencier.getNom());
        email_confer.setText(ConfInvitesController.selectedConferencier.getEmail());
        pays_confer.setText(ConfInvitesController.selectedConferencier.getPays());
        institution_confer.setText(ConfInvitesController.selectedConferencier.getInstitution());
        titre_prse_confer.setText(ConfInvitesController.selectedConferencier.getTitre_pres());
    }

}

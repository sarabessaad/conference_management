package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.*;
import Services.ComiteOrgService;
import Services.ComiteScService;
import Services.ConferenceService;
import Services.PresidentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InfoConfController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date_articles;

    @FXML
    private DatePicker date_debut;

    @FXML
    private DatePicker date_fin;

    @FXML
    private DatePicker date_inscri;

    @FXML
    private TextField domain_tf;

    @FXML
    private TextField frais_tf;

    @FXML
    private TextField institution_tf;

    @FXML
    private TextField lieu_tf;

    @FXML
    private ListView<String> listMembreOrg;

    @FXML
    private ListView<String> listMembreSc;

    @FXML
    private Label nom_admin;

    @FXML
    private TextField presdient_tf;

    @FXML
    private Label titre_conf;

    @FXML
    private TextField titre_tf;

    private PresidentService presidentService = new PresidentService();
    private ComiteOrgService comiteOrgService = new ComiteOrgService();
    private ComiteScService comiteScService = new ComiteScService();
    private ConferenceService conferenceService = new ConferenceService();

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
    void conf_invite(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
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
        Conference conference = new Conference();
        conference.setTitre(titre_tf.getText());
        conference.setPresident_id(Login.id);
        conference.setNom_institution(institution_tf.getText());
        conference.setDate_debut(date_debut.getValue());
        conference.setDate_fin(date_fin.getValue());
        conference.setDomaine(domain_tf.getText());
        conference.setDate_articles(date_articles.getValue());
        conference.setDate_inscri(date_inscri.getValue());
        conference.setFrais(Float.parseFloat(frais_tf.getText()));

        conference.setLocal(ItemAdmin.conference.getLocal());
        conference.setVille(ItemAdmin.conference.getVille());
        conference.setPays(ItemAdmin.conference.getPays());

        conference.setImage(ItemAdmin.conference.getImage());

        conferenceService.modifierConference(conference, ItemAdmin.conference.getId_conference());

        Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
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
    void supprimer(ActionEvent event) throws IOException {
        conferenceService.supprimerConference(ItemAdmin.conference.getId_conference());

        Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        President president = presidentService.recherchePres(Login.id).get(0);

        nom_admin.setText(president.getNom());

        titre_conf.setText(ItemAdmin.conference.getTitre());
        titre_tf.setText(ItemAdmin.conference.getTitre());
        institution_tf.setText(ItemAdmin.conference.getNom_institution());
        date_debut.setValue(ItemAdmin.conference.getDate_debut());
        date_fin.setValue(ItemAdmin.conference.getDate_fin());
        date_articles.setValue(ItemAdmin.conference.getDate_articles());
        date_inscri.setValue(ItemAdmin.conference.getDate_inscri());
        lieu_tf.setText(ItemAdmin.conference.getLocal() + " | " + ItemAdmin.conference.getVille() + " | " + ItemAdmin.conference.getPays());
        domain_tf.setText(ItemAdmin.conference.getDomaine());
        frais_tf.setText(String.valueOf(ItemAdmin.conference.getFrais()));

        List<MembreOrg> membreOrgs = comiteOrgService.getMembresOrg(ItemAdmin.conference.getId_conference());

        for (MembreOrg membreOrg : membreOrgs) {
            listMembreOrg.getItems().add(membreOrg.getNom());
        }

        List<MembreSc> membreScs = comiteScService.getMembresSc(ItemAdmin.conference.getId_conference());

        for (MembreSc membreSc : membreScs) {
            listMembreSc.getItems().add(membreSc.getNom());
        }
    }

}

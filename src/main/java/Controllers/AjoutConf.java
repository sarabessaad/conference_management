package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AjoutConf {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date_article;

    @FXML
    private DatePicker date_deb;

    @FXML
    private DatePicker date_fin;

    @FXML
    private DatePicker date_inscri;

    @FXML
    private TextField frais_tf;

    @FXML
    private TextField intitution_tf;

    @FXML
    private ListView<MembreOrg> listComiteOrg;

    @FXML
    private ListView<MembreSc> listComiteSc;

    @FXML
    private TextField local_tf;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TextField pays_tf;

    @FXML
    private TextField president_tf;

    @FXML
    private TextField thematique_tf;

    @FXML
    private TextField titre_tf;

    @FXML
    private TextField ville_tf;

    private ComiteScService comiteScService = new ComiteScService();
    private ComiteOrgService comiteOrgService = new ComiteOrgService();
    private ConferenceService conferenceService = new ConferenceService();
    private PresidentService presidentService = new PresidentService();

    @FXML
    void ajouter(ActionEvent event) throws SQLException, IOException {
        List<MembreOrg> membreOrgs = new ArrayList<>(listComiteOrg.getSelectionModel().getSelectedItems());
        List<MembreSc> membreScs = new ArrayList<>(listComiteSc.getSelectionModel().getSelectedItems());

        Conference conference = new Conference();
        conference.setPresident_id(Login.id);
        conference.setTitre(titre_tf.getText());
        conference.setNom_institution(intitution_tf.getText());
        conference.setDate_debut(date_deb.getValue());
        conference.setDate_fin(date_fin.getValue());
        conference.setDate_articles(date_article.getValue());
        conference.setDate_inscri(date_inscri.getValue());
        conference.setPays(pays_tf.getText());
        conference.setVille(ville_tf.getText());
        conference.setLocal(local_tf.getText());
        conference.setDomaine(thematique_tf.getText());
        conference.setFrais(Float.parseFloat(frais_tf.getText()));

        if (conferenceService.isDateAfterToday(conference.getDate_debut()) &&
            conferenceService.isDateFinAfterDateDeb(conference.getDate_fin(), conference.getDate_debut()) &&
            conferenceService.isDateBetween(conference.getDate_articles(), conference.getDate_debut(), conference.getDate_fin()) &&
            conferenceService.isDateBetween(conference.getDate_inscri(), conference.getDate_debut(), conference.getDate_fin()))
        {
            conferenceService.ajouterConference(conference);

            List<Conference> conferences = conferenceService.afficherConf();
            int index = conferences.size()-1;
            int id_con = conferences.get(index).getId_conference();

            comiteOrgService.affectMembreOrgToConference(membreOrgs, id_con);
            comiteScService.affectMembreScToConference(membreScs, id_con);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conference ajoutée");
            alert.setContentText("Conference ajoutée");
            alert.show();

            Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("False !!");
            alert.setContentText("Cehck Dates");
            alert.show();
        }

    }

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
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        President president = presidentService.recherchePres(Login.id).get(0);

        nomUserProfile.setText(president.getNom());
        president_tf.setText(president.getNom());

        listComiteSc.getItems().addAll(comiteScService.afficherMembresSc());
        listComiteSc.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listComiteOrg.getItems().addAll(comiteOrgService.afficherMembresOrg());
        listComiteOrg.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

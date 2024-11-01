package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.MembreOrg;
import Entities.MembreSc;
import Entities.President;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InfoConfComController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conf_name;

    @FXML
    private TextField date_article;

    @FXML
    private TextField date_deb;

    @FXML
    private TextField date_fin;

    @FXML
    private TextField date_inscri;

    @FXML
    private TextField frais_tf;

    @FXML
    private TextField institution_tf;

    @FXML
    private TextField lieu_tf;

    @FXML
    private ListView<String > list_membresOrg;

    @FXML
    private ListView<String> list_membresSc;

    @FXML
    private Label membre_name;

    @FXML
    private TextField thematique_tf;

    @FXML
    private TextField titre_tf;

    @FXML
    private TextField president_tf;

    private ComiteOrgService comiteOrgService = new ComiteOrgService();
    private ComiteScService comiteScService = new ComiteScService();
    private ConferenceService conferenceService = new ConferenceService();
    private PresidentService presidentService = new PresidentService();

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelPartCom.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void conf_inv(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvCom.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eva_article(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/evalArticleCom.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeMembreSc.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void stat(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/stat.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        MembreSc membreSc = comiteScService.rechercheMembreSc(Login.id).get(0);

        President president = presidentService.recherchePres(ItemMembreSc.conference.getPresident_id()).get(0);

        membre_name.setText(membreSc.getNom());
        president_tf.setText(president.getNom());
        conf_name.setText(ItemMembreSc.conference.getTitre());
        titre_tf.setText(ItemMembreSc.conference.getTitre());
        institution_tf.setText(ItemMembreSc.conference.getNom_institution());
        date_deb.setText(ItemMembreSc.conference.getDate_debut().toString());
        date_fin.setText(ItemMembreSc.conference.getDate_fin().toString());
        date_article.setText(ItemMembreSc.conference.getDate_articles().toString());
        date_inscri.setText(ItemMembreSc.conference.getDate_inscri().toString());
        lieu_tf.setText(ItemMembreSc.conference.getLocal() + " | " + ItemMembreSc.conference.getVille() + " | " + ItemMembreSc.conference.getPays());
        thematique_tf.setText(ItemMembreSc.conference.getDomaine());
        frais_tf.setText(String.valueOf(ItemMembreSc.conference.getFrais()));

        List<MembreOrg> membreOrgs = comiteOrgService.getMembresOrg(ItemMembreSc.conference.getId_conference());

        for (MembreOrg membreOrg : membreOrgs) {
            list_membresOrg.getItems().add(membreOrg.getNom());
        }

        List<MembreSc> membreScs = comiteScService.getMembresSc(ItemMembreSc.conference.getId_conference());

        for (MembreSc membre : membreScs) {
            list_membresSc.getItems().add(membre.getNom());
        }
    }

}

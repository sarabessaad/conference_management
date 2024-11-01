package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.MembreOrg;
import Entities.MembreSc;
import Entities.President;
import Entities.User;
import Services.ComiteOrgService;
import Services.ComiteScService;
import Services.PresidentService;
import Services.UserService;
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

public class DetailsConfUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField DateFinConf;

    @FXML
    private TextField dateDebutConf;

    @FXML
    private TextField dateInscriConf;

    @FXML
    private TextField dateSoumissionConf;

    @FXML
    private TextField fraisConf;

    @FXML
    private TextField institutionConf;

    @FXML
    private TextField lieuConf;

    @FXML
    private ListView<String> listeComOrgConf;

    @FXML
    private ListView<String> listeComScConf;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TextField presConf;

    @FXML
    private TextField thematiquesConf;

    @FXML
    private TextField titreConf;

    private PresidentService presidentService = new PresidentService();
    private ComiteOrgService comiteOrgService = new ComiteOrgService();
    private ComiteScService comiteScService = new ComiteScService();
    private UserService userService = new UserService();

    @FXML
    void initialize() {
        User user = userService.rechercheUser(Login.id).get(0);

        nomUserProfile.setText(user.getNom());

        titreConf.setText(ItemConfUser.conference.getTitre());

        President president = presidentService.recherchePres(ItemConfUser.conference.getPresident_id()).get(0);
        presConf.setText(president.getNom());

        institutionConf.setText(ItemConfUser.conference.getNom_institution());
        dateDebutConf.setText(ItemConfUser.conference.getDate_debut().toString());
        DateFinConf.setText(ItemConfUser.conference.getDate_fin().toString());
        lieuConf.setText(ItemConfUser.conference.getLocal() + " | " + ItemConfUser.conference.getVille() + " | " + ItemConfUser.conference.getPays());
        thematiquesConf.setText(ItemConfUser.conference.getDomaine());
        dateSoumissionConf.setText(ItemConfUser.conference.getDate_articles().toString());
        dateInscriConf.setText(ItemConfUser.conference.getDate_inscri().toString());
        fraisConf.setText(String.valueOf(ItemConfUser.conference.getFrais()));

        List<MembreOrg> membreOrgs = comiteOrgService.getMembresOrg(ItemConfUser.conference.getId_conference());

        for (MembreOrg membreOrg : membreOrgs) {
            listeComOrgConf.getItems().add(membreOrg.getNom());
        }

        List<MembreSc> membreScs = comiteScService.getMembresSc(ItemConfUser.conference.getId_conference());

        for (MembreSc membreSc : membreScs) {
            listeComScConf.getItems().add(membreSc.getNom());
        }

    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

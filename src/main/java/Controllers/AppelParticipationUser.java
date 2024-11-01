package Controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppelParticipationUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField date_debut;

    @FXML
    private TextField date_fin;

    @FXML
    private TextField date_limite_article;

    @FXML
    private TextField date_limite_inscri;

    @FXML
    private TextField frais_inscri;

    @FXML
    private TextField institutionOrg;

    @FXML
    private TextField lieuConf;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TextField thematique;

    @FXML
    private TextField titreConf;

    private UserService userService = new UserService();

    @FXML
    void imprimer(ActionEvent event) {

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

        titreConf.setText(ItemConfUser.conference.getTitre());
        institutionOrg.setText(ItemConfUser.conference.getNom_institution());
        date_debut.setText(ItemConfUser.conference.getDate_debut().toString());
        date_fin.setText(ItemConfUser.conference.getDate_fin().toString());
        lieuConf.setText(ItemConfUser.conference.getLocal() + " | " + ItemConfUser.conference.getVille() + " | " + ItemConfUser.conference.getPays());
        thematique.setText(ItemConfUser.conference.getDomaine());
        date_limite_article.setText(ItemConfUser.conference.getDate_articles().toString());
        date_limite_inscri.setText(ItemConfUser.conference.getDate_inscri().toString());
        frais_inscri.setText(String.valueOf(ItemConfUser.conference.getFrais()));
    }

}

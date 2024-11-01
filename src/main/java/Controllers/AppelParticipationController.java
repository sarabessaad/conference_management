package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.President;
import Entities.User;
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

public class AppelParticipationController {

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
    private TextField frais;

    @FXML
    private TextField institution_tf;

    @FXML
    private TextField lieu_tf;

    @FXML
    private Label pres_name;

    @FXML
    private TextField thematique_tf;

    @FXML
    private TextField titre_tf;

    private PresidentService presidentService = new PresidentService();

    @FXML
    void imprimer(ActionEvent event) {

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
        pres_name.setText(president.getNom());

        conf_name.setText(ItemAdmin.conference.getTitre());

        titre_tf.setText(ItemAdmin.conference.getTitre());
        institution_tf.setText(ItemAdmin.conference.getNom_institution());
        date_deb.setText(ItemAdmin.conference.getDate_debut().toString());
        date_fin.setText(ItemAdmin.conference.getDate_fin().toString());
        lieu_tf.setText(ItemAdmin.conference.getLocal() + " | " + ItemAdmin.conference.getVille() + " | " + ItemAdmin.conference.getPays());
        thematique_tf.setText(ItemAdmin.conference.getDomaine());
        date_article.setText(ItemAdmin.conference.getDate_articles().toString());
        date_inscri.setText(ItemAdmin.conference.getDate_inscri().toString());
        frais.setText(String.valueOf(ItemAdmin.conference.getFrais()));
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
    void conf_info(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConf.fxml"));
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
    void conf_invite(ActionEvent event) throws IOException {
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

}

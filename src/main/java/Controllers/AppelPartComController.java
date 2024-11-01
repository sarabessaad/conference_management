package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.MembreOrg;
import Entities.MembreSc;
import Services.ComiteScService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppelPartComController {

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
    private Label membre_name;

    @FXML
    private TextField thematique_tf;

    @FXML
    private TextField titre_tf;

    private ComiteScService comiteScService = new ComiteScService();

    @FXML
    void conf_info(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConfCom.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void conferencier_inv(ActionEvent event) throws IOException {
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
    void imprimer(ActionEvent event) {

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

        membre_name.setText(membreSc.getNom());

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
    }

}

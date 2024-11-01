package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Conference;
import Entities.User;
import Services.ConferenceService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemConfUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView photoConf;

    @FXML
    private Label titreConf;

    static Conference conference;
    private ConferenceService conferenceService = new ConferenceService();
    private MyListener myListener;
    private int id;

    @FXML
    void initialize() {

    }

    public void setData(int id_conference, String titre, String nom_institution, LocalDate date_debut, LocalDate date_fin, String local, String ville, String pays, String domaine, LocalDate date_articles, LocalDate date_inscri, float frais, int comite_org_id, int comite_sc_id, int president_id, String image, MyListener myListener) {

        this.id = id_conference;
        this.myListener = myListener;
        titreConf.setText(titre);

        String fullurl = "C:\\xampp\\htdocs\\conf_images\\" + image;
        System.out.println("full url " + fullurl);

        try {
            photoConf.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }

    public interface MyListener {
        void onClick(Conference conference);
    }

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/appelParticipationUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void conference_invite(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/conferenciersInvUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void details_conference(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/detailsConfUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void participer_conference(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/partciperConfUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void soumettre_article(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/soumettreArticleUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

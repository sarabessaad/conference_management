package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.Conference;
import Entities.StatusArticle;
import Services.ConferenceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView photoConf;

    @FXML
    private Button titre_conf;

    static Conference conference;
    private ConferenceService conferenceService = new ConferenceService();
    private MyListener myListener;
    private int id;

    @FXML
    void initialize() {
    }

    @FXML
    void dashboard(ActionEvent event) throws IOException {
        conference = conferenceService.rechercheConf(id).get(0);

        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConf.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setData(int id_conference, String titre, String nom_institution, LocalDate date_debut, LocalDate date_fin, String local, String ville, String pays, String domaine, LocalDate date_articles, LocalDate date_inscri, float frais, int comite_org_id, int comite_sc_id, int president_id, String image, MyListener myListener) {

        this.id = id_conference;
        this.myListener = myListener;
        titre_conf.setText(titre);

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

}

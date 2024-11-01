package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.Conference;
import Entities.StatusArticle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class ItemArticleUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label etat_article;

    @FXML
    private Label titre_article;

    private MyListener myListener;
    private int id;

    @FXML
    void initialize() {
    }

    public void setData(int id_article, String titre, String nom_pdf, String emplacement_pdf, StatusArticle status, int conference_id, int membre_sc_id, MyListener myListener) {

        this.id = id_article;
        this.myListener = myListener;
        titre_article.setText(titre);
        etat_article.setText(status.toString());
    }

    public interface MyListener {
        void onClick(Article article);
    }

}

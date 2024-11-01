package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.StatusArticle;
import Entities.User;
import Services.ArticleService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SoumettreArticleUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_auteur;

    @FXML
    private TextField emplacement_pdf;

    @FXML
    private TextField institution_auteur;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TextField nom_auteur;

    @FXML
    private TextField nom_pdf;

    @FXML
    private TextField titre_article;

    private UserService userService = new UserService();
    private ArticleService articleService = new ArticleService();

    private Article article = new Article();

    List<Integer> auteurs_ids = new ArrayList<>();

    @FXML
    void ajouter_auteur(ActionEvent event) {
        List<User> auteur = userService.rechercheAuteur(nom_auteur.getText(), email_auteur.getText(), institution_auteur.getText());
        if (!auteur.isEmpty())
        {
            auteurs_ids.add(auteur.get(0).getId_user());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Auteur n'existe pas");
            alert.setContentText("Auteur n'existe pas");
            alert.show();
        }

        nom_auteur.setText("");
        email_auteur.setText("");
        institution_auteur.setText("");
    }

    @FXML
    void envoyer(ActionEvent event) throws SQLException, IOException {
        article.setTitre(titre_article.getText());
        article.setNom_pdf(nom_pdf.getText());
        article.setEmplacement_pdf(emplacement_pdf.getText());
        article.setStatus(StatusArticle.NA);
        article.setConference_id(ItemConfUser.conference.getId_conference());

        articleService.ajouterArticle(article);

        List<Article> articles = articleService.afficherArticle();
        int id_article = articles.size();

        for (int i=0; i<auteurs_ids.size(); i++)
        {
            userService.ajouterAuteur(auteurs_ids.get(i), id_article);
        }

        auteurs_ids = null;

        Parent page1 = FXMLLoader.load(getClass().getResource("/homeUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    }

}

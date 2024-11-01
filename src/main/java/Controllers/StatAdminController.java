package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.President;
import Services.ArticleService;
import Services.ComiteScService;
import Services.PresidentService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StatAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Article, String> auteurs_article;

    @FXML
    private Label conf_name;

    @FXML
    private Label nbrPapAcc;

    @FXML
    private Label nbrPapSoumis;

    @FXML
    private Label pres_name;

    @FXML
    private TableView<Article> tableView;

    @FXML
    private TableColumn<Article, String> titre_article;

    private PresidentService presidentService = new PresidentService();
    private ArticleService articleService = new ArticleService();

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelParticipation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    void conf_invite(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/confInvites.fxml"));
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

        titre_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        auteurs_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuteurs().toString()));

        List<Article> articleList = articleService.afficherArticle();

        for (Article article: articleList)
        {
            article.setAuteurs(articleService.GetAuteursArticle(article.getId_article()));
        }

        ObservableList<Article> articles = FXCollections.observableArrayList(articleList);
        tableView.setItems(articles);

        nbrPapSoumis.setText(String.valueOf(articleService.afficherArticle().size()));
        nbrPapAcc.setText(String.valueOf(articleService.afficherArticleAcc().size()));
    }

}

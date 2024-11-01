package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.MembreSc;
import Services.ArticleService;
import Services.ComiteScService;
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

public class StatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Article, String> auteurs;

    @FXML
    private Label conf_name;

    @FXML
    private Label membre_name;

    @FXML
    private Label nbrPapAcc;

    @FXML
    private Label nbrPapSoumis;

    @FXML
    private TableView<Article> tableView;

    @FXML
    private TableColumn<Article, String> titre;

    private ArticleService articleService = new ArticleService();
    private ComiteScService comiteScService = new ComiteScService();

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelPartCom.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void conf_info(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConfCom.fxml"));
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
    void initialize() {
        MembreSc membreSc = comiteScService.rechercheMembreSc(Login.id).get(0);

        membre_name.setText(membreSc.getNom());

        conf_name.setText(ItemMembreSc.conference.getTitre());

        titre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        auteurs.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuteurs().toString()));


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

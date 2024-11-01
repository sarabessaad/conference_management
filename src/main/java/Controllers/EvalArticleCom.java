package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.MembreSc;
import Entities.President;
import Entities.StatusArticle;
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

public class EvalArticleCom {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Article, String> auteurs;

    @FXML
    private Label conf_name;

    @FXML
    private TableColumn<Article, String> decision;

    @FXML
    private Label member_name;

    @FXML
    private TableColumn<Article, String> nom;

    @FXML
    private TableColumn<Article, String> pdf;

    @FXML
    private TableView<Article> tableView;

    private ComiteScService comiteScService = new ComiteScService();
    private ArticleService articleService = new ArticleService();

    @FXML
    void accepter(ActionEvent event) {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
        selectedArticle.setStatus(StatusArticle.ACC);
        articleService.modifierArticle(selectedArticle,selectedArticle.getId_article());

        tableView.refresh();
    }

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
    void rejeter(ActionEvent event) {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
        selectedArticle.setStatus(StatusArticle.REJ);
        articleService.modifierArticle(selectedArticle,selectedArticle.getId_article());

        tableView.refresh();
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

        member_name.setText(membreSc.getNom());

        conf_name.setText(ItemMembreSc.conference.getTitre());

        nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        pdf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmplacement_pdf()));
        decision.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));

        ObservableList<Article> articles = FXCollections.observableArrayList(articleService.afficherArticleConf(ItemMembreSc.conference.getId_conference()));
        tableView.setItems(articles);
    }

}

package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controllers.ItemAdmin;
import Controllers.Login;
import Entities.*;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ArticleAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conf_name;

    @FXML
    private TableColumn<Article, String> etat_article;

    @FXML
    private ComboBox<MembreSc> listMembreSc;

    @FXML
    private TableColumn<Article, String> nom_article;

    @FXML
    private TableColumn<Article, String> pdf_article;

    @FXML
    private Label pres_name;

    @FXML
    private TableView<Article> tableView;

    private PresidentService presidentService = new PresidentService();
    private ComiteScService comiteScService = new ComiteScService();
    private ArticleService articleService = new ArticleService();

    @FXML
    void affect(ActionEvent event) {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();

        MembreSc selectedMember = listMembreSc.getValue();

        if (selectedArticle != null && selectedMember != null) {

            selectedArticle.setMembre_sc_id(selectedMember.getId_membreSc());
            selectedArticle.setStatus(StatusArticle.UE);

            articleService.affectArticleToMembreSc(selectedArticle, selectedArticle.getId_article());

            tableView.refresh();

            System.out.println("Article " + selectedArticle.getTitre() + " assigned to " + selectedMember.getNom());
        } else {

            System.out.println("Please select both an article and a member to assign.");
        }
    }

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelParticipation.fxml"));
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
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
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

    @FXML
    void initialize() {
        President president = presidentService.recherchePres(Login.id).get(0);

        pres_name.setText(president.getNom());

        conf_name.setText(ItemAdmin.conference.getTitre());

        nom_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        pdf_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmplacement_pdf()));
        etat_article.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));

        ObservableList<Article> articles = FXCollections.observableArrayList(articleService.afficherArticleConf(ItemAdmin.conference.getId_conference()));
        tableView.setItems(articles);

        ObservableList<MembreSc> membreScList = FXCollections.observableArrayList();

        membreScList.addAll(comiteScService.afficherMembresSc());

        listMembreSc.setItems(membreScList);

        listMembreSc.getSelectionModel().selectFirst();
    }

}

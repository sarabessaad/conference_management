package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.ComiteSc;
import Entities.ConferencierInvite;
import Entities.MembreSc;
import Entities.President;
import Services.ComiteScService;
import Services.ConferencierInviteService;
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

public class ConfInvCom {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conf_name;

    @FXML
    private TableColumn<ConferencierInvite, String> email;

    @FXML
    private TableColumn<ConferencierInvite, String> institution;

    @FXML
    private Label member_name;

    @FXML
    private TableColumn<ConferencierInvite, String> nom;

    @FXML
    private TableColumn<ConferencierInvite, String> pays;

    @FXML
    private TableView<ConferencierInvite> tableView;

    @FXML
    private TableColumn<ConferencierInvite, String> titre;

    private ComiteScService comiteScService = new ComiteScService();
    private ConferencierInviteService conferencierInviteService = new ConferencierInviteService();

    @FXML
    void appel_participation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/appelPartCom.fxml"));
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
    void info_conf(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/infoConfCom.fxml"));
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

        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        institution.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstitution()));
        nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        pays.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPays()));
        titre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre_pres()));

        ObservableList<ConferencierInvite> conferencierInvites = FXCollections.observableArrayList(conferencierInviteService.GetConferencierInvite(ItemMembreSc.conference.getId_conference()));
        tableView.setItems(conferencierInvites);
    }

}

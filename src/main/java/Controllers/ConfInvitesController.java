package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.ConferencierInvite;
import Entities.President;
import Entities.User;
import Services.ConferenceService;
import Services.ConferencierInviteService;
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

public class ConfInvitesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conf_name;

    @FXML
    private Label pres_name;

    @FXML
    private TableView<ConferencierInvite> tableView;

    @FXML
    private TableColumn<ConferencierInvite, String> email_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> institution_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> nom_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> pays_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> titre_conferencier;

    private PresidentService presidentService = new PresidentService();
    private ConferencierInviteService conferencierInviteService = new ConferencierInviteService();
    static ConferencierInvite selectedConferencier;

    @FXML
    void ajouter_conferencier(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/ajouterConferencier.fxml"));
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
    void list_president(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/listParticipantsPresident.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {

        selectedConferencier = tableView.getSelectionModel().getSelectedItem();

        Parent page1 = FXMLLoader.load(getClass().getResource("/modifierConferencier.fxml"));
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
    void supprimer(ActionEvent event) {
        ConferencierInvite selectedConferencier = tableView.getSelectionModel().getSelectedItem();

        if (selectedConferencier != null) {
            tableView.getItems().remove(selectedConferencier);

            conferencierInviteService.supprimerConferencierInvite(selectedConferencier.getId_conferencier()); // Assuming you have a method to delete a conferencier
        } else {
            System.out.println("No conferencier selected for deletion.");
        }
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

        email_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        institution_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstitution()));
        nom_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        pays_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPays()));
        titre_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre_pres()));

        ObservableList<ConferencierInvite> conferencierInvites = FXCollections.observableArrayList(conferencierInviteService.GetConferencierInvite(ItemAdmin.conference.getId_conference()));
        tableView.setItems(conferencierInvites);
    }

}

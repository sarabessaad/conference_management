package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.ConferencierInvite;
import Entities.User;
import Services.ConferencierInviteService;
import Services.UserService;
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

public class ConferenciersInvUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ConferencierInvite, String> email_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> institution_conferencier;

    @FXML
    private Label nomUserProfile;

    @FXML
    private TableColumn<ConferencierInvite, String> nom_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> pays_conferencier;

    @FXML
    private TableColumn<ConferencierInvite, String> presentation_conferencier;

    @FXML
    private TableView<ConferencierInvite> tableView;

    private ConferencierInviteService conferencierInviteService = new ConferencierInviteService();
    private UserService userService = new UserService();

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

        email_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        institution_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstitution()));
        nom_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        pays_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPays()));
        presentation_conferencier.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre_pres()));

        ObservableList<ConferencierInvite> conferencierInvites = FXCollections.observableArrayList(conferencierInviteService.GetConferencierInvite(ItemConfUser.conference.getId_conference()));
        tableView.setItems(conferencierInvites);

    }

}

package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Conference;
import Entities.User;
import Services.ConferenceService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class HomeUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label nom_user;

    private UserService userService = new UserService();
    private List<Conference> ConfDataList = FXCollections.observableArrayList();
    private ConferenceService conferenceService = new ConferenceService();
    private ItemConfUser.MyListener myListener;

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void articles(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/suiviArticleUser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        User user = userService.rechercheUser(Login.id).get(0);

        nom_user.setText(user.getNom());

        ConfDataList.addAll(conferenceService.afficherConf());
        System.out.println("load data");
        if (ConfDataList.size() > 0) {
            myListener = new ItemConfUser.MyListener() {
                @Override
                public void onClick(Conference conference) {

                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < ConfDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/itemConfUser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemConfUser item = fxmlLoader.getController();
                item.setData(ConfDataList.get(i).getId_conference(), ConfDataList.get(i).getTitre(), ConfDataList.get(i).getNom_institution(), ConfDataList.get(i).getDate_debut(), ConfDataList.get(i).getDate_fin(), ConfDataList.get(i).getLocal(), ConfDataList.get(i).getVille(), ConfDataList.get(i).getPays(), ConfDataList.get(i).getDomaine(), ConfDataList.get(i).getDate_articles(), ConfDataList.get(i).getDate_inscri(), ConfDataList.get(i).getFrais(), ConfDataList.get(i).getComite_org_id(), ConfDataList.get(i).getComite_sc_id(), ConfDataList.get(i).getPresident_id(), ConfDataList.get(i).getImage(), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("problem");
            }
        }
    }

}

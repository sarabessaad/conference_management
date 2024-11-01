package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.Conference;
import Entities.User;
import Services.ArticleService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SuiviArticleUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label nomUserProfile;

    private List<Article> ArticlesDataList = FXCollections.observableArrayList();
    private UserService userService = new UserService();
    private ArticleService articleService = new ArticleService();
    private ItemArticleUser.MyListener myListener;

    @FXML
    void retour(ActionEvent event) throws IOException {
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
    void initialize() {
        User user = userService.rechercheUser(Login.id).get(0);

        nomUserProfile.setText(user.getNom());

        ArticlesDataList.addAll(articleService.GetArticlesAuteur(Login.id));
        System.out.println("load data");
        if (ArticlesDataList.size() > 0) {
            myListener = new ItemArticleUser.MyListener() {
                @Override
                public void onClick(Article article) {

                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < ArticlesDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/itemArticleUser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemArticleUser item = fxmlLoader.getController();
                item.setData(ArticlesDataList.get(i).getId_article(), ArticlesDataList.get(i).getTitre(), ArticlesDataList.get(i).getNom_pdf(), ArticlesDataList.get(i).getEmplacement_pdf(), ArticlesDataList.get(i).getStatus(), ArticlesDataList.get(i).getConference_id(), ArticlesDataList.get(i).getMembre_sc_id() , myListener);

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

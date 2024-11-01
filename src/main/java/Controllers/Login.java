package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_tf;

    @FXML
    private AnchorPane formcontainer;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private Label label21;

    @FXML
    private TextField mdp_tf;

    @FXML
    private ChoiceBox<String> role;

    static int id;

    @FXML
    void login(ActionEvent event) throws IOException {
        String email = email_tf.getText();
        String password = mdp_tf.getText();

        Connection connection = DataSource.getInstance().getCnx();  // Get connection

        try {
            if (role.getValue().equals("president"))
            {
                String query = "SELECT * FROM president WHERE email = ? AND mdp = ?";  // Query
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();  // Execute query

                if (resultSet.next())
                {
                    id = resultSet.getInt("id_president");
                    Parent page1 = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {  // User not found
                    System.out.println("Invalid credentials.");  // Display error message
                    // ... handle incorrect credentials (e.g., display error message to user)
                }

            } else if (role.getValue().equals("user")) {
                String query = "SELECT * FROM user WHERE mail = ? AND mdp = ?";  // Query
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();  // Execute query

                if (resultSet.next())
                {
                    id = resultSet.getInt("id_user");
                    Parent page1 = FXMLLoader.load(getClass().getResource("/homeUser.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {  // User not found
                    System.out.println("Invalid credentials.");  // Display error message
                    // ... handle incorrect credentials (e.g., display error message to user)
                }
            }else {
                String query = "SELECT * FROM membre_sc WHERE email = ? AND mdp = ?";  // Query
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();  // Execute query

                if (resultSet.next())
                {
                    id = resultSet.getInt("id_membre_sc");
                    Parent page1 = FXMLLoader.load(getClass().getResource("/homeMembreSc.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {  // User not found
                    System.out.println("Invalid credentials.");  // Display error message
                    // ... handle incorrect credentials (e.g., display error message to user)
                }
            }


        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    @FXML
    void initialize() {
        role.getItems().addAll("president", "user", "membre_sc");
    }

}

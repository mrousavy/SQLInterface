package Controller;

import Driver.Driver;
import JavaLogger.JLogger;
import JavaLogger.Logger;
import SQLInterface.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    private Logger<String> _logger = new JLogger(System.out);

    @FXML
    public TextField userField;
    @FXML
    public TextField passwordField;

    @FXML
    public void loginButton(ActionEvent event) {
        String database = "tgmbank";
        String server = "172.17.0.2:3306";
        String user = userField.getText();
        String password = passwordField.getText();

        try {
            Main._connection = Driver.connect(server, database, user, password);
            _logger.Log(Logger.Severity.Info, "Connected!");

            // TODO: Or show tables.fxml with 1000x500 dim
            Parent root = FXMLLoader.load(getClass().getResource("../SQLInterface/persanzedit.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Showing tables for " + database);
            stage.setScene(new Scene(root, 200, 200));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (SQLException ex) {
            _logger.Log(ex);
            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Could not login with the user \"" + user + "\"!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (IOException ex) {
            _logger.Log(Logger.Severity.Error, "Cannot create tables Window!");
            ex.printStackTrace();
        }
    }
}

package Controller;

import Driver.Driver;
import JavaLogger.JLogger;
import JavaLogger.Logger;
import SQLInterface.Controller;
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
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    private Logger<String> _logger = new JLogger(System.out);
    private Connection _connection;

    @FXML
    public TextField userField;
    @FXML
    public TextField passwordField;

    @FXML
    public void loginButton(ActionEvent event) {
        String database = "restaurant4a2017";
        String server = "127.0.0.1:3306";
        String user = userField.getText();
        String password = userField.getText();

        try {
            _connection = Driver.connect(server, database, user, password);
            _logger.Log(Logger.Severity.Info, "Connected!");

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/SQLInterface/tables.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Showing tables for " + database);
            stage.setScene(new Scene(root, 450, 450));
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
        }
    }
}

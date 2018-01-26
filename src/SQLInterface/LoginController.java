package SQLInterface;

import Driver.Driver;
import JavaLogger.JLogger;
import JavaLogger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    private Logger<String> _logger = new JLogger(System.out);
    private Connection _connection;

    @FXML
    public void loginButton(ActionEvent event) {
        String user = "insy4";
        String password = "blabla";
        String database = "restaurant4a2017";
        String server = "127.0.0.1:3306";

        try {
            _connection = Driver.connect(server, database, user, password);
            _logger.Log(Logger.Severity.Info, "Connected!");
        } catch (SQLException ex) {
            _logger.Log(ex);
            ex.printStackTrace();
        }
    }
}

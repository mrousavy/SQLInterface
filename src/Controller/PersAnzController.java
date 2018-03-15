package Controller;

import Driver.Driver;
import SQLInterface.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PersAnzController {
    private static final int KONTO_NR = 1;
    private int version = 0;

    @FXML
    public TextField textField;

    private void versionErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error updating");
        alert.setHeaderText("Could not update kontostand on table konto.");
        alert.setContentText("The value could not be updated on the database, " +
                "because changes have already been made somewhere else");
        alert.showAndWait();
    }

    @FXML
    public void updateClick(ActionEvent event) {
        try {
            int version = Driver.getVersion(Main._connection, KONTO_NR);
            if (this.version == version)
                Driver.updateKontoStand(Main._connection, KONTO_NR, Integer.parseInt(textField.getText()));
            else
                versionErrorDialog();
            this.version = Driver.getVersion(Main._connection, KONTO_NR);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error updating");
            alert.setHeaderText("Could not update kontostand on table konto.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void fetchClick(ActionEvent event) {
        try {
            textField.setText(String.valueOf(Driver.getKontoStand(Main._connection, KONTO_NR)));
            version = Driver.getVersion(Main._connection, KONTO_NR);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fetching");
            alert.setHeaderText("Could not fetch kontostand from table konto.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}

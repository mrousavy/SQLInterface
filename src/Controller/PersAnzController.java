package Controller;

import Driver.Driver;
import SQLInterface.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PersAnzController {
    private int version = 0;

    @FXML
    public TextField textField;
    @FXML
    public TextField idField;

    private int getKontoNr() {
        return Integer.parseInt(idField.getText());
    }

    private void versionErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error updating");
        alert.setHeaderText("Could not update kontostand on table konto.");
        alert.setContentText("Changes have already been made somewhere else");
        alert.showAndWait();
    }

    private void deletedErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error updating");
        alert.setHeaderText("Could not update kontostand on table konto.");
        alert.setContentText("Value already deleted somewhere else");
        alert.showAndWait();
    }

    @FXML
    public void deleteClick(ActionEvent event) {
        try {
            if (!Driver.kontoExists(Main._connection, getKontoNr())) {
                deletedErrorDialog();
                return;
            }
            int version = Driver.getVersion(Main._connection, getKontoNr());
            if (this.version == version)
                Driver.deleteKonto(Main._connection, getKontoNr());
            else
                versionErrorDialog();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error updating");
            alert.setHeaderText("Could not delete konto.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void updateClick(ActionEvent event) {
        try {
            if (!Driver.kontoExists(Main._connection, getKontoNr())) {
                deletedErrorDialog();
                return;
            }
            int version = Driver.getVersion(Main._connection, getKontoNr());
            if (this.version == version)
                Driver.updateKontoStand(Main._connection, getKontoNr(), Integer.parseInt(textField.getText()));
            else
                versionErrorDialog();
            this.version = Driver.getVersion(Main._connection, getKontoNr());
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
            if (!Driver.kontoExists(Main._connection, getKontoNr())) {
                deletedErrorDialog();
                return;
            }
            textField.setText(String.valueOf(Driver.getKontoStand(Main._connection, getKontoNr())));
            version = Driver.getVersion(Main._connection, getKontoNr());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fetching");
            alert.setHeaderText("Could not fetch kontostand from table konto.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}

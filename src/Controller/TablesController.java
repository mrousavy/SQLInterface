package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TablesController {
    private final ObservableList<ReservationModel> data =
            FXCollections.observableArrayList(new ReservationModel("0","0","0","0","0"));

    @FXML
    public TableView table;

    public TablesController() {
        super();
        table.setItems(data);
    }


    @FXML
    public void update(ActionEvent event) {
        data.add(new ReservationModel("test", "test", "test", "test", "test"));
    }
}

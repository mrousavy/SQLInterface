package Controller;

import javafx.beans.property.SimpleStringProperty;

public class ReservationModel {
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty tableNr;
    private final SimpleStringProperty billNr;
    private final SimpleStringProperty billStatus;

    public ReservationModel(String date, String time, String tableNr,
                             String billNr, String billStatus) {
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.tableNr = new SimpleStringProperty(tableNr);
        this.billNr = new SimpleStringProperty(billNr);
        this.billStatus = new SimpleStringProperty(billStatus);
    }

    public String getDate() {
        return date.get();
    }
    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }

    public String getTableNr() {
        return tableNr.get();
    }
    public void setTableNr(String tableNr) {
        this.tableNr.set(tableNr);
    }

    public String getBillNr() {
        return billNr.get();
    }
    public void setBillNr(String billNr) {
        this.billNr.set(billNr);
    }

    public String getBillStatus() {
        return billStatus.get();
    }
    public void setBillStatus(String billStatus) {
        this.billStatus.set(billStatus);
    }
}

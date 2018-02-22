package SQLInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    public static Connection _connection;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login - SQLInterface");
        primaryStage.setScene(new Scene(root, 420, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

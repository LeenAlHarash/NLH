package TP3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author my Benjamin Melis
 */

public class TP2Admin extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TP2Admin.class.getResource("interfaceAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion des tâches administratives");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
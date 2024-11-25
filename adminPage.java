package projet;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Benjamin Melis
 */

public class adminPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(adminPage.class.getResource("interfaceAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion des tâches administratives");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

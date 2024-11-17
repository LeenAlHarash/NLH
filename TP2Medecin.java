package TP3;

// Importations
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Hanfaoui Mariyam
 */

public class TP2Medecin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TP2Medecin.class.getResource("interfaceMedecin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion des patients du médecin");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}

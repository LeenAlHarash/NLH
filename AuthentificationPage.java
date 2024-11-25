package projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Leen Al Harash
 */

public class AuthentificationPage extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AuthentificationPage.class.getResource("interfaceAuthentification.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Page d'authentification");
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

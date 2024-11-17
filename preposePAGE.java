package TP3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Leen Al Harash
 */

public class preposePAGE extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(preposePAGE.class.getResource("PreposeSAMPLE.fxml")); 
        Scene scene = new Scene(fxmlLoader.load()); 
        stage.setTitle("Page de gestion de préposé");
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
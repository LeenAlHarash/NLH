package TP3;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
import javafx.scene.control.Label;
//import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * @author Leen Al Harash
 */

public class AuthenController {
    
    @FXML
    private Label label;
    
    @FXML 
    private TextField userN;
    
    @FXML 
    private TextField pass;
//    
//    @FXML
//    private TabPane tp;
    /**********************************************************************/
    
    //mdp pour les trois personnes
    @FXML 
    void infos(ActionEvent event) {
        label.setText("admin-admin0" + "\nmedecin-medecin0" + "\npreposer-prep0");
    }
    
    
    //methode pour le login admin, medecin, preposé
    @FXML    
    void login(ActionEvent event) throws IOException{
        String username = userN.getText();
        String password = pass.getText();
        
        //partie admin
        if (username.equals("admin") && password.equals("admin0")) {
            label.setText("Connexion réussie !");
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("    ")); 
//            Scene scene = new Scene(fxmlLoader.load());
//            //redirection
//            Stage stage = (Stage) tp.getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
        
        //partie medecin
        } else if (username.equals("medecin") && password.equals("medecin0")) {
            label.setText("Connexion réussie !");
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaceMedecin.fxml")); 
//            Scene scene = new Scene(fxmlLoader.load());
//            //redirection
//            Stage stage = (Stage) tp.getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
        
        //partie preposé
        } else if (username.equals("preposer") && password.equals("prep0")) {
            label.setText("Connexion réussie !");
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreposeSAMPLE.fxml")); 
//            Scene scene = new Scene(fxmlLoader.load());
//            //redirection
//            Stage stage = (Stage) tp.getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();

        //erreur
        } else {
            label.setText("La connexion a échoué.");
        }
    }
}

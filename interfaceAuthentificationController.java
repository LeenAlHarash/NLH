package projet;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * @author Leen Al Harash
 */

public class interfaceAuthentificationController {
    
    @FXML
    private Label label;
    
    @FXML 
    private TextField userN;
    
    @FXML 
    private TextField pass;
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
            Parent root = FXMLLoader.load(getClass().getResource("interfaceAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Gestion des tâches administratives");
            s.setScene(scene);
            s.show();
        
        //partie medecin
        } else if (username.equals("medecin") && (password.equals("medecin0") || password.equals("medecin1") || password.equals("medecin2") || password.equals("medecin3") 
                || password.equals("medecin4"))) {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceMedecin.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Gestion des patients du médecin");
            s.setScene(scene);
            s.show();
        
        //partie preposé
        } else if (username.equals("preposer") && (password.equals("prep0") || password.equals("prep1") || password.equals("prep2") || password.equals("prep3") 
                || password.equals("prep4"))) {
            Parent root = FXMLLoader.load(getClass().getResource("interfacePreposer.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Page de gestion de préposé");
            s.setScene(scene);
            s.show();
        }
        //erreur
         else {
            label.setText("La connexion a échoué.");
        }
    }
}

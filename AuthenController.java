package tp2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author leenz
 */
public class AuthenController {
    
    @FXML
    private Label label;
    
    @FXML 
    private TextField userN;
    
    @FXML 
    private TextField pass;
    
    //mdp
    @FXML 
    void infos(ActionEvent event) {
        label.setText("admin-admin0" + "\nmedecin-medecin1" + "\npreposer-prep10");
    }
    
    
    //login admin, medecin, preposé
    @FXML    
    void login(ActionEvent event){
        String username = userN.getText();
        String password = pass.getText();
        
        //admin
        if (username.equals("admin") && password.equals("admin0")) {
            label.setText("Connexion réussie !");
        
        //medecin
        } else if (username.equals("medecin") && password.equals("medecin1")) {
            label.setText("Connexion réussie !");
        
        //preposé
        } else if (username.equals("prepose") && password.equals("prep10")) {
            label.setText("Connexion réussie !");
        
        //erreur
        } else {
            label.setText("La connexion a échoué. Nom d'utilisateur ou mot de passe invalide.");
        }
    }
}
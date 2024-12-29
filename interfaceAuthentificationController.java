package projet;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Leen Al Harash && Mariyam Hanfaoui
 */

public class interfaceAuthentificationController {
    
    @FXML
    private Label label;
    
    @FXML 
    private TextField userN;
    
    @FXML 
    private TextField pass;
    
    // Pour récupérer le nom d'utilisateur du médecin lorsqu'il se connecte
    public static String nomUserMedecin;
    /**********************************************************************/
    
    //mdp pour les trois personnes
    @FXML 
    void infos(ActionEvent event) {
        label.setText("Voir MDP.txt pour \nles mots de passes");
    }
    
    
    //methode pour le login admin, medecin, preposé
    @FXML    
    void login(ActionEvent event) throws IOException{
        String username = userN.getText();
        String password = pass.getText();
        
        //partie admin
        if (username.equals("admin") && password.equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Gestion des tâches administratives");
            s.setScene(scene);
            s.show();
        
        // partie medecin
        } else if (username.equals("paul@nlh.com") && password.equals("medecin0") || username.equals("sophie@nlh.com") && password.equals("medecin1") || 
                   username.equals("lise@nlh.com") && password.equals("medecin2") || username.equals("jean@nlh.com") && password.equals("medecin3") ||
                   username.equals("marc@nlh.com") && password.equals("medecin4")) {
            
            nomUserMedecin = userN.getText();
            Parent root = FXMLLoader.load(getClass().getResource("interfaceMedecin.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Gestion des patients du médecin");
            s.setScene(scene);
            s.show();
        
        //partie preposé
        } else if (username.equals("prep") && (password.equals("prep"))) {
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

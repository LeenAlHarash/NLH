package tp2;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leenz
 */

public class PrepController {

    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private TabPane tp;
    @FXML
    private Button AjoutD;
    @FXML
    private Button ListeP;
    
    
    //selon le bouton choisi, le tab va etre selecté
    public void homePage(ActionEvent event) {
        //AjoutD -> tab2
        if (event.getSource() == AjoutD) {
            tp.getSelectionModel().select(tab2);
        //ListeP -> tab4
        } else if (event.getSource() == ListeP) {
            tp.getSelectionModel().select(tab4);
        }
    }
    
    //info patient (tab2) -> info medical (tab3)
    public void pageSuivante(){
        tp.getSelectionModel().select(tab3);
    }
    
    
    //s'assurer que l'admission et finalizer
    public void finalizer(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Êtez-vous certain de vouloir ajouter l'admission?");
        a.show();
        //changer les noms de boutons
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType annulerButton = new ButtonType("Annuler");
        a.getButtonTypes().setAll(ouiButton, annulerButton);

    }
   

    //deconnexion et redirection au authentification
    public void deconnexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthenSAMPLE.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        //redirection
        Stage stage = (Stage) tp.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
   
    public void ListeP() {
    }
}
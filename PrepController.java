package TP3;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * @author Leen Al Harash
 */

public class PrepController {
    /*ATTRIBUTS*/
    @FXML
    private Tab tab1, tab2, tab3, tab4, tab5;
    @FXML
    private TabPane tp;
    @FXML
    private Button AjoutD, ListeP, ListeA, accepter, refuser, finalizer;
    @FXML
    private TextField txtNom;  
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAdresse;   
    @FXML
    private TextField txtVille;  
    @FXML
    private TextField txtProvince;   
    @FXML
    private TextField txtCodePostal;   
    @FXML
    private TextField txtNumTel;   
    @FXML
    private DatePicker txtDateNaissance;  
    @FXML
    private TextField txtAge; 
    @FXML
    private TextField txtAssurance; 
    @FXML
    private TextField RAMQ;
    @FXML
    private CheckBox Televiseur;
    @FXML
    private CheckBox Telephone;
    @FXML
    private CheckBox chirurgOui;
//    FXML
//    private CheckBox chirurgNon;
    @FXML
    private CheckBox Standard;
    @FXML
    private CheckBox prive;
    @FXML
    private CheckBox semi;

    ObservableList<Patient> patients = FXCollections.observableArrayList();
    ObservableList<Personne> admission = FXCollections.observableArrayList();
    
    @FXML
    ListView listePatient;
    @FXML
    ListView listeAdmission;
    @FXML
    private TextField rechercheField;
    /**************************************************************************************************/
    
    
    //pour faire appel directement
    @FXML
    public void initialize() {
        //affichage de la liste de patient
        ListeP();
        //affichage de la liste d'admission
        ListeA();
        //affichage modifier à chaque fois (live search)
        rechercheField.textProperty().addListener((observable, oldValue, newValue) -> Recherche());
    }
    
    
    //selon le bouton choisi, le tab va etre selecté
    public void homePage(ActionEvent event) {
        //Ajout d'admission -> tab3
        if (event.getSource() == AjoutD) {
            tp.getSelectionModel().select(tab3);
        //Liste de patient-> tab5
        } else if (event.getSource() == ListeP) {
            tp.getSelectionModel().select(tab5);
        //Liste d'admission -> tab1
        } else if (event.getSource() == ListeA) {
            tp.getSelectionModel().select(tab2);
        } 
    }
    
    
    //info patient (tab2) -> info medical (tab3)
    public void pageSuivante(){
        tp.getSelectionModel().select(tab4);
    }
    
    
    //s'assurer que l'admission et finalizer                     /**************************PAS FINI, RESTE L'ENREGISTREMENT************************/
    public void finalizer(){
        if (finalizer == null){
        //ERROR
            Alert erreurSelection = new Alert(Alert.AlertType.ERROR);
            erreurSelection.setTitle("Erreur d'admission!");
            erreurSelection.setContentText("Vous n'avez pas rempli les deux formulaires au complet.");
            erreurSelection.show();
        //CONFIRMATION
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation d'admission!");
            a.setContentText("Êtez-vous certain de vouloir ajouter l'admission?");
            a.show();
            //changement de noms des boutons
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType annulerButton = new ButtonType("Annuler");
            a.getButtonTypes().setAll(ouiButton, annulerButton);
        }
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
    
    
    //affichage de la liste des patients - CODE DE MARIYAM
    public void ListeP() {
        // Création des assurances pour le choiceBox ainsi que pour les patients
        Assurance assurance1 = new Assurance(300, "Beneva");
        Assurance assurance2 = new Assurance(500, "AGECR");
        
        // Création de 5 patients
        Patient p1 = new Patient("200120", "2010-01-03", assurance1, "LaFleur", "Tita", "2004, 10e avenue", "Montréal", "Quebec", "H1X 1J7", "514-098-6453",14);
        Patient p2 = new Patient("548214", "2000-09-08", assurance2, "Tremblay", "John", "8415, 15e avenue", "Montréal", "Quebec", "H1X 1A6", "438-458-5126",24);
        Patient p3 = new Patient("698532", "2005-08-10", assurance2, "LaTour", "Mary", "5482, 58e avenue", "Montréal", "Quebec", "H1X 5H1", "438-582-9615",19);
        Patient p4 = new Patient("876452", "1999-09-05", assurance1, "Abed", "Fatima", "8734, 16e avenue", "Laval", "Quebec", "H1Q 8W2", "514-176-8723",25);
        Patient p5 = new Patient("165432", "1986-10-31", assurance2, "Abdellah","Aziz","1234, 19e avenue","Laval","Quebec","H1Z 3A9","514-395-0932",38);
        
        // Ajouter les patients au ObservableList
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patients.add(p5);
        
        // Afficher
        listePatient.setItems(patients);
    }
   
    
    //Methode pour faire la recherche en utilisant le RAMQ
    public void Recherche() {
        String searchText = rechercheField.getText().trim();

        ObservableList<Patient> filteredPatients = FXCollections.observableArrayList();
        //Parcourir la liste
        for (Patient patient : patients) {
            if (patient.getNumRAMQ().toLowerCase().contains(searchText)) {
                filteredPatients.add(patient);
            }
        }
        //Modifier l'affichage
        listePatient.setItems(filteredPatients);
    }

    
    //Methode pour voir la liste d'admission
    public void ListeA(){
        //initializer des personnes pour l'admission
        Personne A1 = new Personne("Doe", "John", "240, Saint-Michel", "Montreal" ,"Quebec" ,"H3H 2N4" , "514-293-9839");
        Personne A2 = new Personne("Bieber", "Justin" ,"1625, Boul Maissonneuve" ,"Montreal","Quebec","H3I 2J1", "514-589-5428");
        Personne A3 = new Personne("Stewart", "Carl","854, Saint-Hubert" , "Montreal", "Quebec", "O2P 8G4", "468-896-8545");
        Personne A4 = new Personne("Marie", "Claude","3453, 58e avenue" , "Montreal", "Quebec", "P0Z 1Q2", "658-854-9856");
        Personne A5 = new Personne("Klein", "Calvin" ,"647, 13e avenue" , "Montreal", "Quebec", "X2C 3V5", "154-589-5698");
        
        // Ajouter les admissions au ObservableList
        admission.add(A1);
        admission.add(A2);
        admission.add(A3);
        admission.add(A4);
        admission.add(A5);
        
        // Afficher
        listeAdmission.setItems(admission);
    }
}

package projet;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Leen Al Harash
 */

public class interfacePreposerController {
    //ATTRIBUTS
    @FXML
    private Tab tab1, tab2, tab3, tab4, tab5, tab6;
    @FXML
    private TabPane tp1, tp2;
    
    //tab 3-4-5 - créer un admission ou patient
    @FXML
    private Button finalizer, creerPatient;
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
    private DatePicker txtDateNaissance, dateAdmission;  
    @FXML
    private TextField txtAge; 
    @FXML
    private TextField txtAssurance; 
    @FXML
    private TextField txtRAMQ;
    @FXML
    private CheckBox Televiseur;
    @FXML
    private CheckBox Telephone;
    @FXML
    private RadioButton chirurgOui;
    @FXML
    private Pane chirurgOuiPANE;
    @FXML
    private RadioButton chirurgNon;
    @FXML
    private ComboBox combolit, combodepartement, combomedecin, cboID;  
    
    
    @FXML
    private TextField rechercheRAMQ, factureNoms;
    @FXML
    private Button deconnexion;
    @FXML
    private TextArea listePatient, listeAdmission, facturePatient;
    
    Gestionnaire gestionnaire = new Gestionnaire();
    ObservableList<Patient> patients = FXCollections.observableArrayList(gestionnaire.getPatientsListe());
    ObservableList<Medecin> medecins = FXCollections.observableArrayList(gestionnaire.getMedecinsListe());
    ObservableList<PreposeAdmission> preposes = FXCollections.observableArrayList(gestionnaire.getPreposesListe());
    ObservableList<Admission> admissions = FXCollections.observableArrayList(gestionnaire.getAdmissionsListe());
    ObservableList<Departement> departements = FXCollections.observableArrayList(gestionnaire.getDepartementsListe());
    ObservableList<Lit> lits = FXCollections.observableArrayList(gestionnaire.getLitsListe());
    ObservableList<Assurance> assurance = FXCollections.observableArrayList(gestionnaire.getAssurancesListe());
    /**************************************************************************************************/

    @FXML
    public void initialize() {
        listePatient.setText("\nVeuillez saisir le numéro RAMQ du patient pour voir ses informations.");
        listeAdmission.setText("\nVeuillez choisir le numéro d'identification d'admission pour voir ses informations.");
        facturePatient.setText("\nVeuillez saisir le numéro RAMQ du patient pour voir son facture.");
        
        //affichage combo box
        cboID.setItems(admissions);
        combomedecin.setItems(medecins); // only nom, prenom
        combolit.setItems(lits); // only standard, privé, semi-privé
        combodepartement.setItems(departements);
    }

    
    //deconnexion et redirection au authentification
    public void deconnexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaceAuthentification.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        //redirection
        Stage stage = (Stage)deconnexion.getScene().getWindow();
        stage.setTitle("Page d'authentification");
        stage.setScene(scene);
        stage.show();
    }
    
    
    //s'assurer que l'ajout de patient est complet - tab4                /**************************PAS FINI, RESTE L'ENREGISTREMENT************************/
    public void creerPatientBtn(){
        if (creerPatient == null){
        //ERROR
            Alert erreurSelection = new Alert(Alert.AlertType.ERROR);
            erreurSelection.setTitle("Erreur!");
            erreurSelection.setContentText("Vous n'avez pas rempli le formulaire au complet.");
            erreurSelection.show();
        //CONFIRMATION
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation de patient!");
            a.setContentText("Êtez-vous certain de vouloir ajouter ce patient?");
            a.show();
            //changement de noms des boutons
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType annulerButton = new ButtonType("Annuler");
            a.getButtonTypes().setAll(ouiButton, annulerButton);
        }
    }
   

    //s'assurer que l'admission et finalizer - tab 5                 /**************************PAS FINI, RESTE L'ENREGISTREMENT************************/
    public void finalizerBtn(){
        if (finalizer == null){
        //ERROR
            Alert erreurSelection = new Alert(Alert.AlertType.ERROR);
            erreurSelection.setTitle("Erreur d'admission!");
            erreurSelection.setContentText("Vous n'avez pas rempli le formulaire au complet.");
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
    
    
    //si chirurgie == oui -> afficher chirurgOuiPANE
    public void chirurgOuiBtn(){
        //si le bouton est selected = affichage, sinon ca va rester hidden
        chirurgOuiPANE.setVisible(!chirurgOuiPANE.isVisible());
    }
   
    
    //Methode pour faire la recherche d'un patient en utilisant le RAMQ - tab2
    public void Recherche(ActionEvent actionEvent){
        listePatient.setText(gestionnaire.rechercherPatient(rechercheRAMQ.getText()));
        
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        if (rechercheRAMQ.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }    }
    
    
    //combobox pour les id d'admission pour en choisir - tab1
    public void combdoAdmission() {
        Admission sAdmission = (Admission)cboID.getSelectionModel().getSelectedItem();           
        listeAdmission.setText("ID Admission: "+ sAdmission.getIdAdmission() + "\nDate d'admission: " + sAdmission.getDateAdmission() + "\nNom du patient: " + sAdmission.getPatient().getNom() + " , " +  sAdmission.getPatient().getPrenom()
                 + "\nMedecin: " + sAdmission.getMedecin().getNom() + " , " +  sAdmission.getMedecin().getPrenom() + "\nDepartement: " + sAdmission.getDepartement() + "\nTeleviseur Loué: " + sAdmission.getTeleviseurLoue() + "\nTelephone loué: " + sAdmission.getTelephoneLoue()
                 + "\nLit: "+ sAdmission.getLit() + "\nChirurgie: " + sAdmission.getChirurgieProgramee() + "\nDate de la chirurgie: " + sAdmission.getDateChirurgie() + "\nDate conger: " + sAdmission.getDateConge());
    }
    
    
    //textfield pour effectuer la recherche par ramq pour trouver le facture du patient - tab 6
    public void rechercheFactures(){
        facturePatient.setText(gestionnaire.afficherFacturePatient(factureNoms.getText()));
    }
}

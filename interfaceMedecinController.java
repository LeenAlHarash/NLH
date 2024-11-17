package TP3;

// Importations
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Hanfaoui Mariyam
 */


// Contrôleur de l'interface du médecin
public class interfaceMedecinController implements Initializable {
    
    // Création d'un ObservableList pour les patients (ListView, comboBox)
    ObservableList<Patient> patients = FXCollections.observableArrayList();
    
    @FXML
    ListView listePatient;
    
    @FXML
    ListView listePatientConge;
    
    @FXML
    Button deconnexionBTN;
    
    @FXML
    Button modificationBTN;
    
    @FXML
    Button donnerCongeBTN;
    
    @FXML
    ComboBox cboPatient;
    
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
    private DatePicker DateNaissance;
    
    @FXML
    private TextField txtAge;
    
    @FXML
    private TextField txtAssurance;
    
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Création des assurances pour les patients
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
        
        // Afficher cela à ListView de l'onglet ListePatient
        listePatient.setItems(patients);
        
        // Afficher cela à ListView de l'onglet DonnerConge
        listePatientConge.setItems(patients);
        
        // Ajouter la liste des patients au ComboBox
        cboPatient.setItems(patients);
    }
    
    
    // Méthode pour le ComboBox dans l'onglet modifier un patient dont celui-ci affiche les informations du patient dans
    // les TextFields appropriés pour effectuer la modification
    @FXML
    public void comboPatient(ActionEvent actionEvent) throws ParseException {
        // Sélectionner le patient pour lui modifier les informations
        Patient sPatient = (Patient)cboPatient.getSelectionModel().getSelectedItem();
        txtNom.setText(sPatient.getNom());
        txtPrenom.setText(sPatient.getPrenom());
        txtAdresse.setText(sPatient.getAdresse());
        txtVille.setText(sPatient.getVille());
        txtProvince.setText(sPatient.getProvince());
        txtCodePostal.setText(sPatient.getCodePostal());
        txtNumTel.setText(sPatient.getTelephone());
        txtAge.setText(String.valueOf(sPatient.getAge()));
        txtAssurance.setText(String.valueOf(sPatient.getAssurance().getNomCompagnie()));
        
        // Conversion du String de la date de naissance du patient en local date parce que
        // datePicker prend seulement des dates de type LocalDate et non de Date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datePatient = LocalDate.parse(sPatient.getDateNaissance(), dateFormat);
        DateNaissance.setValue(datePatient);
    }
    
    
    // Méthode associé au bouton donnerCongeBTN pour donner le congé au patient
    public void btnDonnerConge(ActionEvent event){
        // Sélectionner un patient de la liste dans l'onglet Donner un congé pour lui donner le congé
        Patient patientSelectionner = (Patient)listePatientConge.getSelectionModel().getSelectedItem();
        // Vérifier si le patient a été sélectionner
        if (patientSelectionner != null){
            // Création d'une alerte de confirmation ainsi que son contenu
            Alert donnerCongeAlert = new Alert(AlertType.CONFIRMATION);
            donnerCongeAlert.setTitle("Confirmation de l'attribution du congé");
            donnerCongeAlert.setContentText("Voulez-vous vraiment donner le congé à "+patientSelectionner.getPrenom()+" "+patientSelectionner.getNom()+" ?");
            // Attribuer l'alerte au bouton ayant l'id donnerCongeBTN pour afficher la confirmation
            donnerCongeBTN.setOnAction(e -> {
            // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
            donnerCongeAlert.showAndWait().ifPresent(resultat -> {
                // Vérifie si le resultat est égal au bouton OK
                if (resultat == ButtonType.OK){
                // Ici, on implémenterai la méthode qui donne le congé au patient
                // ....
                // Dialog qui dit confirme à l'utilisateur que les modifications ont été faites
                Dialog<String> dcReussi = new Dialog<String>();
                // Création d'un bouton à ajouter dans le dialog
                ButtonType dialogOkBtn = new ButtonType("OK", ButtonData.OK_DONE);
                // Contenu du dialog
                dcReussi.setTitle("Congé confirmé");
                dcReussi.setContentText("L'attribution du congé de "+patientSelectionner.getPrenom()+" "+patientSelectionner.getNom()+" est réussie!");
                // Ajout du bouton OK dans le dialog
                dcReussi.getDialogPane().getButtonTypes().add(dialogOkBtn);
                dcReussi.show();
                    }
                });
            });
        } else {
            Alert erreurSelection = new Alert(AlertType.ERROR);
            erreurSelection.setTitle("Erreur de sélection");
            erreurSelection.setContentText("ERREUR!! Vous n'avez pas sélectionné de patient");
            erreurSelection.show();
        }        
    }
    
    
    // Méthode associé au bouton modificationBTN pour la modification des informations du patient
    public void btnModifier(ActionEvent event){
        // Vérifie si un patient a été sélectionné du comboBox
        if (cboPatient.getValue() != null){
            // Création d'une alerte de confirmation ainsi que son contenu
            Alert modifierConfirmationAlert = new Alert(AlertType.CONFIRMATION);
            modifierConfirmationAlert.setTitle("Confirmation de la modification des informations");
            modifierConfirmationAlert.setContentText("Voulez-vous vraiment modifier les informations à "+txtPrenom.getText()+" "+txtNom.getText()+" ?");
            // Attribuer l'alerte au bouton ayant l'id modificationBTN pour afficher la confirmation
            modificationBTN.setOnAction(e -> {
            // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
            modifierConfirmationAlert.showAndWait().ifPresent(resultat -> {
                // Vérifie si le resultat est égal au bouton OK
                if (resultat == ButtonType.OK){
                // Ici, on implémenterai la méthode qui modifie les informations et qui sauvegarde ces nouvelles informations du patient
                // ....
                // Dialog qui dit confirme à l'utilisateur que les modifications ont été faites
                Dialog<String> modReussi = new Dialog<String>();
                // Création d'un bouton à ajouter dans le dialog
                ButtonType dialogOkBtn = new ButtonType("OK", ButtonData.OK_DONE);
                // Contenu du dialog
                modReussi.setTitle("Modification confirmée");
                modReussi.setContentText("La modification des informations de "+txtPrenom.getText()+" "+txtNom.getText()+" est réussie!");
                // Ajout du bouton OK dans le dialog
                modReussi.getDialogPane().getButtonTypes().add(dialogOkBtn);
                modReussi.show();
                }
            });
        });
        // Si il n'y a pas de patient sélectionné à partir du comboBox, ça affiche une alerte d'erreur
        } else {
            Alert erreurSelection = new Alert(AlertType.ERROR);
            erreurSelection.setTitle("Erreur de sélection");
            erreurSelection.setContentText("Vous n'avez pas sélectionné de patient pour effectuer une modification.");
            erreurSelection.show();
        }
    }
    
    
    // Déconnexion et redirection à la page d'authentification
    public void deconnexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthenSAMPLE.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        // Redirection à la page d'authentification à partir du bouton déconnexion
        Stage stage = (Stage)deconnexionBTN.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
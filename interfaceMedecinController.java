package projet;

// Importations
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Hanfaoui Mariyam
 */

// Contrôleur de l'interface du médecin
public class interfaceMedecinController implements Initializable {
    
    // Création du système gestionnaire
    Gestionnaire gestionnaire = new Gestionnaire();
    
    // Création des observableList qui contiennent tous les objets des autres classes dans chacunes
    ObservableList<Patient> patients = FXCollections.observableArrayList(gestionnaire.getPatientsListe());
    ObservableList<Medecin> medecins = FXCollections.observableArrayList(gestionnaire.getMedecinsListe());
    ObservableList<Assurance> assurances = FXCollections.observableArrayList(gestionnaire.getAssurancesListe());
    ObservableList<PreposeAdmission> preposes = FXCollections.observableArrayList(gestionnaire.getPreposesListe());
    ObservableList<Admission> admissions = FXCollections.observableArrayList(gestionnaire.getAdmissionsListe());
    ObservableList<Departement> departements = FXCollections.observableArrayList(gestionnaire.getDepartementsListe());
    ObservableList<Lit> lits = FXCollections.observableArrayList(gestionnaire.getLitsListe());
    
    // Liste des patients qui contient les patients filtrer
    ObservableList<Patient> patientsFiltrer = FXCollections.observableArrayList();
    
    @FXML
    TextArea txtListePatients;
    
    @FXML
    TextArea txtListePatientsConge;
    
    @FXML
    Button deconnexionBTN;
    
    @FXML
    Button modificationBTN;
    
    @FXML
    Button donnerCongeBTN;
    
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
    private TextField txtNumeroPermis;
    
    @FXML
    private TextField txtUtilisateur;
    
    @FXML
    private TextField txtMdp;
    
    @FXML
    private TextField txtRecherche1;
    
    @FXML
    private TextField txtRecherche2;
    
    @FXML
    private Button btnRecherche1;
    
    @FXML
    private Button btnRecherche2;
    
    @FXML
    private DatePicker dateConge;
    
    @FXML
    private Label bienvenue;
        
    static Object dateCongeAdmission;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Message initiale du TextArea txtListePatients dans l'onglet Liste des patients
        txtListePatients.setText("Veuillez recherchez le numéro RAMQ du patient pour voir ces informations.");
        
        // Message initiale du TextArea txtListePatients dans l'onglet Donner un congé
        txtListePatientsConge.setText("Veuillez recherchez le numéro RAMQ du patient pour voir ces informations et lui donner un congé.");
        
        // Changement du Label bienvenue qui inclut le nom d'utilisateur du médecin lorsqu'il s'est authentifié
        bienvenue.setText("Bienvenue "+interfaceAuthentificationController.nomUserMedecin);
        
        // Affichage des informations du médecin, dans l'onglet Modifier informations, afin qu'il puisse les modifier
        for(Medecin medecin : medecins){
            if(medecin.getNomUtilisateur().equals(interfaceAuthentificationController.nomUserMedecin)){
                txtNumeroPermis.setText(medecin.getNumeroPermis());
                txtNom.setText(medecin.getNom());
                txtPrenom.setText(medecin.getPrenom());
                txtVille.setText(medecin.getVille());
                txtProvince.setText(medecin.getProvince());
                txtCodePostal.setText(medecin.getCodePostal());
                txtAdresse.setText(medecin.getAdresse());
                txtNumTel.setText(medecin.getTelephone());
                txtUtilisateur.setText(medecin.getNomUtilisateur());
                txtMdp.setText(medecin.getMotDePasse());
            }
        }
        
        // Parcours la liste des patients et ajoute le patient correspondant au médecin à la liste filtrer
        for(Patient patient : patients){
            if(patient.getMedecin().getNomUtilisateur().equals(interfaceAuthentificationController.nomUserMedecin)){
                patientsFiltrer.add(patient);
            }
        }
    }
    
    // Méthode de recherche des patients, dans l'onglet Liste des patients, à partir de leur numRAMQ
    // Elle est différente que celle de la classe Gestionnaire parce qu'elle parcours la liste des patients filtrer du médecin
    // tandis que celle de la classe Gestionnaire parcours celle dans le constructeur Gestionnaire
    @FXML
    public void btnRechercherPatient(ActionEvent actionEvent){
        // Vérifie si le textField n'est pas vide
        if(!txtRecherche1.getText().isEmpty()){
            // Avant d'ajouter cela, la méthode était capable seulement de rechercher un des patients dans la liste, malgré que le filtrage fonctionnait
            boolean patientTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
            // Parcourir la liste des patients filtrer
            for (Patient patient : patientsFiltrer) {
                // Vérifie si le patient à le même numRAMQ que celui rechercher dans le textField
                if (patient.getNumRAMQ().equals(txtRecherche1.getText().trim())){
                    patientTrouve = true;
                    txtListePatients.setText("NUMRAMQ: " + patient.getNumRAMQ() + 
                            "\nNom du patient: " + patient.getNom() + " " + patient.getPrenom() + 
                            "\nDate de naissance: " + patient.getDateNaissance() +
                            "\nÂge du patient: " + patient.getAge() +
                            "\nAdresse: " + patient.getAdresse() + 
                            "\nVille & Province: " + patient.getVille() + ", " + patient.getProvince() + 
                            "\nCode Postal: " + patient.getCodePostal() + 
                            "\nNuméro de téléphone: "+ patient.getTelephone() + 
                            "\nAssurance: " + patient.getAssurance());
                    break;
                } else if (!patientTrouve){
                    txtListePatients.setText("Patient non trouvé.");
                }
            }
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }
    } 
    
    // Méthode de recherche des patients, dans l'onglet Donner un congé, à partir de leur numRAMQ
    // Même chose que la méthode de btnRechercherPatient
    @FXML
    public void btnRechercherPatientConge(ActionEvent actionEvent){
        // Vérifie si le textField n'est pas vide
        if(!txtRecherche2.getText().isEmpty()){
            // Avant d'ajouter cela, la méthode était capable seulement de rechercher un des patients dans la liste, malgré que le filtrage fonctionnait
            boolean patientTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
            // Parcourir la liste des patients filtrer
            for (Patient patient : patientsFiltrer) {
                // Vérifie si le patient à le même numRAMQ que celui rechercher dans le textField
                if (patient.getNumRAMQ().equals(txtRecherche2.getText().trim())){
                    patientTrouve = true;
                    txtListePatientsConge.setText("NUMRAMQ: " + patient.getNumRAMQ() + 
                            "\nNom du patient: " + patient.getNom() + " " + patient.getPrenom() + 
                            "\nDate de naissance: " + patient.getDateNaissance() +
                            "\nÂge du patient: " + patient.getAge() +
                            "\nAdresse: " + patient.getAdresse() + 
                            "\nVille & Province: " + patient.getVille() + ", " + patient.getProvince() + 
                            "\nCode Postal: " + patient.getCodePostal() + 
                            "\nNuméro de téléphone: "+ patient.getTelephone() + 
                            "\nAssurance: " + patient.getAssurance());
                    break;
                } else if (!patientTrouve){
                    txtListePatientsConge.setText("Patient non trouvé.");
                }
            }
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }
    }
    
    // Méthode associé au bouton donnerCongeBTN pour donner le congé au patient
    @FXML
    public void btnDonnerConge(ActionEvent event){
        // Vérifier si le patient a été sélectionner
        if (txtRecherche2.getText() != null){
            // Création d'une alerte de confirmation ainsi que son contenu
            Alert donnerCongeAlert = new Alert(AlertType.CONFIRMATION);
            donnerCongeAlert.setTitle("Confirmation de l'attribution du congé");
            donnerCongeAlert.setHeaderText(null);
            donnerCongeAlert.setContentText("Voulez-vous vraiment lui donner un congé?");
            // Attribuer l'alerte au bouton ayant l'id donnerCongeBTN pour afficher la confirmation
            donnerCongeBTN.setOnAction(e -> {
            // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
            donnerCongeAlert.showAndWait().ifPresent(resultat -> {
                // Vérifie si le resultat est égal au bouton OK
                if (resultat == ButtonType.OK){
                // Donner le congé au patient recherché
                gestionnaire.donnerConge(txtRecherche2.getText(), dateConge.getValue().toString());
                // Dialog qui dit confirme à l'utilisateur que l'attribution du congé a été fait
                Dialog<String> dcReussi = new Dialog<>();
                // Création d'un bouton à ajouter dans le dialog
                ButtonType dialogOkBtn = new ButtonType("OK", ButtonData.OK_DONE);
                // Contenu du dialog
                dcReussi.setTitle("Congé confirmé");
                dcReussi.setContentText("L'attribution du congé est réussie!");
                // Ajout du bouton OK dans le dialog
                dcReussi.getDialogPane().getButtonTypes().add(dialogOkBtn);
                dcReussi.show();
                    }
                });
            });
        } else {
            Alert erreurSelection = new Alert(AlertType.ERROR);
            erreurSelection.setTitle("Erreur");
            erreurSelection.setHeaderText(null);
            erreurSelection.setContentText("Vous n'avez pas rechercher de patient! Veuillez rechercher un patient pour lui donner un congé.");
            erreurSelection.show();
        }        
    }
    
    
    // Méthode associé au bouton modificationBTN pour la modification des informations personnelles du médecin
    @FXML
    public void btnModifier(ActionEvent event){
        // Création d'une alerte de confirmation ainsi que son contenu
        Alert modifierConfirmationAlert = new Alert(AlertType.CONFIRMATION);
        modifierConfirmationAlert.setTitle("Confirmation de la modification des informations");
        modifierConfirmationAlert.setHeaderText("Confirmation");
        modifierConfirmationAlert.setContentText("Voulez-vous vraiment modifier vos informations personnelles?");
        // Attribuer l'alerte au bouton ayant l'id modificationBTN pour afficher la confirmation
        modificationBTN.setOnAction(e -> {
        // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
        modifierConfirmationAlert.showAndWait().ifPresent(resultat -> {
            // Vérifie si le résultat est égal au bouton OK
            if (resultat == ButtonType.OK){
                // Modifications des informations personnelles du médecin
                txtNumeroPermis.setText(txtNumeroPermis.getText());
                txtNom.setText(txtNom.getText());
                txtPrenom.setText(txtPrenom.getText());
                txtVille.setText(txtVille.getText());
                txtProvince.setText(txtProvince.getText());
                txtCodePostal.setText(txtCodePostal.getText());
                txtAdresse.setText(txtAdresse.getText());
                txtNumTel.setText(txtNumTel.getText());
                txtUtilisateur.setText(txtUtilisateur.getText());
                txtMdp.setText(txtMdp.getText());
                // Dialog qui dit confirme à l'utilisateur que les modifications ont été faites
                Dialog<String> modReussi = new Dialog<>();
                // Création d'un bouton à ajouter dans le dialog
                ButtonType dialogOkBtn = new ButtonType("OK", ButtonData.OK_DONE);
                // Contenu du dialog
                modReussi.setTitle("Modification confirmée");
                modReussi.setContentText("La modification des informations personnelles a été fait avec succès!");
                // Ajout du bouton OK dans le dialog
                modReussi.getDialogPane().getButtonTypes().add(dialogOkBtn);
                modReussi.show();
                }
            });
        });
    }
    
    // Déconnexion et redirection à la page d'authentification
    @FXML
    public void deconnexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaceAuthentification.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        // Redirection à la page d'authentification à partir du bouton déconnexion
        Stage stage = (Stage)deconnexionBTN.getScene().getWindow();
        stage.setTitle("Page d'authentification");
        stage.setScene(scene);
        stage.show();
    }
}

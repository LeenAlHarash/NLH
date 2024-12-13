package projet;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Benjamin Melis
 */

public class interfaceAdminController implements Initializable {
    
    Gestionnaire gestionnaire = new Gestionnaire();

    // ObservableLists
    ObservableList<Patient> patients = FXCollections.observableArrayList(gestionnaire.getPatientsListe());
    ObservableList<Medecin> medecins = FXCollections.observableArrayList(gestionnaire.getMedecinsListe());
    ObservableList<PreposeAdmission> preposes = FXCollections.observableArrayList(gestionnaire.getPreposesListe());
    ObservableList<Admission> admissions = FXCollections.observableArrayList(gestionnaire.getAdmissionsListe());
    ObservableList<Departement> departements = FXCollections.observableArrayList(gestionnaire.getDepartementsListe());
    ObservableList<Lit> lits = FXCollections.observableArrayList(gestionnaire.getLitsListe());
    ObservableList<Assurance> assurances = FXCollections.observableArrayList(gestionnaire.getAssurancesListe());

    // FXML ListView et ComboBox elements
    @FXML
    TextArea adminListePatient;  
    @FXML
    TextArea adminListeMedecin;
    @FXML
    TextArea adminListePrepose;
    @FXML
    TextArea adminEtatAdmission;
    @FXML
    ComboBox<Medecin> selectionMedecin;  

    // FXML TextField elements 
    @FXML
    private TextField fieldNomMedecin, fieldPrenomMedecin, fieldNumPermis, fieldNomUtilisateur, fieldMotPasse;
    @FXML
    private TextField fieldAdresseMedecin, fieldVilleMedecin, fieldProvinceMedecin, fieldCodePostalMedecin, fieldNumTelephoneMedecin;
    @FXML
    private TextField fieldNomMedecin1, fieldPrenomMedecin1, fieldNumPermis1, fieldNomUtilisateur1, fieldMotPasse1;
    @FXML
    private TextField fieldAdresseMedecin1, fieldVilleMedecin1, fieldProvinceMedecin1, fieldCodePostalMedecin1, fieldNumTelephoneMedecin1;
    @FXML
    private TextField txtRecherche1, txtRecherche2, txtRecherche3, txtRecherche4;

    // FXML Buttons 
    @FXML
    private Button adminModification, adminSupprimer, deconnexionBTN, adminAjouter, btnRecherchePatients, btnRechercheMedecins, btnRecherchePreposes, btnRechercheAdmissions;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Message initiale du TextArea dans l'onglet Liste des patients
        adminListePatient.setText("Veuillez recherchez le numéro RAMQ du patient pour voir ces informations.");
        adminListeMedecin.setText("Veuillez recherchez le numéro de permis du medecin pour voir ces informations.");
        adminListePrepose.setText("Veuillez recherchez le ID du prepose pour voir ces informations.");
        adminEtatAdmission.setText("Veuillez recherchez le numéro d'admission ces informations.");
        // Mettre les objets dans le ListView
       

        // Monter le comboBox pour la sélection d'un medecin
        selectionMedecin.setItems(medecins);
    }


    // ComboBox pour Medecin
    @FXML
    public void comboMedecin(ActionEvent actionEvent) {
        Medecin selectedMedecin = (Medecin)selectionMedecin.getSelectionModel().getSelectedItem(); 
        
        if (selectedMedecin == null) {
        // Show an alert or handle the case where no item is selected
        Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
        noSelectionAlert.setTitle("Aucune sélection");
        noSelectionAlert.setHeaderText("Aucun médecin sélectionné");
        noSelectionAlert.setContentText("Veuillez sélectionner un médecin.");
        noSelectionAlert.showAndWait();
        return; // Exit ;a methode si aucun Medecin est selectionner
    }

        fieldNomMedecin1.setText(selectedMedecin.getNom()); 
        fieldPrenomMedecin1.setText(selectedMedecin.getPrenom()); 
        fieldAdresseMedecin1.setText(selectedMedecin.getAdresse());
        fieldVilleMedecin1.setText(selectedMedecin.getVille()); 
        fieldProvinceMedecin1.setText(selectedMedecin.getProvince());
        fieldCodePostalMedecin1.setText(selectedMedecin.getCodePostal());
        fieldNumTelephoneMedecin1.setText(selectedMedecin.getTelephone()); 
        fieldNomUtilisateur1.setText(selectedMedecin.getNomUtilisateur()); 
        fieldMotPasse1.setText(selectedMedecin.getMotDePasse()); 
        fieldNumPermis1.setText(selectedMedecin.getNumeroPermis()); 
    }

    
    @FXML
    public void deconnexion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaceAuthentification.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        // Redirection à la page d'authentification à partir du bouton déconnexion
        Stage stage = (Stage)deconnexionBTN.getScene().getWindow();
        stage.setTitle("Page d'authentification");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnRechercherPatient(ActionEvent actionEvent){
        // Vérifie si le textField n'est pas vide
        if(!txtRecherche1.getText().isEmpty()){
            boolean patientTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
            // Parcourir la liste des patients
            adminListePatient.setText(gestionnaire.rechercherPatient(txtRecherche1.getText()));
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }
    } 
    
@FXML
public void btnRechercherMedecin(ActionEvent actionEvent) {
    // Vérifie si le textField n'est pas vide
    if (!txtRecherche2.getText().isEmpty()) {
        boolean medecinTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
        // Parcourir la liste des médecins
        adminListeMedecin.setText(gestionnaire.rechercherMedecin(txtRecherche2.getText()));
    } else {
        // Alerte au cas où l'utilisateur ne met pas de numéro de permis dans le TextField
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Erreur de saisie");
        a.setHeaderText(null);
        a.setContentText("Veuillez saisir le numéro de permis du médecin que vous recherchez.");
        a.show();
    }
}

@FXML
public void btnRechercherPrepose(ActionEvent actionEvent) {
    // Vérifie si le textField n'est pas vide
    if (!txtRecherche3.getText().isEmpty()) {
        boolean preposeTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
        // Parcourir la liste des préposés
        adminListePrepose.setText(gestionnaire.rechercherPrepose(txtRecherche3.getText()));
    } else {
        // Alerte au cas où l'utilisateur ne met pas d'ID dans le TextField
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Erreur de saisie");
        a.setHeaderText(null);
        a.setContentText("Veuillez saisir l'ID du préposé que vous recherchez.");
        a.show();
    }
}


@FXML
public void btnRechercherAdmission(ActionEvent actionEvent) {
    // Vérifie si le textField n'est pas vide
    if (!txtRecherche4.getText().isEmpty()) {
        boolean admissionTrouve = false; // Attribut boolean pour éviter d'avoir une boucle infinie
        // Parcourir la liste des admissions
        adminEtatAdmission.setText(gestionnaire.rechercherAdmission(txtRecherche4.getText()));        
    } else {
        // Alerte au cas où l'utilisateur ne met pas d'ID d'admission dans le TextField
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Erreur de saisie");
        a.setHeaderText(null);
        a.setContentText("Veuillez saisir l'ID de l'admission que vous recherchez.");
        a.show();
    }
}


    
    @FXML
    public void adminModification(ActionEvent event) {
        
         // Assurer que le ComboBox est correctement populer avant de continuer
    if (selectionMedecin.getItems() == null || selectionMedecin.getItems().isEmpty()) {
        if (medecins != null) {
            selectionMedecin.setItems(medecins);
        } else {
            System.err.println("ObservableList medecins is null!");
        }
    }
        // Prendre le medecin selectionner du comboBox
        Medecin selectedMedecin = selectionMedecin.getSelectionModel().getSelectedItem();

        if (selectedMedecin == null) {
            // Montrer une alerte si aucun medecin est selectionner
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.setTitle("Aucune sélection");
            noSelectionAlert.setHeaderText("Aucun médecin sélectionné");
            noSelectionAlert.setContentText("Veuillez sélectionner un médecin à modifier.");
            noSelectionAlert.showAndWait();
            return;
        }

        // Creer dialogue de confirmation
        Alert modifierConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        modifierConfirmationAlert.setTitle("Confirmation de la modification");
        modifierConfirmationAlert.setHeaderText("Modifier les informations");
        modifierConfirmationAlert.setContentText("Êtes-vous sûr de vouloir modifier ces informations personnelles?");

        modifierConfirmationAlert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                // Update les attributs du Medecin selectionner
                selectedMedecin.setNom(fieldNomMedecin1.getText());
                selectedMedecin.setPrenom(fieldPrenomMedecin1.getText());
                selectedMedecin.setAdresse(fieldAdresseMedecin1.getText());
                selectedMedecin.setVille(fieldVilleMedecin1.getText());
                selectedMedecin.setProvince(fieldProvinceMedecin1.getText());
                selectedMedecin.setCodePostal(fieldCodePostalMedecin1.getText());
                selectedMedecin.setTelephone(fieldNumTelephoneMedecin1.getText());
                selectedMedecin.setNomUtilisateur(fieldNomUtilisateur1.getText());
                selectedMedecin.setMotDePasse(fieldMotPasse1.getText());


                // Mettre à jour le TextArea pour afficher les informations du médecin
            adminListeMedecin.setText(
                "Nom: " + selectedMedecin.getNom() + "\n" +
                "Prénom: " + selectedMedecin.getPrenom() + "\n" +
                "Adresse: " + selectedMedecin.getAdresse() + "\n" +
                "Ville: " + selectedMedecin.getVille() + "\n" +
                "Province: " + selectedMedecin.getProvince() + "\n" +
                "Code Postal: " + selectedMedecin.getCodePostal() + "\n" +
                "Numéro de téléphone: " + selectedMedecin.getTelephone() + "\n" +
                "Nom d'utilisateur: " + selectedMedecin.getNomUtilisateur() + "\n" +
                "Mot de passe: " + selectedMedecin.getMotDePasse() + "\n" +
                "Numéro de permis: " + selectedMedecin.getNumeroPermis()
            );

                // Montrer dialogue de succes
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText("Informations mises à jour");
                successAlert.setContentText("Les informations du médecin ont été modifiées avec succès.");
                successAlert.showAndWait();
            }
        });
    }
    
    


    @FXML
    public void adminSupprimer(ActionEvent event) {
        // Check si un medecin est selectionner du comboBox
        Medecin selectedMedecin = selectionMedecin.getSelectionModel().getSelectedItem();
        if (selectedMedecin == null) {
            // montrer une alerte si aucun medecin est selectionner
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.setTitle("Aucune sélection");
            noSelectionAlert.setHeaderText("Aucun médecin sélectionné");
            noSelectionAlert.setContentText("Veuillez sélectionner un médecin à supprimer.");
            noSelectionAlert.showAndWait();
            return;
        }

        // Creer une alerte de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de la suppression");
        confirmationAlert.setHeaderText("Supprimer un médecin");
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce médecin?");

        confirmationAlert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                // enlever un medecin du ObservableList
                medecins.remove(selectedMedecin);

                // Update le ComboBox et ListView pour refleter le removal
                selectionMedecin.setItems(medecins);
                 // Effacer le contenu de TextArea après la suppression
                adminListeMedecin.setText("Aucun médecin sélectionné.");

                // Montrer un dialogue de confirmation a l'utilisateur
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Suppression réussie");
                successAlert.setHeaderText("Médecin supprimé");
                successAlert.setContentText("Le médecin a été supprimé avec succès.");
                successAlert.showAndWait();
            }
        });
    }
    

    @FXML
    public void adminAjouter(ActionEvent event) {
        if (fieldNumPermis.getText().isEmpty() || fieldMotPasse.getText().isEmpty() || fieldNomMedecin.getText().isEmpty() ||
            fieldPrenomMedecin.getText().isEmpty() || fieldAdresseMedecin.getText().isEmpty() || 
            fieldVilleMedecin.getText().isEmpty() || fieldProvinceMedecin.getText().isEmpty() || 
            fieldCodePostalMedecin.getText().isEmpty() || fieldNumTelephoneMedecin.getText().isEmpty()) {

            Alert missingFieldsAlert = new Alert(Alert.AlertType.WARNING);
            missingFieldsAlert.setTitle("Champs manquants");
            missingFieldsAlert.setHeaderText("Des champs obligatoires sont vides");
            missingFieldsAlert.setContentText("Veuillez remplir tous les champs pour ajouter un médecin.");
            missingFieldsAlert.showAndWait();
            return;
        }

        // Generer la prochaine Cle dynamique(m1, m2, m3)
        String key = "m" + (medecins.size() + 1);

        // Creer le nouveau Medecin objet
        Medecin medecin = new Medecin(
            fieldNumPermis.getText(),
            fieldNomUtilisateur.getText(),
            fieldMotPasse.getText(),
            fieldNomMedecin.getText(),
            fieldPrenomMedecin.getText(),
            fieldAdresseMedecin.getText(),
            fieldVilleMedecin.getText(),
            fieldProvinceMedecin.getText(),
            fieldCodePostalMedecin.getText(),
            fieldNumTelephoneMedecin.getText()
        );

        // Ajouter le nouveau medecin au HashMap
        gestionnaire.numerosDeMedecin(key, medecin);            

        // Update le ObservableList pour le ComboBox et ListView
        medecins.add(medecin);
        // Mettre à jour le ComboBox et TextArea
        selectionMedecin.setItems(medecins);
    
        // Mettre à jour le contenu de TextArea pour refléter l'ajout
        adminListeMedecin.setText("Médecin ajouté: \n" + 
        "Numéro de Permis: " + medecin.getNumeroPermis() + "\n" +
        "Nom: " + medecin.getNom() + "\n" +
        "Prénom: " + medecin.getPrenom() + "\n" +
        "Adresse: " + medecin.getAdresse() + "\n" +
        "Ville: " + medecin.getVille() + "\n" +
        "Province: " + medecin.getProvince() + "\n" +
        "Code Postal: " + medecin.getCodePostal() + "\n" +
        "Numéro de téléphone: " + medecin.getTelephone() + "\n" +
        "Nom d'utilisateur: " + medecin.getNomUtilisateur() + "\n" +
        "Mot de passe: " + medecin.getMotDePasse());
    
        // Clear les input fields
        fieldNumPermis.clear();
        fieldNomUtilisateur.clear();
        fieldMotPasse.clear();
        fieldNomMedecin.clear();
        fieldPrenomMedecin.clear();
        fieldAdresseMedecin.clear();
        fieldVilleMedecin.clear();
        fieldProvinceMedecin.clear();
        fieldCodePostalMedecin.clear();
        fieldNumTelephoneMedecin.clear();

        // Montrer le dialogue succes
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Ajout réussi");
        successAlert.setHeaderText("Médecin ajouté");
        successAlert.setContentText("Le médecin a été ajouté avec l'identifiant " + key + ".");
        successAlert.showAndWait();
    }
}

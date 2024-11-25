package projet;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Hanfaoui Mariyam
 */


// Contrôleur de l'interface du médecin
public class interfaceMedecinController implements Initializable {
    
    // Création du système
    Gestionnaire gestionnaire = new Gestionnaire();
    
    // Création d'un ObservableList pour les patients (comboBox)
    ObservableList<Patient> patients = FXCollections.observableArrayList(gestionnaire.getPatientsListe());
    
    // Création d'un ObservableList pour les assurances (choiceBox)
    ObservableList<Assurance> assurances = FXCollections.observableArrayList(gestionnaire.getAssurancesListe());
    
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
    private ChoiceBox choixAssurance;
    
    @FXML
    private TextField txtRecherche1;
    
    @FXML
    private TextField txtRecherche2;
    
    @FXML
    private Button btnRecherche1;
    
    @FXML
    private Button btnRecherche2;
        
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Remplir tableau des medecins
        Medecin m1 = new Medecin("1234-5678", "paul@nlh.com", "medecin0", "Paul", "Marie", "2004, 10e avenue", "Montreal", "Quebec", "H3H 2N4", "123-4567");
        Medecin m2 = new Medecin("2345-6789", "sophie@nlh.com","medecin1", "Sophie", "Lana-Del", "8415, 15e avenue", "Montreal", "Quebec", "X8X 6G8", "234-5678");
        Medecin m3 = new Medecin("3456-7890", "lise@nlh.com", "medecin2", "Lise", "Jean-Pierre", "5482, 58e avenue", "Montreal", "Quebec", "L0P 3K1", "345-6789");
        Medecin m4 = new Medecin("4567-8901", "jean@nlh.com", "medecin3", "Jean", "Rosaline", "8734, 16e avenue", "Montreal", "Quebec", "H1J 4P2", "456-7890");
        Medecin m5 = new Medecin("5678-9012", "marc@nlh.com", "medecin4", "Marc", "Derek", "1234, 19e avenue", "Montreal", "Quebec", "P1P 3M3", "567-8901");
        
        // Remplir tableau des preposés
        PreposeAdmission pr1 = new PreposeAdmission(201, "lucie@nlh.com", "prep0", "Lucie", "Aziz", "2004, 10e avenue", "Montreal", "Quebec", "H3H 2N4", "123-4567");
        PreposeAdmission pr2 = new PreposeAdmission(202, "jean@nlh.com", "prep1", "Jean", "Singh", "5482, 58e avenue", "Montreal", "Quebec", "X8X 6G8", "234-5678");
        PreposeAdmission pr3 = new PreposeAdmission(203, "marie@nlh.com", "prep2", "Marie", "Luisan", "8734, 16e avenue", "Montreal", "Quebec", "L0P 3K1", "345-6789");
        PreposeAdmission pr4 = new PreposeAdmission(204, "luca@nlh.com", "prep3", "Luca", "Pierre", "1234, 19e avenue", "Montreal", "Quebec", "P1P 3M3", "456-7890");
        PreposeAdmission pr5 = new PreposeAdmission(205, "nadia@nlh.com", "prep4", "Nadia", "Melisel", "5487, 16e avenue", "Montreal", "Quebec", "H1J 4P2", "567-8901");
        
        // Remplir tableau des départements
        Departement departement1 = new Departement(100, "Ob/Gyn");
        Departement departement2 = new Departement(200, "Chirurgie");
        Departement departement3 = new Departement(300, "Pédiatrie");
        Departement departement4 = new Departement(400, "Imagerie médicale");
        Departement departement5 = new Departement(500, "Régulier");
        
        // Remplir tableau des lits
        Lit lit1 = new Lit("1", true, "Standard", departement2);
        Lit lit2 = new Lit("2", true, "Semi-privé", departement3);
        Lit lit3 = new Lit("3", false, "Standard", departement5);
        Lit lit4 = new Lit("4", true, "Semi-privé", departement1);
        Lit lit5 = new Lit("5", false, "Privé", departement2);

        // Remplir tableau admissions
        Admission A1 = new Admission(1, true, "2024-11-01", "2024-11-05", "2024-11-10", true, false, patients.get(0), lit1, m1, departement1);
        Admission A2 = new Admission(2, false, "2024-10-15", "2024-10-20", "2024-10-22", false, true, patients.get(1), lit2, m2, departement2);
        Admission A3 = new Admission(3, true, "2024-09-10", "2024-09-12", "2024-09-15", true, true, patients.get(2), lit3, m3, departement3);
        Admission A4 = new Admission(4, false, "2024-08-05", "2024-08-07", "2024-08-10", false, false, patients.get(3), lit4, m4, departement4);
        Admission A5 = new Admission(5, true, "2024-07-20", "2024-07-25", "2024-07-30", true, true, patients.get(4), lit5, m5, departement5);
        
        // Ajouter la liste des patients au ComboBox
        cboPatient.setItems(patients);
        
        // Ajouter la liste des assurances au ChoiceBox
        choixAssurance.setItems(assurances);
        
        // Message initiale du TextArea txtListePatients dans l'onglet Liste des patients
        txtListePatients.setText("Veuillez recherchez le numéro RAMQ du patient pour voir ces informations.");
        
        // Message initiale du TextArea txtListePatients dans l'onglet Donner un congé
        txtListePatientsConge.setText("Veuillez recherchez le numéro RAMQ du patient pour voir ces informations et lui donner un congé.");
    }
    
    // Méthode de recherche des patients, dans l'onglet Liste des patients, à partir de leur numRAMQ
    @FXML
    public void btnRechercherPatient(ActionEvent actionEvent){
        txtListePatients.setText(gestionnaire.rechercherPatient(txtRecherche1.getText()));
        
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        if (txtRecherche1.getText().isEmpty()){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }
    } 
    
    
    // Méthode de recherche des patients, dans l'onglet Donner un congé, à partir de leur numRAMQ
    @FXML
    public void btnRechercherPatientConge(ActionEvent actionEvent){
        txtListePatientsConge.setText(gestionnaire.rechercherPatient(txtRecherche2.getText()));
        
        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        if (txtRecherche2.getText().isEmpty()){
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            a.show();
        }
    } 
    
    // Méthode pour le ComboBox dans l'onglet modifier un patient dont celui-ci affiche les informations du patient dans
    // les TextFields appropriés pour effectuer la modification
    @FXML
    public void comboPatient(ActionEvent actionEvent) throws ParseException {
        // Sélectionner le patient pour lui afficher les informations
        Patient sPatient = (Patient)cboPatient.getSelectionModel().getSelectedItem();
        txtNom.setText(sPatient.getNom());
        txtPrenom.setText(sPatient.getPrenom());
        txtAdresse.setText(sPatient.getAdresse());
        txtVille.setText(sPatient.getVille());
        txtProvince.setText(sPatient.getProvince());
        txtCodePostal.setText(sPatient.getCodePostal());
        txtNumTel.setText(sPatient.getTelephone());
        txtAge.setText(String.valueOf(sPatient.getAge()));
        choixAssurance.setValue(sPatient.getAssurance());
        
        // Conversion du String de la date de naissance du patient en local date parce que
        // datePicker prend seulement des dates de type LocalDate et non de Date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datePatient = LocalDate.parse(sPatient.getDateNaissance(), dateFormat);
        DateNaissance.setValue(datePatient);
    }
    
    
    // Méthode associé au bouton donnerCongeBTN pour donner le congé au patient
//    @FXML
    public void btnDonnerConge(ActionEvent event){
//        // Sélectionner un patient de la liste dans l'onglet Donner un congé pour lui donner le congé
//        Patient patientSelectionner = (Patient)listePatientConge.getSelectionModel().getSelectedItem();
//        // Vérifier si le patient a été sélectionner
//        if (patientSelectionner != null){
//            // Création d'une alerte de confirmation ainsi que son contenu
//            Alert donnerCongeAlert = new Alert(AlertType.CONFIRMATION);
//            donnerCongeAlert.setTitle("Confirmation de l'attribution du congé");
//            donnerCongeAlert.setContentText("Voulez-vous vraiment donner le congé à "+patientSelectionner.getPrenom()+" "+patientSelectionner.getNom()+" ?");
//            // Attribuer l'alerte au bouton ayant l'id donnerCongeBTN pour afficher la confirmation
//            donnerCongeBTN.setOnAction(e -> {
//            // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
//            donnerCongeAlert.showAndWait().ifPresent(resultat -> {
//                // Vérifie si le resultat est égal au bouton OK
//                if (resultat == ButtonType.OK){
//                // Ici, on implémenterai la méthode qui donne le congé au patient
//                // ....
//                // Dialog qui dit confirme à l'utilisateur que les modifications ont été faites
//                Dialog<String> dcReussi = new Dialog<>();
//                // Création d'un bouton à ajouter dans le dialog
//                ButtonType dialogOkBtn = new ButtonType("OK", ButtonData.OK_DONE);
//                // Contenu du dialog
//                dcReussi.setTitle("Congé confirmé");
//                dcReussi.setContentText("L'attribution du congé de "+patientSelectionner.getPrenom()+" "+patientSelectionner.getNom()+" est réussie!");
//                // Ajout du bouton OK dans le dialog
//                dcReussi.getDialogPane().getButtonTypes().add(dialogOkBtn);
//                dcReussi.show();
//                    }
//                });
//            });
//        } else {
//            Alert erreurSelection = new Alert(AlertType.ERROR);
//            erreurSelection.setTitle("Erreur de sélection");
//            erreurSelection.setContentText("ERREUR!! Vous n'avez pas sélectionné de patient");
//            erreurSelection.show();
//        }        
    }
    
    
    // Méthode associé au bouton modificationBTN pour la modification des informations du patient
    @FXML
    public void btnModifier(ActionEvent event){
        // Vérifie si un patient a été sélectionné du comboBox
        if (cboPatient.getValue() != null){
            // Création d'une alerte de confirmation ainsi que son contenu
            Alert modifierConfirmationAlert = new Alert(AlertType.CONFIRMATION);
            modifierConfirmationAlert.setTitle("Confirmation de la modification des informations");
            modifierConfirmationAlert.setContentText("Voulez-vous vraiment modifier ces informations ?");
            // Attribuer l'alerte au bouton ayant l'id modificationBTN pour afficher la confirmation
            modificationBTN.setOnAction(e -> {
            // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
            modifierConfirmationAlert.showAndWait().ifPresent(resultat -> {
                // Vérifie si le resultat est égal au bouton OK
                if (resultat == ButtonType.OK){
                // Méthode de modification des informations du patient
                Patient sPatient = (Patient)cboPatient.getSelectionModel().getSelectedItem();
                sPatient.setNom(txtNom.getText());
                sPatient.setPrenom(txtPrenom.getText());
                sPatient.setAdresse(txtAdresse.getText());
                sPatient.setAge(Integer.parseInt(txtAge.getText()));
                sPatient.setVille(txtVille.getText());
                sPatient.setProvince(txtProvince.getText());
                sPatient.setCodePostal(txtCodePostal.getText());
                sPatient.setTelephone(txtNumTel.getText());
                sPatient.setDateNaissance(String.valueOf(DateNaissance.getValue()));
                sPatient.setAssurance((Assurance)choixAssurance.getValue());
                // Conversion du String de la date de naissance du patient en local date parce que
                // datePicker prend seulement des dates de type LocalDate et non de Date
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate datePatient = LocalDate.parse(sPatient.getDateNaissance(), dateFormat);
                DateNaissance.setValue(datePatient);
                // Dialog qui dit confirme à l'utilisateur que les modifications ont été faites
                Dialog<String> modReussi = new Dialog<>();
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

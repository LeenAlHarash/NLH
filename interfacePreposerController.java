package projet;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
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
    private DatePicker txtDateNaissance, dateAdmission, dateChirurgie;
    @FXML
    private TextField txtAge; 
    @FXML
    private ComboBox cboAssurancesCr, cboMedecinCr, cboPatientsA, comboMedecinAdmission; 
    @FXML
    private TextField txtRAMQ;
    @FXML
    private CheckBox Televiseur ,Telephone;
    @FXML
    private RadioButton chirurgOui;
    @FXML
    private Pane chirurgOuiPANE;
    @FXML
    private RadioButton chirurgNon;
    @FXML
    private ComboBox combolit, combodepartement, cboID;     
    @FXML
    private TextField combomedecin;
    @FXML
    private TextField rechercheRAMQ, factureRAMQ;
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
        
        //affichage combobox departement, admissions, assurance et patients
        cboID.setItems(admissions);
        combodepartement.setItems(departements);
        cboAssurancesCr.setItems(assurance);
        cboPatientsA.setItems(patients);
        
        //pour appeler la methode verification
        Verification();
    }
   
    
    //methode pour lits et medecins
    @FXML
    public void Verification() {    
        //affichage de combobox lits
        combolit.setItems(lits);
        //au cas que le lit qu'on choisi est occupé, si le boolean = true, alors le lit est occupé donc erreur
        combolit.setOnAction(event -> {
            Lit litChoisi = (Lit) combolit.getSelectionModel().getSelectedItem();
            if (litChoisi != null) {
                if (litChoisi.isOccupe()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lit Occupé");
                    alert.setHeaderText("Attention!");
                    alert.setContentText("Le lit sélectionné est occupé. Veuillez en choisir un autre.");
                    alert.showAndWait();
                }
            }
        });
        
        
        //affichage combomedecin pour créer un patient
        cboMedecinCr.setItems(medecins);
        //personnaliser l'affichage dans le combobox
        cboMedecinCr.setCellFactory(listView -> new ListCell<Medecin>() {
            @Override
            //updateItem = pour changer l'affichage forcement d'un toString + elle appartient à un JavaFX ListCell class
            protected void updateItem(Medecin medecin, boolean vide) {
                super.updateItem(medecin, vide);
                //verifie si la cellule est vide ou si l'objet Medecin est null
                if (vide || medecin == null) {
                    setText(null); //supprime le texte de la cellule
                } else {
                    //affichage du nom et prenom
                    setText(medecin.getNom() + ", " + medecin.getPrenom());
                }
            }
        });
        
        //assurer que le nom,prénom sélectionné restent affichés dans le comboBox après la sélection, au lieu d'afficher la méthode toString()
        cboMedecinCr.setButtonCell(new ListCell<Medecin>() {
            @Override
            protected void updateItem(Medecin medecin, boolean vide) {
                super.updateItem(medecin, vide);
                if (vide || medecin == null) {
                    setText(null);
                } else {
                    //affichage du nom et prenom dans le combobox
                    setText(medecin.getNom() + " " + medecin.getPrenom());
                }
            }
        });
                
        //affichage combomedecin pour finalizer un admission
        comboMedecinAdmission.setItems(medecins);
        //meme code qu'avant mais le nom est different (cboMedecinCr VS combomedecin)
        comboMedecinAdmission.setCellFactory(listView -> new ListCell<Medecin>() {
            @Override
            protected void updateItem(Medecin medecin, boolean vide) {
                super.updateItem(medecin, vide);
                if (vide || medecin == null) {
                    setText(null);
                } else {
                    setText(medecin.getNom() + ", " + medecin.getPrenom());
                }
            }
        });
        comboMedecinAdmission.setButtonCell(new ListCell<Medecin>() {
            @Override
            protected void updateItem(Medecin medecin, boolean vide) {
                super.updateItem(medecin, vide);
                if (vide || medecin == null) {
                    setText(null);
                } else {
                    setText(medecin.getNom() + " " + medecin.getPrenom());
                }
            }
        });
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
    
    
    //s'assurer que l'ajout de patient est complet - tab4
    public void creerPatientBtn(ActionEvent event) throws IOException{    
        //Erreur
        if (txtRAMQ.getText().isEmpty() || txtDateNaissance.getValue() == null || cboAssurancesCr.getValue() == null || 
            cboMedecinCr.getValue() == null || txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || 
            txtAdresse.getText().isEmpty() || txtVille.getText().isEmpty() || txtProvince.getText().isEmpty() || 
            txtCodePostal.getText().isEmpty() || txtNumTel.getText().isEmpty() || txtAge.getText().isEmpty()) {
            //Alert Erreur
            Alert erreurSelection = new Alert(Alert.AlertType.ERROR);
            erreurSelection.setTitle("Erreur!");
            erreurSelection.setContentText("Vous n'avez pas rempli le formulaire au complet.");
            erreurSelection.show();
        //Confirmation   
        } else {
            //Alert confirmation
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation de patient!");
            a.setContentText("Êtez-vous certain de vouloir ajouter ce patient?");

            // Changement noms des boutons
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType annulerButton = new ButtonType("Annuler");
            a.getButtonTypes().setAll(ouiButton, annulerButton);

           //enregistrement du patient si le bouton oui est choisi
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ouiButton) {
                Patient p = new Patient(
                    txtRAMQ.getText(), String.valueOf(txtDateNaissance.getValue()),
                    (Assurance) cboAssurancesCr.getValue(), (Medecin) cboMedecinCr.getValue(),
                    txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(),
                    txtVille.getText(), txtProvince.getText(), txtCodePostal.getText(),
                    txtNumTel.getText(), Integer.parseInt(txtAge.getText())
                );
                //ajouter le patient - tab 2
                gestionnaire.ajouterPatient(p);
                //ajouter le patient - liste d'admission
                patients.add(p);
                // sauvegarder le patient ajouté dans le fichier des patients
                gestionnaire.sauvegarderFichiersPatients(patients);

                //alert d'enregistrement
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Patient ajouté!");
                b.setContentText("Patient ajouté avec succès: " + p);
                b.show();  
            }
        }
    }    

    
    //s'assurer que l'admission et finalizer - tab 5 
    public void finalizerBtn() throws IOException {
        if (cboPatientsA == null || combolit == null || combodepartement == null || comboMedecinAdmission == null ||
            dateAdmission.getValue() == null || chirurgOui == null || chirurgNon == null ||
            Televiseur == null || Telephone == null){
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
            //changement de noms des boutons
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType annulerButton = new ButtonType("Annuler");
            a.getButtonTypes().setAll(ouiButton, annulerButton);

            // Attribuer l'alerte au bouton ayant l'id modificationBTN pour afficher la confirmation
            finalizer.setOnAction(e -> {
                // Affichage de l'alerte de confirmation et vérifie si le résultat est présent, si oui, on l'attribue à resultat
                Optional<ButtonType> resultat = a.showAndWait();

                // Vérifie si le résultat est égal au bouton OUI
                if (resultat.isPresent() && resultat.get() == ouiButton) {
                    //incrementer l'id pour la liste d'admission
                    int idAdmission = admissions.isEmpty() ? 1 : admissions.get(admissions.size() - 1).getIdAdmission() + 1;

                    Admission ad = new Admission(
                        idAdmission, chirurgOui.isSelected() || chirurgNon.isSelected(),
                        String.valueOf(dateAdmission.getValue()), String.valueOf(dateChirurgie.getValue()),
                        null, Televiseur.isSelected(), Telephone.isSelected(),
                        (Patient) cboPatientsA.getValue(), (Lit) combolit.getValue(),
                        (Medecin) comboMedecinAdmission.getValue(), (Departement) combodepartement.getValue()
                    );
                    //ajouter un lit à un admission
                    gestionnaire.ajouterLitAdmission(ad);
                    //ajouter l'admission - tab 3
                    gestionnaire.ajouterAdmission(ad);
                    //ajouter dans le combobox d'admissions
                    admissions.add(ad);
                    
                    // sauvegarder le patient ajouté dans le fichier des patients, try-catch proposé par le programme
                    try {
                        gestionnaire.sauvegarderFichiersAdmissions(admissions);
                    } catch (IOException ex) {
                        Logger.getLogger(interfacePreposerController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //alert d'enregistrement
                    Alert b = new Alert(Alert.AlertType.INFORMATION);
                    b.setTitle("Patient ajouté!");
                    b.setContentText("Patient ajouté avec succès: " + ad);
                    b.show();
                }
            });
        }
    }    
    
    
    //si chirurgie == oui -> afficher chirurgOuiPANE
    public void chirurgOuiBtn(){
        //si le bouton est selected = affichage, sinon ca va rester hidden
        chirurgOuiPANE.setVisible(!chirurgOuiPANE.isVisible());
    }    
    //si chirurgie == non -> hide
    public void chirurgNonBtn(){
        //si le bouton est selected = ca va rester cacher
        chirurgOuiPANE.setVisible(false);
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
        }
    }
   
    
    //combobox pour les id d'admission pour en choisir - tab1
    public void combdoAdmission(){
        Admission sAdmission = (Admission)cboID.getSelectionModel().getSelectedItem();
        listeAdmission.setText("ID Admission: "+ sAdmission.getIdAdmission() + "\nDate d'admission: " + sAdmission.getDateAdmission() + "\nNom du patient: " + sAdmission.getPatient().getNom() + " , " +  sAdmission.getPatient().getPrenom()
                 + "\nMedecin: " + sAdmission.getMedecin().getNom() + " , " +  sAdmission.getMedecin().getPrenom() + "\nDepartement: " + sAdmission.getDepartement() + "\nTeleviseur Loué: " + sAdmission.getTeleviseurLoue() + "\nTelephone loué: " + sAdmission.getTelephoneLoue()
                 + "\nLit: "+ sAdmission.getLit() + "\nChirurgie: " + sAdmission.getChirurgieProgramee() + "\nDate de la chirurgie: " + sAdmission.getDateChirurgie());
    }
    
    
    //textfield pour effectuer la recherche par ramq pour trouver le facture du patient - tab 6
    public void rechercheFactures(ActionEvent actionEvent){
        facturePatient.setText(gestionnaire.afficherFacturePatient(factureRAMQ.getText()));

        // Alerte au cas ou l'utilisateur ne met pas de numRAMQ dans le TextField
        if (factureRAMQ.getText().isEmpty()){
            Alert c = new Alert(Alert.AlertType.ERROR);
            c.setTitle("Erreur de saisie");
            c.setContentText("Veuillez saisir le numéro RAMQ du patient que vous recherchez.");
            c.show();
        }
    }
}

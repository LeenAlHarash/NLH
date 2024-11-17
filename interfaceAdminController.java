package tp3;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import TP3.Patient;
import TP3.Medecin;
import TP3.PreposeAdmission;
import TP3.Assurance;
import TP3.Admission;
import TP3.Lit;
import TP3.Departement;

/**
 * @author Benjamin Melis
 */

public class interfaceAdminController implements Initializable {

    // ObservableLists
    ObservableList<Patient> patients = FXCollections.observableArrayList();
    ObservableList<Medecin> medecins = FXCollections.observableArrayList();
    ObservableList<PreposeAdmission> preposes = FXCollections.observableArrayList();
    ObservableList<Admission> admissions = FXCollections.observableArrayList();

    // FXML ListView and ComboBox elements
    @FXML
    ListView adminListePatient;  
    @FXML
    ListView adminListeMedecin;
    @FXML
    ListView adminListePrepose;
    @FXML
    ListView adminEtatAdmission;
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

    // FXML Buttons 
    @FXML
    private Button adminModification, adminSupprimer, deconnexionBTN;

    
    public void initialize(URL url, ResourceBundle rb) {
        Assurance assurance1 = new Assurance(300, "Beneva");
        Assurance assurance2 = new Assurance(500, "AGECR");

        // Patients
        Patient p1 = new Patient("200120", "2010-01-03", assurance1, "LaFleur", "Tita", "2004, 10e avenue", "Montréal", "Quebec", "H1X 1J7", "514-098-6453", 14);
        Patient p2 = new Patient("548214", "2000-09-08", assurance2, "Tremblay", "John", "8415, 15e avenue", "Montréal", "Quebec", "H1X 1A6", "438-458-5126", 24);
        Patient p3 = new Patient("698532", "2005-08-10", assurance2, "LaTour", "Mary", "5482, 58e avenue", "Montréal", "Quebec", "H1X 5H1", "438-582-9615", 19);
        Patient p4 = new Patient("876452", "1999-09-05", assurance1, "Abed", "Fatima", "8734, 16e avenue", "Laval", "Quebec", "H1Q 8W2", "514-176-8723", 25);
        Patient p5 = new Patient("165432", "1986-10-31", assurance2, "Abdellah", "Aziz", "1234, 19e avenue", "Laval", "Quebec", "H1Z 3A9", "514-395-0932", 38);

        // Doctors (Medecins)
        Medecin m1 = new Medecin("1234-5678", "paul@hospital.com", "password", "Paul", "Dr", "Address1", "City1", "Province1", "12345", "123-4567");
        Medecin m2 = new Medecin("2345-6789", "sophie@hospital.com", "password", "Sophie", "Dr", "Address2", "City2", "Province2", "23456", "234-5678");
        Medecin m3 = new Medecin("3456-7890", "lise@hospital.com", "password", "Lise", "Dr", "Address3", "City3", "Province3", "34567", "345-6789");
        Medecin m4 = new Medecin("4567-8901", "jean@hospital.com", "password", "Jean", "Dr", "Address4", "City4", "Province4", "45678", "456-7890");
        Medecin m5 = new Medecin("5678-9012", "marc@hospital.com", "password", "Marc", "Dr", "Address5", "City5", "Province5", "56789", "567-8901");

        // Prepose Admission
        PreposeAdmission pr1 = new PreposeAdmission(201, "lucie@hospital.com", "password", "Lucie", "Prepose", "Address1", "City1", "Province1", "12345", "123-4567");
        PreposeAdmission pr2 = new PreposeAdmission(202, "jean@hospital.com", "password", "Jean", "Prepose", "Address2", "City2", "Province2", "23456", "234-5678");
        PreposeAdmission pr3 = new PreposeAdmission(203, "marie@hospital.com", "password", "Marie", "Prepose", "Address3", "City3", "Province3", "34567", "345-6789");
        PreposeAdmission pr4 = new PreposeAdmission(204, "luc@hospital.com", "password", "Luc", "Prepose", "Address4", "City4", "Province4", "45678", "456-7890");
        PreposeAdmission pr5 = new PreposeAdmission(205, "nadia@hospital.com", "password", "Nadia", "Prepose", "Address5", "City5", "Province5", "56789", "567-8901");

        // Departments
        Departement dep1 = new Departement(1, "Cardiology");
        Departement dep2 = new Departement(2, "Neurology");
        Departement dep3 = new Departement(3, "Orthopedics");
        Departement dep4 = new Departement(4, "Pediatrics");
        Departement dep5 = new Departement(5, "Surgery");


        // Lit 
        Lit lit1 = new Lit("Room 101", false, "Standard", dep1);
        Lit lit2 = new Lit("Room 102", false, "VIP", dep1);
        Lit lit3 = new Lit("Room 103", true, "ICU", dep1);
        Lit lit4 = new Lit("Room 104", false, "Standard", dep1);
        Lit lit5 = new Lit("Room 105", true, "Emergency", dep1);


        // Add data to the ObservableLists
        patients.addAll(p1, p2, p3, p4, p5);
        medecins.addAll(m1, m2, m3, m4, m5);
        preposes.addAll(pr1, pr2, pr3, pr4, pr5);
        admissions.addAll(new Admission(1, true, "2024-11-01", "2024-11-05", "2024-11-10", true, false, p1, lit1, m1, dep1),
                          new Admission(2, false, "2024-10-15", "2024-10-20", "2024-10-22", false, true, p2, lit2, m2, dep2),
                          new Admission(3, true, "2024-09-10", "2024-09-12", "2024-09-15", true, true, p3, lit3, m3, dep3),
                          new Admission(4, false, "2024-08-05", "2024-08-07", "2024-08-10", false, false, p4, lit4, m4, dep4),
                          new Admission(5, true, "2024-07-20", "2024-07-25", "2024-07-30", true, true, p5, lit5, m5, dep5));


        // Set the items in the ListViews
        adminListePatient.setItems(patients);
        adminListeMedecin.setItems(medecins);
        adminListePrepose.setItems(preposes);
        adminEtatAdmission.setItems(admissions);

        // Set the ComboBox for selecting Medecin
        selectionMedecin.setItems(medecins);
    }


    // ComboBox for Medecin
    @FXML
    public void comboMedecin(ActionEvent actionEvent) {
        Medecin selectedMedecin = (Medecin)selectionMedecin.getSelectionModel().getSelectedItem(); 

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthenSAMPLE.fxml")); 
        Scene scene = new Scene(fxmlLoader.load());
        // Redirection à la page d'authentification à partir du bouton déconnexion
        Stage stage = (Stage)deconnexionBTN.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    public void adminModification(ActionEvent event) {
       
    }

   
    @FXML
    public void adminSupprimer(ActionEvent event) {
       
    }
}
package projet;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * @author Mariyam Hanfaoui && Leen Al Harash && Benjamin Melis
 */

public class Gestionnaire {
    // Déclaration des attributs privés
    private final int nbPatients = 200; // Pour avoir le nombre de patients maximum dans le système
    private final int nbLits = 200; // Pour avoir le nombre de lits maximum dans le système
    private final int nbLitStandard = 100;
    private final int nbLitPrive = 50;
    private final int nbLitSemiPrive = 50;
    private double montant, location; // Montants séparés pour les tarifs quotidiens
    private int jours=0; // Jours pour les tarifs quotidiens
    private Medecin medecinConnecte;
    private ArrayList<Admission> admissions; //liste admission
    private ArrayList<Patient> patients; //liste patient
    private ArrayList<Medecin> medecins; //liste medecin
    private ArrayList<PreposeAdmission> preposes; //liste preposer
    private ArrayList<Lit> lits; //liste lits
    private ArrayList<Assurance> assurances; //liste assurance
    private ArrayList<Departement> departements; //liste departement
    private PreposeAdmission preposeConnecte;
    private HashMap<String, Medecin> medecinMap = new HashMap<>();

    // Méthode constructeur de la classe Gestionnaire
    public Gestionnaire() {
        this.patients = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.preposes = new ArrayList<>();
        this.admissions = new ArrayList<>();
        this.departements = new ArrayList<>();
        this.lits = new ArrayList<>();
        this.assurances = new ArrayList<>();
        
        // Remplir le tableau des assurances
        Assurance assurance1 = new Assurance(300, "Beneva");
        Assurance assurance2 = new Assurance(500, "AGECR");
        Assurance assurance3 = new Assurance(700, "Éducaloi");
        Assurance assurance4 = new Assurance(900, "Croix Bleue");
        
        assurances.add(assurance1);
        assurances.add(assurance2);
        assurances.add(assurance3);
        assurances.add(assurance4);
        
        // Remplir tableau des medecins
        Medecin m1 = new Medecin("1234-5678", "paul@nlh.com", "medecin0", "Paul", "Marie", "2004, 10e avenue", "Montreal", "Quebec", "H3H 2N4", "123-4567");
        Medecin m2 = new Medecin("2345-6789", "sophie@nlh.com","medecin1", "Sophie", "Lana-Del", "8415, 15e avenue", "Montreal", "Quebec", "X8X 6G8", "234-5678");
        Medecin m3 = new Medecin("3456-7890", "lise@nlh.com", "medecin2", "Lise", "Jean-Pierre", "5482, 58e avenue", "Montreal", "Quebec", "L0P 3K1", "345-6789");
        Medecin m4 = new Medecin("4567-8901", "jean@nlh.com", "medecin3", "Jean", "Rosaline", "8734, 16e avenue", "Montreal", "Quebec", "H1J 4P2", "456-7890");
        Medecin m5 = new Medecin("5678-9012", "marc@nlh.com", "medecin4", "Marc", "Derek", "1234, 19e avenue", "Montreal", "Quebec", "P1P 3M3", "567-8901");
        
        medecins.add(m1);
        medecins.add(m2);
        medecins.add(m3);
        medecins.add(m4);
        medecins.add(m5);
        
        // Remplir tableau des patients
        Patient p1 = new Patient("200120", "2010-01-03", assurance1, m1, "LaFleur", "Tita", "2004, 10e avenue", "Montréal", "Quebec", "H1X 1J7", "514-098-6453",14);
        Patient p2 = new Patient("548214", "2000-09-08", assurance4, m2, "Tremblay", "John", "8415, 15e avenue", "Montréal", "Quebec", "H1X 1A6", "438-458-5126",24);
        Patient p3 = new Patient("698532", "2005-08-10", assurance3, m3, "LaTour", "Mary", "5482, 58e avenue", "Montréal", "Quebec", "H1X 5H1", "438-582-9615",19);
        Patient p4 = new Patient("876452", "1999-09-05", assurance1, m4, "Abed", "Fatima", "8734, 16e avenue", "Laval", "Quebec", "H1Q 8W2", "514-176-8723",25);
        Patient p5 = new Patient("165432", "1986-10-31", assurance2, m5, "Abdellah","Aziz","1234, 19e avenue","Laval","Quebec","H1Z 3A9","514-395-0932",38);
        Patient p6 = new Patient("982741", "1977-04-21", assurance1, m1, "Choi", "Mikasa", "6104, 2e avenue", "Montréal", "Quebec", "H1X 8Q7", "514-200-2020",47);
        Patient p7 = new Patient("091834", "1999-06-17", assurance4, m2, "Johns", "Jack", "1010, 8e avenue", "Montréal", "Quebec", "H1Q 9I7", "438-741-8529",25);
        Patient p8 = new Patient("176549", "2013-01-02", assurance3, m3, "Abbas", "Akram", "9999, 64e avenue", "Laval", "Quebec", "H1X 3O2", "438-963-0145",11);
        Patient p9 = new Patient("300981", "2002-03-24", assurance2, m4, "Michaud", "Philippe", "7836, 13e avenue", "Montréal", "Quebec", "H1J 1K6", "514-654-9874",22);
        Patient p10 = new Patient("816543", "1959-11-05", assurance2, m5, "Trudeau", "Mark", "2000, 20e avenue", "Repentigny", "Quebec", "H1X 6J6", "438-000-1111",65);
        
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patients.add(p5);
        patients.add(p6);
        patients.add(p7);
        patients.add(p8);
        patients.add(p9);
        patients.add(p10);
        
        // Remplir tableau des preposés
        PreposeAdmission pr1 = new PreposeAdmission(201, "lucie@nlh.com", "prep0", "Lucie", "Aziz", "2004, 10e avenue", "Montreal", "Quebec", "H3H 2N4", "123-4567");
        PreposeAdmission pr2 = new PreposeAdmission(202, "jean@nlh.com", "prep1", "Jean", "Singh", "5482, 58e avenue", "Montreal", "Quebec", "X8X 6G8", "234-5678");
        PreposeAdmission pr3 = new PreposeAdmission(203, "marie@nlh.com", "prep2", "Marie", "Luisan", "8734, 16e avenue", "Montreal", "Quebec", "L0P 3K1", "345-6789");
        PreposeAdmission pr4 = new PreposeAdmission(204, "luca@nlh.com", "prep3", "Luca", "Pierre", "1234, 19e avenue", "Montreal", "Quebec", "P1P 3M3", "456-7890");
        PreposeAdmission pr5 = new PreposeAdmission(205, "nadia@nlh.com", "prep4", "Nadia", "Melisel", "5487, 16e avenue", "Montreal", "Quebec", "H1J 4P2", "567-8901");
        
        preposes.add(pr1);
        preposes.add(pr2);
        preposes.add(pr3);
        preposes.add(pr4);
        preposes.add(pr5);
        
        // Remplir tableau des départements
        Departement departement1 = new Departement(100, "Ob/Gyn");
        Departement departement2 = new Departement(200, "Chirurgie");
        Departement departement3 = new Departement(300, "Pédiatrie");
        Departement departement4 = new Departement(400, "Imagerie médicale");
        Departement departement5 = new Departement(500, "Régulier");
        
        departements.add(departement1);
        departements.add(departement2);
        departements.add(departement3);
        departements.add(departement4);
        departements.add(departement5);
        
        // Remplir tableau des lits
        Lit lit1 = new Lit("1", true, "Standard", departement2);
        Lit lit2 = new Lit("2", false, "Semi-privé", departement3);
        Lit lit3 = new Lit("3", false, "Standard", departement5);
        Lit lit4 = new Lit("4", true, "Semi-privé", departement1);
        Lit lit5 = new Lit("5", false, "Privé", departement2);
        
        lits.add(lit1);
        lits.add(lit2);
        lits.add(lit3);
        lits.add(lit4);
        lits.add(lit5);

        // Remplir tableau admissions
        Admission A1 = new Admission(1, true, "2024-11-01", "2024-11-05", "2024-11-10", true, false, p1, lit1, m1, departement1);
        Admission A2 = new Admission(2, false, "2024-10-15", "2024-10-20", "2024-10-22", false, true, p2, lit2, m2, departement2);
        Admission A3 = new Admission(3, true, "2024-09-10", "2024-09-12", "2024-09-15", true, true, p3, lit3, m3, departement3);
        Admission A4 = new Admission(4, false, "2024-08-05", "2024-08-07", "2024-08-10", false, false, p4, lit4, m4, departement4);
        Admission A5 = new Admission(5, true, "2024-07-20", "2024-07-25", "2024-07-30", true, true, p5, lit5, m5, departement5);
        
        admissions.add(A1);
        admissions.add(A2);
        admissions.add(A3);
        admissions.add(A4);
        admissions.add(A5);
    }
    
    // Méthodes accesseurs pour les ArrayLists afin de les accéders dans nos contrôleurs
    public ArrayList<Patient> getPatientsListe(){
        return this.patients;
    }
    
    public ArrayList<Medecin> getMedecinsListe(){
        return this.medecins;
    }
    
    public ArrayList<PreposeAdmission> getPreposesListe(){
        return this.preposes;
    }
    
    public ArrayList<Admission> getAdmissionsListe(){
        return this.admissions;
    }
    
    public ArrayList<Departement> getDepartementsListe(){
        return this.departements;
    }
    
    public ArrayList<Lit> getLitsListe(){
        return this.lits;
    }
    
    public ArrayList<Assurance> getAssurancesListe(){
        return this.assurances;
    }
    
    // Méthode qui ajoute un patient à la liste des patients
    public String ajouterPatient(Patient patient) {
        // Si le nombre de patients n'a pas été atteint
        if (patients.size() < nbPatients){
            if (!patients.contains(patient)){
                patients.add(patient);
                return "Le patient a été ajouté avec succès.";
            } else {
                return "Le patient existe déjà.";
            }
        }
        return "Il n'y a plus de places.";
    }
    
    // Méthode pour l'ajout d'un patient à un département précis selon s'ils ont une chirurgie ou pas
    public String ajouterPatientDepartement(Patient patient) {
        for (Admission admission : admissions) {
            if (admission.getPatient() != null && admission.getPatient().equals(patient)) {
                if (admission.getChirurgieProgramee()) {
                    admission.getLit().setDepartement(departements.get(1)); // Chirurgie
                } else if (admission.getPatient().getAge() <= 16) {
                    admission.getLit().setDepartement(departements.get(2)); // Pédiatrie
                } else {
                    admission.getLit().setDepartement(departements.get(4)); // Régulier
                }
                return "Patient ajouté au département.";
            }
        }
        return "Patient non trouvé.";
    }
    
    // Méthode qui cherche si le patient existe déjà et le retourne
    public String rechercherPatient(String numRAMQ) {
        for (Patient patient : patients) {
            if (patient.getNumRAMQ().equalsIgnoreCase(numRAMQ)) {
                return "NUMRAMQ: " + patient.getNumRAMQ() + "\nNom du patient: " + patient.getNom() + " " + patient.getPrenom() + "\nDate de naissance: " + patient.getDateNaissance() +
                        "\nÂge du patient: " + patient.getAge() + "\nAdresse: " + patient.getAdresse() + "\nVille & Province: " + patient.getVille() + ", " + patient.getProvince() 
                        + "\nCode Postal: " + patient.getCodePostal() + "\nNuméro de téléphone: "+ patient.getTelephone() + "\nAssurance: " + patient.getAssurance();
            }
        }
        return "\nPatient non trouvé.";
    }
    
    // Méthode qui ajoute une admission du patient si il existe
    public String ajouterAdmission(Admission admission) {
        if (!admissions.contains(admission)) {
            admissions.add(admission);
            return "Admission ajoutée avec succès.";                              
        } else {
            return "Cette admission existe dejà.";              
        }
    }
    
    // Méthode qui ajoute un département à la liste des départements
    public String ajouterDepartement(Departement departement) {
        if (departement != null) {
            departements.add(departement);
            return "Département ajouté.";
        }
        return "Département non valide.";
    }
    
    // Méthode qui ajoute à une admission selon les conditions de l'assurance
    public String ajouterLitAdmission(Admission admission) {
        // Compteurs pour les types de lits
        int comptLitsStandard = 0; // Compteur pour les lits standard
        int comptLitsSemiPrives = 0; // Compteur pour les lits semi-privés
        int comptLitsPrives = 0; // Compteur pour les lits privés
        
        // Compter le nombre de lits de chaque type
        for (Lit lit : lits) {
            switch (lit.getType()) {
                case "Standard":
                    comptLitsStandard++;
                    break;
                case "Semi-privé":
                    comptLitsSemiPrives++;
                    break;
                case "Privé":
                    comptLitsPrives++;
                    break;
            }
        }
        
        // Si la liste des patients n'excède pas le maximum de patients
        if (patients.size() < nbPatients) {
            // Logique pour attribuer le lit
            String litType = "Standard"; // Valeur par défaut
            for (Patient patient : patients) {
                // Si le patient n'a pas d'assurance et qu'il n'y a plus de lits standards
                if (patient.getAssurance() == null && comptLitsStandard >= nbLitStandard) {
                    // Vérifie si le préposé est connecté et s'il n'y a plus de lits semi-privés
                    if (preposeConnecte != null && comptLitsSemiPrives >= nbLitSemiPrive) {
                        litType = "Privé"; // Attribuer un lit privé
                        comptLitsPrives++;
                    } else {
                        litType = "Semi-privé"; // Attribuer un lit semi-privé
                        comptLitsSemiPrives++;
                    }
                } else {
                    litType = "Semi-Privé"; // Attribuer un lit semi-privé par défaut
                    comptLitsSemiPrives++;
                }
            }
            admission.getLit().setType(litType); // Appliquer le type déterminé à l'admission
            return "Lit ajouté: " + litType; // Message de confirmation
        }
        return "Il n'y a plus de places"; // Message si pas de places disponibles
    }
    
    // Méthode qui permet au médecin de donner un congé au patient recherché
    public String donnerConge(String numRAMQ, String date) {
        // Si le médecin est connecté
        if (medecinConnecte != null){
            for (Admission admission : admissions) {
                if (admission.getPatient().getNumRAMQ().equals(numRAMQ)) {
                    admission.setDateConge(date);
                    admission.getLit().setOccupe(false); // Libéré le lit après que le congé a été donnée
                    return "Le patient a reçu son congé et le lit a été libérée.";
                }
            }
            return "Aucune admission trouvée pour ce patient.";
        }
        return "Le médecin n'est pas connecté";
    }
    
    // Méthode qui ajoute un medecin à la liste des medecins
    // Lorsqu'un médecin se connecte, on ajoute le médecin connecté à la liste des médecins
    public String ajouterMedecin(Medecin medecin) {
        if (!medecins.contains(medecin)) {
            medecins.add(medecin);
            return "Médecin ajoutée avec succès.";
        } else {
            return "Ce médecin existe déjà dans la liste.";
        }
    }
    
    //Methode pour aider a continuer les numeros des medecins ajouter (m1, m2, m3, etc)
    public void numerosDeMedecin(String key, Medecin medecin) {
        medecinMap.put(key, medecin);
    }
    
    // Méthode pour les tarifs quotidiennes de chambres
    public double tarifsQuotidienneChambre(Admission admissions){
        double montant = 0;
        if (admissions.getLit().getType().equals("Semi-privé")){
            montant += 267;
        } else if (admissions.getLit().getType().equals("Privé")) {
            montant += 571;
        }
        return montant;
    }
    
    // Méthode pour les tarifs quotidiennes pour locations
    public double tarifsQuotidienneLocations(Admission admissions){
        double location = 0;
        if (admissions.getTelephoneLoue() && admissions.getTeleviseurLoue()) {
            location += 50;
        } else if (admissions.getTeleviseurLoue()) {
            location += 42.50;
        } else if (admissions.getTelephoneLoue()) {
            location += 7.50;
        }
        return location;
    }
    
    // Methode pour calculer le nombre de jours entre l'admission et la dateconge
    public int calculeJour(Patient patient) {  
        int jours = 0;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Admission admission : admissions) {
            //Filtrer les admissions du patient
            if (admission.getPatient() != null && admission.getPatient().equals(patient)) {
                //verifier que les informations sont là
                if (admission.getDateAdmission() == null) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Erreur!");
                    a.setContentText("Il n'y a pas de date d'admission pour ce patient. Veuillez lui donner une admission.");
                    a.show();
                } else if (admission.getDateConge() == null) {
                    Alert b = new Alert(Alert.AlertType.ERROR);
                    b.setTitle("Erreur!");
                    b.setContentText("Il n'y a pas de date congé pour ce patient. Veuillez demander au medecin de liberer le patient afin de voir les montants de la facture.") ;
                    b.show();
                } else {
                    LocalDate dateAdmission = LocalDate.parse(admission.getDateAdmission(), format);
                    LocalDate dateConge = LocalDate.parse(admission.getDateConge(), format);

                    //calculer les jours entre dateAdmissione et dateConge
                    jours += Math.abs(ChronoUnit.DAYS.between(dateAdmission, dateConge));
                }
            }     
        }
        return jours;  
    }
   
    // Méthode qui affiche la facture du patient selon le numRAMQ rechercher
    public String afficherFacturePatient(String numRAMQ) {
        for (Admission admission : admissions) {
            while (admission.getPatient().getNumRAMQ().equals(numRAMQ)) {
                int jours = calculeJour(admission.getPatient());
                double factureTotal = (tarifsQuotidienneChambre(admission) + tarifsQuotidienneLocations(admission)) * jours;

                return "Facture pour le patient : " + admission.getPatient().getNom() + " , " + admission.getPatient().getPrenom() + " ; Numero de RAMQ: " + admission.getPatient().getNumRAMQ()+ "\n===================" +
                        "\nNom du medecin: " + admission.getMedecin().getNom() + " , " + admission.getMedecin().getPrenom() +
                        "\nNom de departement: "+ admission.getDepartement() +
                        "\nType de chambre: " + admission.getLit().getType() + "\n"+
                        "\nCalculs pour la facture: " +
                        "\nTotal Chambre par jour: " + tarifsQuotidienneChambre(admission) + "$/" + jours + " = " + tarifsQuotidienneChambre(admission)*jours + "$" +
                        "\nTotal location televiseur/telephone par jour: " + tarifsQuotidienneLocations(admission) + "$/" + jours + " = " + tarifsQuotidienneLocations(admission)*jours + "$" +
                        "\nMontant total : " + factureTotal + "$";
            }
        }
        return "\nAucune admission trouvée pour ce patient.";
    }
    
    // Méthode qui affiche les informations des patients dans un fichier
    // Moyen de sauvegarder les patients dans un fichier
    // Changement du paramètre d'un ArrayList à un ObservableList parce qu'on effectue les changements avec un ObservableList
    public String sauvegarderFichiersPatients(ObservableList<Patient> patients) throws IOException {

        // Flux de sortie qui écrit les patients dans un fichier pour les patients
        BufferedWriter fichierPatients = new BufferedWriter(new FileWriter("fichiersPatients.txt"));

        // Écrit tous les patients de l'arraylist dans le fichier
        for(Patient patient : patients){
            fichierPatients.write("NUMRAMQ: " + patient.getNumRAMQ() + "\nNom du patient: " + patient.getNom() + " " + patient.getPrenom() + "\nDate de naissance: " + patient.getDateNaissance() +
                        "\nÂge du patient: " + patient.getAge() + "\nAdresse: " + patient.getAdresse() + "\nVille & Province: " + patient.getVille() + ", " + patient.getProvince() 
                        + "\nCode Postal: " + patient.getCodePostal() + "\nNuméro de téléphone: "+ patient.getTelephone() + "\nAssurance: " + patient.getAssurance());
            fichierPatients.write("\n");
            fichierPatients.newLine();
        }
        // Fermer le flux du fichier
        fichierPatients.close();

        return "Fichier crée et les patients ont été sauvegardés!";
    }
    
    // Méthode qui affiche les informations des admissions dans un fichier
    // Moyen de sauvegarder les admissions dans un fichier
    // Changement du paramètre d'un ArrayList à un ObservableList parce qu'on effectue les changements avec un ObservableList
    public String sauvegarderFichiersAdmissions(ObservableList<Admission> admissions) throws IOException {

        // Flux de sortie qui écrit les patients dans un fichier pour les patients
        BufferedWriter fichierAdmissions = new BufferedWriter(new FileWriter("fichiersAdmissions.txt"));

        // Écrit tous les admissions de l'arraylist dans le fichier
        for(Admission admission : admissions){
            fichierAdmissions.write("ID d'Admission: " + admission.getIdAdmission() +
                                    "\nDate d'admission: " + admission.getDateAdmission() +
                                    "\nChirurgie programmée: " + admission.getChirurgieProgramee() +
                                    "\nDate de chirurgie: " + admission.getDateChirurgie() +
                                    "\nDate de congé: " + admission.getDateConge() +
                                    "\nTéléviseur loué: " + admission.getTeleviseurLoue() +
                                    "\nTéléphone loué: " + admission.getTelephoneLoue() +
                                    "\nPatient: " + admission.getPatient().getNom() + " " + admission.getPatient().getPrenom() +
                                    "\nMédecin responsable du patient: " + admission.getMedecin().getNom() + " " + admission.getMedecin().getPrenom() +
                                    "\nDépartement de l'admission: " + admission.getDepartement() +
                                    "\nLit de l'admission :" + admission.getLit());
            fichierAdmissions.write("\n");
            fichierAdmissions.newLine();
        }
        // Fermer le flux du fichier
        fichierAdmissions.close();

        return "Fichier crée et les admissions ont été sauvegardés!";
    }
}

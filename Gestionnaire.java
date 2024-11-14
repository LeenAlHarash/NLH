package TP2;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

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
    
    private ArrayList<Admission> admissions;
    private ArrayList<Patient> patients;
    private ArrayList<Medecin> medecins;
    private ArrayList<Lit> lits;
    private ArrayList<Assurance> assurances;
    private ArrayList<Departement> departements;
    private Medecin medecinConnecte;
    private PreposeAdmission preposeConnecte;
    private double montant, location; // Montants séparés pour les tarifs quotidiens
    private int jours=0; // Jours pour les tarifs quotidiens
    
    
    // Méthode constructeur
    public Gestionnaire(ArrayList<Admission> admissions, ArrayList<Patient> patients, ArrayList<Medecin> medecins, ArrayList<Lit> lits, 
        ArrayList<Assurance> assurances, ArrayList<Departement> departements, Medecin medecinConnecte) {
        this.admissions = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.lits = new ArrayList<>();
        this.assurances = new ArrayList<>();
        this.departements = new ArrayList<>();
        this.medecinConnecte = medecinConnecte;
        
        // Remplir tableau des assurances
        Assurance assurance1 = new Assurance(300, "Beneva");
        Assurance assurance2 = new Assurance(500, "AGECR");
        assurances.add(assurance1);
        assurances.add(assurance2);
        
        
        // Remplir tableau des patients
        patients.add(new Patient("200120", "2010-01-03", assurance1, "LaFleur", "Tita", "2004, 10e avenue", "Montréal", "Quebec", "H1X 1J7", "514-098-6453"));
        patients.add(new Patient("548214", "2000-09-08", assurance2, "Tremblay", "John", "8415, 15e avenue", "Montréal", "Quebec", "H1X 1A6", "438-458-5126"));
        patients.add(new Patient("698532", "2005-08-10", assurance2, "LaTour", "Mary", "5482, 58e avenue", "Montréal", "Quebec", "H1X 5H1", "438-582-9615"));
        
        
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
        Lit lit2 = new Lit("2", true, "Semi-privé", departement3);
        Lit lit3 = new Lit("3", false, "Standard", departement5);
        Lit lit4 = new Lit("4", true, "Semi-privé", departement1);
        Lit lit5 = new Lit("5", false, "Privé", departement2);
        Lit lit6 = new Lit("6", false, "Semi-privé", departement4);
        Lit lit7 = new Lit("7", true, "Privé", departement4);
        Lit lit8 = new Lit("8", true, "Standard", departement5);
        Lit lit9 = new Lit("9", true, "Privé", departement5);
        
        lits.add(lit1);
        lits.add(lit2);
        lits.add(lit3);
        lits.add(lit4);
        lits.add(lit5);
        lits.add(lit6);
        lits.add(lit7);
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
                return "Patient trouvé: " + patient.toString();
            }
        }
        return "Patient non trouvé.";
    }
    
    
    // Méthode qui ajoute une admission du patient si il existe
    public String ajouterAdmission(Admission admission) {
        if (!admissions.contains(admission)) {
            admissions.add(admission);
            return "Admission ajoutée avec succès.";
        } else {
            return "Cette admission existe déjà dans la liste.";
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
    
            
    // Méthode qui permet au médecin de donner un congé au patient
    public String donnerConge(Patient patient, String date) {
        // Si le médecin est connecté
        if (medecinConnecte != null){
            for (Admission admission : admissions) {
                if (admission.getPatient().equals(patient)) {
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
    public String ajouterMedecin() {
        if (medecinConnecte != null && !medecins.contains(medecinConnecte)) {
            medecins.add(medecinConnecte);
            return "Le médecin connecté a été ajouté avec succès.";
        } else {
            return "Médecin déjà ajouté ou non connecté.";
        }
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
        if (admissions.getTeleviseurLoue()){
            location += 42.50;
        } else if (admissions.getTelephoneLoue()) {
            location += 7.50;
        }
        return location;
    }
 
    
    // Method to calculate the number of days for the amounts
    public int calculeJour(Patient patient) {  
        int jours = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Admission admission : admissions) {
            //Filtrer les admissions du patient
            if (admission.getPatient() != null && admission.getPatient().equals(patient)) {
                //S'assurer que les dates d’admission et de sortie sont valides
                if (admission.getDateAdmission() != null && admission.getDateConge() != null) {
                    //Parse les dates en utilisant le format spécifié
                    LocalDate dateAdmission = LocalDate.parse(admission.getDateAdmission(), formatter);
                    LocalDate dateConge = LocalDate.parse(admission.getDateConge(), formatter);

                    //Calcul le nombre de jours entre l'admission et la sortie
                    long daysBetween = ChronoUnit.DAYS.between(dateAdmission, dateConge);
                    jours += daysBetween > 0 ? daysBetween : 0; // Avoid negative days
                }
            }     
        }
        return jours;  
    }

    
    // Méthode qui affiche la facture du patient
    public String afficherFacturePatient(Patient patient) {
        for (Admission admission : admissions) {
            if (admission.getPatient().equals(patient)) {
                double factureTotal = (montant + location) * jours;

                return "Facture pour le patient : " + patient + "\n===================" +
                        "\nNom du medecin: " + admission.getMedecin() +
                        "\nNom de departement: " + admission.getDepartement() + "\n " +
                        "\nType de chambre: " + admission.getLit().getType() +
                        "\nTotal Chambre par jour: " + montant + "$" +
                        "\nTotal location televiseur/telephone par jour: " + location + "$" +
                        "\nMontant total : " + factureTotal + "$";
            }
        }
        return "Aucune admission trouvée pour ce patient.";
    }
    
    
    // Méthode qui affiche les informations des patients dans un fichier
    // Moyen de sauvegarder les patients dans un fichier (On planifie d'associer cet méthode à un bouton dans l'interface, lorsque le médecin est connecté)
    public String sauvegarderFichiersPatients(ArrayList<Patient> patients) throws IOException {

        // Flux de sortie qui écrit les patients dans un fichier pour les patients
        BufferedWriter fichierPatients = new BufferedWriter(new FileWriter("fichiersPatients.txt"));

        // Écrit tous les patients de l'arraylist dans le fichier
        for(Patient patient : patients){
            fichierPatients.write(patient.toString());
            fichierPatients.newLine();
        }
        // Fermer le flux du fichier
        fichierPatients.close();

        return "Fichier crée et les patients ont été sauvegardés!";
    }

    
    // Méthode qui affiche les informations des admissions dans un fichier
    // Moyen de sauvegarder les admissions dans un fichier (On planifie d'associer cet méthode à un bouton dans l'interface, lorsque le médecin est connecté)
    public String sauvegarderFichiersAdmissions(ArrayList<Admission> admissions) throws IOException {

        // Flux de sortie qui écrit les patients dans un fichier pour les patients
        BufferedWriter fichierAdmissions = new BufferedWriter(new FileWriter("fichiersAdmissions.txt"));

        // Écrit tous les admissions de l'arraylist dans le fichier
        for(Admission admission : admissions){
            fichierAdmissions.write(admission.toString());
            fichierAdmissions.newLine();
        }
        // Fermer le flux du fichier
        fichierAdmissions.close();

        return "Fichier crée et les admissions ont été sauvegardés!";
    }
}
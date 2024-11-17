package TP3;

/**
 * @author Mariyam Hanfaoui
 */

public class Admission {
    // Déclaration des attributs privés
    private int idAdmission;
    private boolean chirurgieProgrammee;
    private String dateAdmission;
    private String dateChirurgie;
    private String dateConge;
    private boolean televiseurLoue;
    private boolean telephoneLoue;
    private Patient patient;
    private Lit lit;
    private Medecin medecin;
    private Departement departement;
    
    // Méthode constructeur
    public Admission(int idAdmission, boolean chirurgieProgrammee, String dateAdmission, String dateChirurgie, String dateConge, boolean televiseurLoue, boolean telephoneLoue, Patient patient, Lit lit, Medecin medecin, Departement departement) {
        this.idAdmission = idAdmission;
        this.chirurgieProgrammee = chirurgieProgrammee;
        this.dateAdmission = dateAdmission;
        this.dateChirurgie = dateChirurgie;
        this.dateConge = dateConge;
        this.televiseurLoue = televiseurLoue;
        this.telephoneLoue = telephoneLoue;
        this.patient = patient;
        this.lit = lit;
        this.medecin = medecin;
        this.departement = departement;
    }
    
    // Méthodes accesseurs
    public int getIdAdmission(){
        return idAdmission;
    }
    
    public boolean getChirurgieProgramee(){
        return chirurgieProgrammee;
    }
    
    public String getDateAdmission(){
        return dateAdmission;
    }
    
    public String getDateChirurgie(){
        return dateChirurgie;
    }
    
    public String getDateConge(){
        return dateConge;
    }
    
    public boolean getTeleviseurLoue(){
        return televiseurLoue;
    }
    
    public boolean getTelephoneLoue(){
        return telephoneLoue;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public Lit getLit(){
        return lit;
    }
    
    public Medecin getMedecin(){
        return medecin;
    }
    
    public Departement getDepartement(){
        return departement;
    }
    
    // Méthodes mutateurs
    public void setDateConge(String dateConge){
        this.dateConge = dateConge;
    }
    
    public void setLit(Lit lit){
        this.lit = lit;
    }
    

    // Méthode toString
    @Override
    public String toString(){
        return "Admission : "+ patient +" , "+ idAdmission +" , "+ dateAdmission+ ". \nProgramme de Chirurgie: "+
        chirurgieProgrammee +" , "+ dateChirurgie +".\nMedecin: "+ medecin +" , "+ dateConge +".\nDepartement et lit: "+
        departement +" , "+ lit + ".\nCommodités supplémentaires: "+ televiseurLoue +" , "+ telephoneLoue + ".";
    }
}

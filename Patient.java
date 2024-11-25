package projet;
import java.util.Objects;

/*
 * @author Leen Al Harash
 */

public class Patient extends Personne {   
    // Déclaration des attributs privés
    private final String numRAMQ;
    private String dateNaissance;
    private int age;
    private Assurance assurance;
    
    // Méthode toString
    @Override
    public String toString(){
        return "NUMRAMQ: "+numRAMQ;
    }
    
    // Méthode equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient patient = (Patient) obj;
        return numRAMQ.equals(patient.numRAMQ);
    }

    // Méthode hashCode
    @Override
    public int hashCode() {
        return Objects.hash(numRAMQ);
    }
    
    // Méthode constructeur
    public Patient(String numRAMQ, String dateNaissance, Assurance assurance, String nom, String prenom, 
            String adresse, String ville, String province, String codePostal, String telephone, int age) {
        super(nom, prenom, adresse, ville, province, codePostal, telephone);
        this.numRAMQ = numRAMQ;
        this.dateNaissance = dateNaissance;
        this.age = age;
        this.assurance = assurance;
    }
    
    // Méthodes accesseurs
    public String getNumRAMQ(){
        return numRAMQ;
    }
    
    public String getDateNaissance(){
        return dateNaissance;
    }
    
    public int getAge(){
        return age;
    }
    
    public Assurance getAssurance(){
        return assurance;
    }

    // Méthodes mutateurs
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }
    
    public void setAge(int age){
        this.age = age;
    }
}

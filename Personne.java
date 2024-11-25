package projet;

/**
 *@author Leen Al Harash
 */

public class Personne {
    
    // Déclaration des attributs privés
    private String nom, prenom;
    private String adresse;
    private String ville , province;
    private String codePostal;
    private String telephone;
    
    // Méthode toString
    @Override
    public String toString(){
        return nom + ", " + prenom + "\nAdresse: " + adresse + ", " + ville
                + ", " + province + "\nCode Postal: " + codePostal + "\nNuméro de téléphone: " + telephone;
    }
    
    // Méthode constructeur
    public Personne(String nom, String prenom, String adresse, String ville, String province, String codePostal, String telephone){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.province = province;
        this.codePostal = codePostal;
        this.telephone = telephone;   
    }
      
    // Méthodes accesseurs
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    public String getTelephone(){
        return telephone;
    }
    
    public String getVille(){
        return ville;
    }
    
    public String getProvince(){
        return province;
    }
    
    public String getCodePostal(){
        return codePostal;
    }       

    // Méthodes mutateurs
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

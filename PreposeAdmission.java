package TP2;

/**
 * @author Mariyam Hanfaoui && Leen Al Harash
 */

/* Classe préposé pour l'authentification de l'administrateur pour faire la sélection d'un lit lorsque les patients ne sont pas couverts
   par une assurance privé et quand le nombre de lits
*/
public class PreposeAdmission extends Personne{
    
    // Déclaration des attributs privés
    private final int idPrepose;
    private String nomUtilisateur;
    private String motDePasse;
    
    // Méthode constructeur
    public PreposeAdmission(int idPrepose, String nomUtilisateur, String motDePasse, String nom, String prenom, String adresse, String ville, String province, 
            String codePostal, String telephone) {
        super(nom, prenom, adresse, ville, province, codePostal, telephone);
        this.idPrepose = idPrepose;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }
    
    // Méthodes accesseurs
    public int getIdPrepose(){
        return idPrepose;
    }
    
    public String getNomUtilisateur(){
        return nomUtilisateur;
    }
    
    public String getMotDePasse(){
        return motDePasse;
    }
    
    // Méthodes mutateurs
    public void setNomUtilisateur(String nomUtilisateur){
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
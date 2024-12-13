package projet;

/**
 * @author Leen Al Harash
 */

public class Medecin extends Personne{   
    // Déclaration des attributs privés
    private final String numeroPermis;
    private String nomUtilisateur;
    private String motDePasse;

    // Méthode toString
    @Override
    public String toString(){
        return "Numero de permis: " + numeroPermis + "\nNom de l'utilisateur: " + nomUtilisateur
                + "\nMot de passe: " + motDePasse;
    }
    
    // Méthode constructeur
    public Medecin(String numeroPermis, String nomUtilisateur, String motDePasse, String nom, String prenom, 
            String adresse, String ville, String province, String codePostal, String telephone) {
        super(nom, prenom, adresse, ville, province, codePostal, telephone);
        this.numeroPermis = numeroPermis;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }
     
    // Méthodes accesseurs
    public String getNumeroPermis() {
        return numeroPermis;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    // Méthodes mutateurs
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }   
}

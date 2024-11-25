package projet;

/**
 * @author Leen Al Harash
 */

public class Assurance {
    // Déclaration des attributs privés
    private int IDAssurance;
    private String nomCompagnie;
    
    // Méthode constructeur
    public Assurance(int IDAssurance, String nomCompagnie) {
        this.IDAssurance = IDAssurance;
        this.nomCompagnie = nomCompagnie;
    }

    // Méthodes accesseurs
    public int getIDAssurance() {
        return IDAssurance;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }
    
    // Méthodes mutateurs
    public void setIDAssurance(int IDAssurance) {
        this.IDAssurance = IDAssurance;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

    // Méthode toString
    @Override
    public String toString(){
        return IDAssurance + " , " + nomCompagnie;
    }
}

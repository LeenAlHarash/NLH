package projet;

/**
 * @author Mariyam Hanfaoui
 */

public class Lit {   
    // Déclaration des attributs privés
    private String numeroLit;
    private boolean occupe;
    private String type;
    private Departement departement;
    
    // Méthode constructeur
    public Lit(String numeroLit, boolean occupe, String type, Departement departement) {
        this.numeroLit = numeroLit;
        this.occupe = occupe;
        this.type = type;
        this.departement = departement;
    }
    
    // Méthodes accesseurs
    public String getNumeroLit(){
        return numeroLit;
    }
    
    public Departement getDepartement(){
        return departement;
    }

    public boolean isOccupe() {
        return occupe;
    }

    public String getType() {
        return type;
    }
    
    // Méthodes mutateurs
    public void setDepartement(Departement departement){
        this.departement = departement;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }
    
    
    // Méthode toString
    @Override
    public String toString(){
        return numeroLit+" , "+type+" , "+occupe+" , "+departement;
    }
}

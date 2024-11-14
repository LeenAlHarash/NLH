package TP2;

/**
 * @author Mariyam Hanfaoui, 2240026
 */

public class Departement {
    // Déclaration des attributs privés
    private int idDepartement;
    private String nomDepartement;
    
    // Méthode constructeur
    public Departement(int idDepartement, String nomDepartement){    
        this.idDepartement = idDepartement;
        this.nomDepartement = nomDepartement;
    }
    
    // Méthodes accesseurs
    public int getIdDepartement(){
        return idDepartement;
    }
    
    public String getNomDepartement(){
        return nomDepartement;
    }
    
    // Méthode toString
    @Override
    public String toString() {
        return "Département : "+idDepartement+" , "+nomDepartement;
    }
}
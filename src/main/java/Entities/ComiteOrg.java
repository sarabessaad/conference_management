package Entities;

public class ComiteOrg {

    private int id_comiteOrg;
    private String nom;

    public ComiteOrg() {
    }

    public ComiteOrg(String nom) {
        this.nom = nom;
    }

    public ComiteOrg(int id_comiteOrg, String nom) {
        this.id_comiteOrg = id_comiteOrg;
        this.nom = nom;
    }

    public int getId_comiteOrg() {
        return id_comiteOrg;
    }

    public void setId_comiteOrg(int id_comiteOrg) {
        this.id_comiteOrg = id_comiteOrg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ComiteOrg{" +
                "id_comiteOrg=" + id_comiteOrg +
                ", nom='" + nom + '\'' +
                '}';
    }
}

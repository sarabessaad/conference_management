package Entities;

public class ComiteSc {

    private int id_comiteSc;
    private String nom;

    public ComiteSc() {
    }

    public ComiteSc(String nom) {
        this.nom = nom;
    }

    public ComiteSc(int id_comiteSc, String nom) {
        this.id_comiteSc = id_comiteSc;
        this.nom = nom;
    }

    public int getId_comiteSc() {
        return id_comiteSc;
    }

    public void setId_comiteSc(int id_comiteSc) {
        this.id_comiteSc = id_comiteSc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ComiteSc{" +
                "id_comiteSc=" + id_comiteSc +
                ", nom='" + nom + '\'' +
                '}';
    }
}

package Entities;

public class MembreSc {

    private int id_membreSc;
    private String nom;
    private String email;
    private String institution;
    private int id_comite_sc;
    private String mdp;

    public MembreSc() {
    }

    public MembreSc(String nom, String email, String institution, int id_comite_sc) {
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_sc = id_comite_sc;
    }

    public MembreSc(int id_membreSc, String nom, String email, String institution, int id_comite_sc) {
        this.id_membreSc = id_membreSc;
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_sc = id_comite_sc;
    }

    public MembreSc(int id_membreSc, String nom, String email, String institution, int id_comite_sc, String mdp) {
        this.id_membreSc = id_membreSc;
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_sc = id_comite_sc;
        this.mdp = mdp;
    }

    public MembreSc(String nom, String email, String institution, int id_comite_sc, String mdp) {
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_sc = id_comite_sc;
        this.mdp = mdp;
    }

    public int getId_membreSc() {
        return id_membreSc;
    }

    public void setId_membreSc(int id_membreSc) {
        this.id_membreSc = id_membreSc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getId_comite_sc() {
        return id_comite_sc;
    }

    public void setId_comite_sc(int id_comite_sc) {
        this.id_comite_sc = id_comite_sc;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return nom;
    }
}

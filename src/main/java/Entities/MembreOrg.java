package Entities;

public class MembreOrg {

    private int id_membreOrg;
    private String nom;
    private String email;
    private String institution;
    private int id_comite_org;

    public MembreOrg() {
    }

    public MembreOrg(int id_membreOrg, String nom, String email, String institution, int id_comite_org) {
        this.id_membreOrg = id_membreOrg;
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_org = id_comite_org;
    }

    public MembreOrg(String nom, String email, String institution, int id_comite_org) {
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.id_comite_org = id_comite_org;
    }

    public int getId_membreOrg() {
        return id_membreOrg;
    }

    public void setId_membreOrg(int id_membreOrg) {
        this.id_membreOrg = id_membreOrg;
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

    public int getId_comite_org() {
        return id_comite_org;
    }

    public void setId_comite_org(int id_comite_org) {
        this.id_comite_org = id_comite_org;
    }

    @Override
    public String toString() {
        return nom;
    }
}

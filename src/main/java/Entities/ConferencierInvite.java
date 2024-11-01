package Entities;

public class ConferencierInvite {

    private int id_conferencier;
    private String nom;
    private String email;
    private String institution;
    private String pays;
    private String titre_pres;

    public ConferencierInvite() {
    }

    public ConferencierInvite(String nom, String email, String institution, String pays, String titre_pres) {
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.pays = pays;
        this.titre_pres = titre_pres;
    }

    public ConferencierInvite(int id_conferencier, String nom, String email, String institution, String pays, String titre_pres) {
        this.id_conferencier = id_conferencier;
        this.nom = nom;
        this.email = email;
        this.institution = institution;
        this.pays = pays;
        this.titre_pres = titre_pres;
    }

    public int getId_conferencier() {
        return id_conferencier;
    }

    public void setId_conferencier(int id_conferencier) {
        this.id_conferencier = id_conferencier;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTitre_pres() {
        return titre_pres;
    }

    public void setTitre_pres(String titre_pres) {
        this.titre_pres = titre_pres;
    }

    @Override
    public String toString() {
        return "ConferencierInvite{" +
                "id_conferencier=" + id_conferencier +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", institution='" + institution + '\'' +
                ", pays='" + pays + '\'' +
                ", titre_pres='" + titre_pres + '\'' +
                '}';
    }
}

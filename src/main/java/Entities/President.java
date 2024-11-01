package Entities;

public class President {

    private int id_president;
    private String nom;
    private String email;
    private String institution;
    private String mdp;

    public President() {
    }

    public President(String nom, String email, String institution) {
        this.nom = nom;
        this.email = email;
        this.institution = institution;
    }

    public President(int id_president, String nom, String email, String institution) {
        this.id_president = id_president;
        this.nom = nom;
        this.email = email;
        this.institution = institution;
    }

    public int getId_president() {
        return id_president;
    }

    public void setId_president(int id_president) {
        this.id_president = id_president;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "President{" +
                "id_president=" + id_president +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
}

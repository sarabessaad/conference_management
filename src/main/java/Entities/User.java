package Entities;

public class User {

    private int id_user;
    private String nom;
    private String mail;
    private String institution;
    private String mdp;

    public User() {
    }

    public User(String nom, String mail, String institution) {
        this.nom = nom;
        this.mail = mail;
        this.institution = institution;
    }

    public User(int id_user, String nom, String mail, String institution) {
        this.id_user = id_user;
        this.nom = nom;
        this.mail = mail;
        this.institution = institution;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        return nom;
    }
}

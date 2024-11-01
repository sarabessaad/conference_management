package Entities;

import java.time.LocalDate;

public class Conference {

    private int id_conference;

    private String titre;

    private String nom_institution;

    private LocalDate date_debut ;

    private LocalDate date_fin;

    private String local;

    private String ville;

    private String pays;

    private String domaine;

    private LocalDate date_articles;

    private LocalDate date_inscri;

    private float frais;

    private int comite_org_id;
    private int comite_sc_id;
    private int president_id;
    private String image;

    public Conference() {
    }

    public Conference(int id_conference, String titre, String nom_institution, LocalDate date_debut, LocalDate date_fin, String local, String ville, String pays, String domaine, LocalDate date_articles, LocalDate date_inscri, float frais, int comite_org_id, int comite_sc_id, int president_id, String image) {
        this.id_conference = id_conference;
        this.titre = titre;
        this.nom_institution = nom_institution;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.local = local;
        this.ville = ville;
        this.pays = pays;
        this.domaine = domaine;
        this.date_articles = date_articles;
        this.date_inscri = date_inscri;
        this.frais = frais;
        this.comite_org_id = comite_org_id;
        this.comite_sc_id = comite_sc_id;
        this.president_id = president_id;
        this.image = image;
    }

    public Conference(String titre, String nom_institution, LocalDate date_debut, LocalDate date_fin, String local, String ville, String pays, String domaine, LocalDate date_articles, LocalDate date_inscri, float frais, int comite_org_id, int comite_sc_id, int president_id, String image) {
        this.titre = titre;
        this.nom_institution = nom_institution;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.local = local;
        this.ville = ville;
        this.pays = pays;
        this.domaine = domaine;
        this.date_articles = date_articles;
        this.date_inscri = date_inscri;
        this.frais = frais;
        this.comite_org_id = comite_org_id;
        this.comite_sc_id = comite_sc_id;
        this.president_id = president_id;
        this.image = image;
    }

    public int getId_conference() {
        return id_conference;
    }

    public void setId_conference(int id_conference) {
        this.id_conference = id_conference;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom_institution() {
        return nom_institution;
    }

    public void setNom_institution(String nom_institution) {
        this.nom_institution = nom_institution;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public LocalDate getDate_articles() {
        return date_articles;
    }

    public void setDate_articles(LocalDate date_articles) {
        this.date_articles = date_articles;
    }

    public LocalDate getDate_inscri() {
        return date_inscri;
    }

    public void setDate_inscri(LocalDate date_inscri) {
        this.date_inscri = date_inscri;
    }

    public float getFrais() {
        return frais;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public int getComite_org_id() {
        return comite_org_id;
    }

    public void setComite_org_id(int comite_org_id) {
        this.comite_org_id = comite_org_id;
    }

    public int getComite_sc_id() {
        return comite_sc_id;
    }

    public void setComite_sc_id(int comite_sc_id) {
        this.comite_sc_id = comite_sc_id;
    }

    public int getPresident_id() {
        return president_id;
    }

    public void setPresident_id(int president_id) {
        this.president_id = president_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id_conference=" + id_conference +
                ", nom_institution='" + nom_institution + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", local='" + local + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", domaine='" + domaine + '\'' +
                ", date_articles=" + date_articles +
                ", date_inscri=" + date_inscri +
                ", frais=" + frais +
                ", comite_org_id=" + comite_org_id +
                ", comite_sc_id=" + comite_sc_id +
                ", president_id=" + president_id +
                '}';
    }
}

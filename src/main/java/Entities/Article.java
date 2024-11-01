package Entities;

import java.util.List;

public class Article {

    private int id_article;
    private String titre;
    private String nom_pdf;
    private String emplacement_pdf;
    private StatusArticle status;
    private int conference_id;
    private int membre_sc_id;
    private List<User> auteurs;

    public Article() {
    }

    public Article(String titre, String nom_pdf, String emplacement_pdf, StatusArticle status, int conference_id, int membre_sc_id) {
        this.titre = titre;
        this.nom_pdf = nom_pdf;
        this.emplacement_pdf = emplacement_pdf;
        this.status = status;
        this.conference_id = conference_id;
        this.membre_sc_id = membre_sc_id;
    }

    public Article(int id_article, String titre, String nom_pdf, String emplacement_pdf, StatusArticle status, int conference_id, int membre_sc_id) {
        this.id_article = id_article;
        this.titre = titre;
        this.nom_pdf = nom_pdf;
        this.emplacement_pdf = emplacement_pdf;
        this.status = status;
        this.conference_id = conference_id;
        this.membre_sc_id = membre_sc_id;
    }

    public Article(int id_article, String titre, String nom_pdf, String emplacement_pdf, StatusArticle status, int conference_id, int membre_sc_id, List<User> auteurs) {
        this.id_article = id_article;
        this.titre = titre;
        this.nom_pdf = nom_pdf;
        this.emplacement_pdf = emplacement_pdf;
        this.status = status;
        this.conference_id = conference_id;
        this.membre_sc_id = membre_sc_id;
        this.auteurs = auteurs;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom_pdf() {
        return nom_pdf;
    }

    public void setNom_pdf(String nom_pdf) {
        this.nom_pdf = nom_pdf;
    }

    public String getEmplacement_pdf() {
        return emplacement_pdf;
    }

    public void setEmplacement_pdf(String emplacement_pdf) {
        this.emplacement_pdf = emplacement_pdf;
    }

    public StatusArticle getStatus() {
        return status;
    }

    public void setStatus(StatusArticle status) {
        this.status = status;
    }

    public int getConference_id() {
        return conference_id;
    }

    public void setConference_id(int conference_id) {
        this.conference_id = conference_id;
    }

    public int getMembre_sc_id() {
        return membre_sc_id;
    }

    public void setMembre_sc_id(int membre_sc_id) {
        this.membre_sc_id = membre_sc_id;
    }

    public List<User> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<User> auteurs) {
        this.auteurs = auteurs;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id_article=" + id_article +
                ", titre='" + titre + '\'' +
                ", nom_pdf='" + nom_pdf + '\'' +
                ", emplacement_pdf='" + emplacement_pdf + '\'' +
                ", status=" + status +
                ", conference_id=" + conference_id +
                ", membre_sc_id=" + membre_sc_id +
                '}';
    }
}

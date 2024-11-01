package Entities;

public class Participation {

    private int id_participation;
    private int id_user;
    private int id_conference;
    private String mode_paiement;

    public Participation() {
    }

    public Participation(int id_participation, int id_user, int id_conference, String mode_paiement) {
        this.id_participation = id_participation;
        this.id_user = id_user;
        this.id_conference = id_conference;
        this.mode_paiement = mode_paiement;
    }

    public Participation(int id_user, int id_conference, String mode_paiement) {
        this.id_user = id_user;
        this.id_conference = id_conference;
        this.mode_paiement = mode_paiement;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_conference() {
        return id_conference;
    }

    public void setId_conference(int id_conference) {
        this.id_conference = id_conference;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id_participation=" + id_participation +
                ", id_user=" + id_user +
                ", id_conference=" + id_conference +
                ", mode_paiement='" + mode_paiement + '\'' +
                '}';
    }
}

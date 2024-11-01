package Services;

import Entities.*;
import Utils.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConferenceService {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<Conference> afficherConf() {

        List<Conference> list = new ArrayList<>();
        try {
            String query = "Select * from conference";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                Conference conference = new Conference();
                conference.setId_conference(RS.getInt("id_conference"));
                conference.setTitre(RS.getString("titre"));
                conference.setDate_articles(RS.getDate("date_articles").toLocalDate());
                conference.setDate_debut(RS.getDate("date_debut").toLocalDate());
                conference.setDate_fin(RS.getDate("date_fin").toLocalDate());
                conference.setDate_inscri(RS.getDate("date_inscri").toLocalDate());
                conference.setDomaine(RS.getString("domaine"));
                conference.setFrais(RS.getFloat("frais"));
                conference.setLocal(RS.getString("local"));
                conference.setNom_institution(RS.getString("nom_institution"));
                conference.setPays(RS.getString("pays"));
                conference.setVille(RS.getString("ville"));
                conference.setComite_org_id(RS.getInt("comite_org_id"));
                conference.setComite_sc_id(RS.getInt("comite_sc_id"));
                conference.setPresident_id(RS.getInt("president_id"));
                conference.setImage(RS.getString("image"));

                list.add(conference);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Conference> rechercheConf(int id) {
        List<Conference> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM conference WHERE id_conference = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                Conference conference = new Conference();
                conference.setId_conference(RS.getInt("id_conference"));
                conference.setTitre(RS.getString("titre"));
                conference.setDate_articles(RS.getDate("date_articles").toLocalDate());
                conference.setDate_debut(RS.getDate("date_debut").toLocalDate());
                conference.setDate_fin(RS.getDate("date_fin").toLocalDate());
                conference.setDate_inscri(RS.getDate("date_inscri").toLocalDate());
                conference.setDomaine(RS.getString("domaine"));
                conference.setFrais(RS.getFloat("frais"));
                conference.setLocal(RS.getString("local"));
                conference.setNom_institution(RS.getString("nom_institution"));
                conference.setPays(RS.getString("pays"));
                conference.setVille(RS.getString("ville"));
                conference.setComite_org_id(RS.getInt("comite_org_id"));
                conference.setComite_sc_id(RS.getInt("comite_sc_id"));
                conference.setPresident_id(RS.getInt("president_id"));
                conference.setImage(RS.getString("image"));

                System.out.println(conference);
                list.add(conference);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public List<Conference> rechercheConfPresident(int id_president) {
        List<Conference> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM conference WHERE president_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_president);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                Conference conference = new Conference();
                conference.setId_conference(RS.getInt("id_conference"));
                conference.setTitre(RS.getString("titre"));
                conference.setDate_articles(RS.getDate("date_articles").toLocalDate());
                conference.setDate_debut(RS.getDate("date_debut").toLocalDate());
                conference.setDate_fin(RS.getDate("date_fin").toLocalDate());
                conference.setDate_inscri(RS.getDate("date_inscri").toLocalDate());
                conference.setDomaine(RS.getString("domaine"));
                conference.setFrais(RS.getFloat("frais"));
                conference.setLocal(RS.getString("local"));
                conference.setNom_institution(RS.getString("nom_institution"));
                conference.setPays(RS.getString("pays"));
                conference.setVille(RS.getString("ville"));
                conference.setComite_org_id(RS.getInt("comite_org_id"));
                conference.setComite_sc_id(RS.getInt("comite_sc_id"));
                conference.setPresident_id(RS.getInt("president_id"));
                conference.setImage(RS.getString("image"));

                System.out.println(conference);
                list.add(conference);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public void ajouterConference(Conference conference) throws SQLException {

        String query = "INSERT INTO conference (titre, date_articles, date_debut, date_fin, date_inscri, domaine, frais, local, nom_institution, pays, ville, president_id, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, conference.getTitre());
        preparedStatement.setDate(2, Date.valueOf(conference.getDate_articles()));
        preparedStatement.setDate(3, Date.valueOf(conference.getDate_debut()));
        preparedStatement.setDate(4, Date.valueOf(conference.getDate_fin()));
        preparedStatement.setDate(5, Date.valueOf(conference.getDate_inscri()));
        preparedStatement.setString(6, conference.getDomaine());
        preparedStatement.setFloat(7, conference.getFrais());
        preparedStatement.setString(8, conference.getLocal());
        preparedStatement.setString(9, conference.getNom_institution());
        preparedStatement.setString(10, conference.getPays());
        preparedStatement.setString(11, conference.getVille());
        preparedStatement.setInt(12, conference.getPresident_id());
        preparedStatement.setString(13, conference.getImage());
        preparedStatement.executeUpdate();
        System.out.println("conference ajouté");
    }

    public void modifierConference(Conference conference, int id) {

        try {
            String query = "UPDATE conference SET titre=? , date_articles=? , date_debut=? , date_fin=? , date_inscri=? , domaine=? , frais=? , local=? , nom_institution=? , pays=? , ville=? , president_id=? , image=?  WHERE id_conference=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, conference.getTitre());
            preparedStatement.setDate(2, Date.valueOf(conference.getDate_articles()));
            preparedStatement.setDate(3, Date.valueOf(conference.getDate_debut()));
            preparedStatement.setDate(4, Date.valueOf(conference.getDate_fin()));
            preparedStatement.setDate(5, Date.valueOf(conference.getDate_inscri()));
            preparedStatement.setString(6, conference.getDomaine());
            preparedStatement.setFloat(7, conference.getFrais());
            preparedStatement.setString(8, conference.getLocal());
            preparedStatement.setString(9, conference.getNom_institution());
            preparedStatement.setString(10, conference.getPays());
            preparedStatement.setString(11, conference.getVille());
            preparedStatement.setInt(12, conference.getPresident_id());
            preparedStatement.setString(13, conference.getImage());
            preparedStatement.setInt(14, id);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Conference updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerConference(int id) {
        try {
            String req = "DELETE FROM `conference` WHERE id_conference = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("conference deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Conference> rechercheConfMembreSc(int id_membreSc) {

        List<Conference> list = new ArrayList<>();
        List<Integer> ids_conf = new ArrayList<>();
        try {
            String req = "Select * from membre_sc_conference where membres_sc_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_membreSc);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ids_conf.add(RS.getInt("conferences_id"));
            }
            for (int i=0; i<ids_conf.size(); i++)
            {
                String req2 = "Select * from conference where id_conference = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_conf.get(i));
                ResultSet RS2 = ps2.executeQuery();
                while (RS2.next()) {
                    Conference conference = new Conference();
                    conference.setId_conference(RS2.getInt("id_conference"));
                    conference.setTitre(RS2.getString("titre"));
                    conference.setDate_articles(RS2.getDate("date_articles").toLocalDate());
                    conference.setDate_debut(RS2.getDate("date_debut").toLocalDate());
                    conference.setDate_fin(RS2.getDate("date_fin").toLocalDate());
                    conference.setDate_inscri(RS2.getDate("date_inscri").toLocalDate());
                    conference.setDomaine(RS2.getString("domaine"));
                    conference.setFrais(RS2.getFloat("frais"));
                    conference.setLocal(RS2.getString("local"));
                    conference.setNom_institution(RS2.getString("nom_institution"));
                    conference.setPays(RS2.getString("pays"));
                    conference.setVille(RS2.getString("ville"));
                    conference.setComite_org_id(RS2.getInt("comite_org_id"));
                    conference.setComite_sc_id(RS2.getInt("comite_sc_id"));
                    conference.setPresident_id(RS2.getInt("president_id"));
                    conference.setImage(RS2.getString("image"));

                    list.add(conference);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public boolean isDateAfterToday(LocalDate datedeb) {
        LocalDate today = LocalDate.now();
        return datedeb.isAfter(today);
    }

    public boolean isDateFinAfterDateDeb(LocalDate datefin, LocalDate datedeb) {
        return datefin.isAfter(datedeb);
    }

    public boolean isDateNowBeforeDateLimite(LocalDate dateLimite) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isBefore(dateLimite);
    }

    public boolean isDateBetween(LocalDate dateToCheck, LocalDate datedeb, LocalDate datefin) {
        return !dateToCheck.isBefore(datedeb) && !dateToCheck.isAfter(datefin);
    }
}

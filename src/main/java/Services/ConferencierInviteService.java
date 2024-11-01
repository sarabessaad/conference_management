package Services;

import Entities.Article;
import Entities.Conference;
import Entities.ConferencierInvite;
import Entities.StatusArticle;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConferencierInviteService {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<ConferencierInvite> GetConferencierInvite(int idConferencier) {
        List<ConferencierInvite> list = new ArrayList<>();
        List<Integer> ids_conferenciers = new ArrayList<>();
        try {
            String req = "SELECT * FROM conference_conferencier_invites WHERE conferences_id_conference = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idConferencier);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ids_conferenciers.add(RS.getInt("conferencier_invites_id_conferencier"));
            }

            for (int i=0; i<ids_conferenciers.size(); i++)
            {
                String req2 = "SELECT * FROM conferencier_invite WHERE id_conferencier = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_conferenciers.get(i));
                ResultSet RS1 = ps2.executeQuery();
                while (RS1.next()) {

                    ConferencierInvite conferencierInvite = new ConferencierInvite();
                    conferencierInvite.setId_conferencier(RS1.getInt("id_conferencier"));
                    conferencierInvite.setEmail(RS1.getString("email"));
                    conferencierInvite.setInstitution(RS1.getString("institution"));
                    conferencierInvite.setNom(RS1.getString("nom"));
                    conferencierInvite.setPays(RS1.getString("pays"));
                    conferencierInvite.setTitre_pres(RS1.getString("titre_pres"));

                    System.out.println(conferencierInvite);
                    list.add(conferencierInvite);

                }
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public void supprimerConferencierInvite(int id) {
        try {
            String req = "DELETE FROM `conferencier_invite` WHERE id_conferencier = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("conferencier invite deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierConferencier(ConferencierInvite conferencierInvite, int id) {

        try {
            String query = "UPDATE conferencier_invite SET email=? , institution=? , nom=? , pays=? , titre_pres=?  WHERE id_conferencier=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, conferencierInvite.getEmail());
            preparedStatement.setString(2, conferencierInvite.getInstitution());
            preparedStatement.setString(3, conferencierInvite.getNom());
            preparedStatement.setString(4, conferencierInvite.getPays());
            preparedStatement.setString(5, conferencierInvite.getTitre_pres());
            preparedStatement.setInt(6, id);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Conferencier updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterConferencier(ConferencierInvite conferencierInvite) throws SQLException {

        String query = "INSERT INTO conferencier_invite (email, institution, nom, pays, titre_pres) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, conferencierInvite.getEmail());
        preparedStatement.setString(2, conferencierInvite.getInstitution());
        preparedStatement.setString(3, conferencierInvite.getNom());
        preparedStatement.setString(4, conferencierInvite.getPays());
        preparedStatement.setString(5, conferencierInvite.getTitre_pres());
        preparedStatement.executeUpdate();
        System.out.println("Conferencier ajouté");
    }

    public void affectConferencierToConference(int id_conferencier, int id_conf) throws SQLException {

        String query = "INSERT INTO conference_conferencier_invites (conferences_id_conference, conferencier_invites_id_conferencier) VALUES (?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setInt(1, id_conf);
        preparedStatement.setInt(2, id_conferencier);
        preparedStatement.executeUpdate();
        System.out.println("Conferencier ajouté");
    }

    public List<ConferencierInvite> afficherConferencier() {

        List<ConferencierInvite> list = new ArrayList<>();
        try {
            String query = "Select * from conferencier_invite";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                ConferencierInvite conferencierInvite = new ConferencierInvite();
                conferencierInvite.setId_conferencier(RS.getInt("id_conferencier"));
                conferencierInvite.setNom(RS.getString("nom"));
                conferencierInvite.setEmail(RS.getString("email"));
                conferencierInvite.setPays(RS.getString("pays"));
                conferencierInvite.setInstitution(RS.getString("institution"));
                conferencierInvite.setTitre_pres(RS.getString("titre_pres"));

                list.add(conferencierInvite);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}

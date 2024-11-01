package Services;

import Entities.MembreOrg;
import Entities.President;
import Entities.User;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterUser(User user) throws SQLException {

        String query = "INSERT INTO user (mail, institution, nom, mdp) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, user.getMail());
        preparedStatement.setString(2, user.getInstitution());
        preparedStatement.setString(3, user.getNom());
        preparedStatement.setString(4, user.getMdp());
        preparedStatement.executeUpdate();
        System.out.println("user ajouté");
    }

    public boolean test_Email(String mail) {
        int test = 0;
        int position = 0;
        int test2 = 0;
        String[] tab = {"/", ";", ",", ":", "'", "&", "=", ">", "-", "_", "+", " ","!"};

        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
                position = i;
            }

        }
        for (int k = 0; k < mail.length(); k++) {

            for (String tab1 : tab) {
                if (mail.charAt(k) == tab1.charAt(0)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {

                if (((mail.length() > i + 2) && (i > position + 4))) {
                    for (int j = position; j < mail.length(); j++) {
                        if (mail.charAt(j) == ".".charAt(0)) {
                            test2++;

                        }
                    }
                    if (test2 > 1) {
                        return false;
                    }

                    return true;
                }

            }

        }
        return false;
    }

    public List<User> rechercheUser(int id) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM user WHERE id_user = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                User user = new User();
                user.setId_user(RS.getInt("id_user"));
                user.setNom(RS.getString("nom"));
                user.setInstitution(RS.getString("institution"));
                user.setMail(RS.getString("mail"));
                user.setMdp(RS.getString("mdp"));

                System.out.println(user);
                list.add(user);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public List<User> rechercheAuteur(String nom, String mail, String institution) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM user WHERE nom = ? AND mail = ? AND institution = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ps.setString(2, mail);
            ps.setString(3, institution);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                User user = new User();
                user.setId_user(RS.getInt("id_user"));
                user.setNom(RS.getString("nom"));
                user.setInstitution(RS.getString("institution"));
                user.setMail(RS.getString("mail"));
                user.setMdp(RS.getString("mdp"));

                System.out.println(user);
                list.add(user);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public void ajouterAuteur(int id_user, int id_article) throws SQLException {

        String query = "INSERT INTO user_articles (users_id_user, articles_id_article) VALUES (?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setInt(1, id_user);
        preparedStatement.setInt(2, id_article);
        preparedStatement.executeUpdate();
        System.out.println("auteur ajouté");
    }

    public List<User> rechercheUserConf(int id_conf) {
        List<User> list = new ArrayList<>();
        List<Integer> ids_users = new ArrayList<>();
        try {
            String req = "SELECT * FROM participation_conference where id_conference = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_conf);
            ResultSet RS = ps.executeQuery();
            while (RS.next())
            {
                ids_users.add(RS.getInt("id_user"));
            }

            for (int i=0; i<ids_users.size(); i++)
            {
                String req2 = "Select * from user where id_user = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_users.get(i));
                ResultSet RS2 = ps2.executeQuery();
                while (RS2.next()) {
                    User user = new User();
                    user.setId_user(RS2.getInt("id_user"));
                    user.setNom(RS2.getString("nom"));
                    user.setMail(RS2.getString("mail"));
                    user.setMdp(RS2.getString("mdp"));
                    user.setInstitution(RS2.getString("institution"));

                    System.out.println(user);
                    list.add(user);
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

}

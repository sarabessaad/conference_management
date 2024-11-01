package Services;

import Entities.President;
import Entities.User;
import Utils.DataSource;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PresidentService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterPresident(President president) throws SQLException {

        String query = "INSERT INTO president (email, institution, nom, mdp) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, president.getEmail());
        preparedStatement.setString(2, president.getInstitution());
        preparedStatement.setString(3, president.getNom());
        preparedStatement.setString(4, president.getMdp());
        preparedStatement.executeUpdate();
        System.out.println("president ajouté");
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

    public List<President> recherchePres(int id) {
        List<President> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM president WHERE id_president = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                President president = new President();
                president.setId_president(RS.getInt("id_president"));
                president.setNom(RS.getString("nom"));
                president.setInstitution(RS.getString("institution"));
                president.setEmail(RS.getString("email"));
                president.setMdp(RS.getString("mdp"));

                System.out.println(president);
                list.add(president);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }
}

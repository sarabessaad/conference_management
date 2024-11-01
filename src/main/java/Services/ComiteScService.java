package Services;

import Entities.Conference;
import Entities.MembreOrg;
import Entities.MembreSc;
import Entities.President;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComiteScService {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<MembreSc> getMembresSc(int id_conf) {
        List<MembreSc> list = new ArrayList<>();
        List<Integer> ids_membresSc = new ArrayList<>();
        try {
            String req = "SELECT * FROM membre_sc_conference where conferences_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_conf);
            ResultSet RS = ps.executeQuery();
            while (RS.next())
            {
                ids_membresSc.add(RS.getInt("membres_sc_id"));
            }

            for (int i=0; i<ids_membresSc.size(); i++)
            {
                String req2 = "Select * from membre_sc where id_membre_sc = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_membresSc.get(i));
                ResultSet RS2 = ps2.executeQuery();
                while (RS2.next()) {
                    MembreSc membreSc = new MembreSc();
                    membreSc.setId_comite_sc(RS2.getInt("id_membre_sc"));
                    membreSc.setNom(RS2.getString("nom"));
                    membreSc.setEmail(RS2.getString("email"));
                    membreSc.setInstitution(RS2.getString("institution"));

                    System.out.println(membreSc);
                    list.add(membreSc);
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public List<MembreSc> afficherMembresSc() {

        List<MembreSc> list = new ArrayList<>();
        try {
            String query = "Select * from membre_sc";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                MembreSc membreSc = new MembreSc();
                membreSc.setId_membreSc(RS.getInt("id_membre_sc"));
                membreSc.setNom(RS.getString("nom"));
                membreSc.setEmail(RS.getString("email"));
                membreSc.setInstitution(RS.getString("institution"));

                System.out.println(membreSc);
                list.add(membreSc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void affectMembreScToConference(List<MembreSc> membreScs, int id_conference) throws SQLException {

        for (int i=0; i<membreScs.size(); i++)
        {
            String query = "INSERT INTO membre_sc_conference (membres_sc_id, conferences_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, membreScs.get(i).getId_membreSc());
            preparedStatement.setInt(2, id_conference);
            preparedStatement.executeUpdate();
            System.out.println("membresSc affecté");
        }

    }

    public List<MembreSc> rechercheMembreSc(int id) {
        List<MembreSc> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM membre_sc WHERE id_membre_sc = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                MembreSc membreSc = new MembreSc();
                membreSc.setId_membreSc(RS.getInt("id_membre_sc"));
                membreSc.setEmail(RS.getString("email"));
                membreSc.setInstitution(RS.getString("institution"));
                membreSc.setNom(RS.getString("nom"));
                membreSc.setMdp(RS.getString("mdp"));

                System.out.println(membreSc);
                list.add(membreSc);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }
}

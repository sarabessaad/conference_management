package Services;

import Entities.*;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComiteOrgService {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<MembreOrg> getMembresOrg(int id_conf) {
        List<MembreOrg> list = new ArrayList<>();
        List<Integer> ids_membresOrg = new ArrayList<>();
        try {
            String req = "SELECT * FROM membre_org_conference where conference_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_conf);
            ResultSet RS = ps.executeQuery();
            while (RS.next())
            {
                ids_membresOrg.add(RS.getInt("membre_org_id"));
            }

            for (int i=0; i<ids_membresOrg.size(); i++)
            {
                String req2 = "Select * from membre_org where id_membre_org = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_membresOrg.get(i));
                ResultSet RS2 = ps2.executeQuery();
                while (RS2.next()) {
                    MembreOrg membreOrg = new MembreOrg();
                    membreOrg.setId_membreOrg(RS2.getInt("id_membre_org"));
                    membreOrg.setNom(RS2.getString("nom"));
                    membreOrg.setEmail(RS2.getString("email"));
                    membreOrg.setInstitution(RS2.getString("institution"));

                    System.out.println(membreOrg);
                    list.add(membreOrg);
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public List<MembreOrg> afficherMembresOrg() {

        List<MembreOrg> list = new ArrayList<>();
        try {
            String query = "Select * from membre_org";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                MembreOrg membreOrg = new MembreOrg();
                membreOrg.setId_membreOrg(RS.getInt("id_membre_org"));
                membreOrg.setNom(RS.getString("nom"));
                membreOrg.setEmail(RS.getString("email"));
                membreOrg.setInstitution(RS.getString("institution"));

                System.out.println(membreOrg);
                list.add(membreOrg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void affectMembreOrgToConference(List<MembreOrg> membreOrgs, int id_conference) throws SQLException {

        for (int i=0; i<membreOrgs.size(); i++)
        {
            String query = "INSERT INTO membre_org_conference (membre_org_id, conference_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, membreOrgs.get(i).getId_membreOrg());
            preparedStatement.setInt(2, id_conference);
            preparedStatement.executeUpdate();
            System.out.println("membresOrg affecté");
        }

    }
}

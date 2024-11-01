package Services;

import Entities.Participation;
import Entities.User;
import Utils.DataSource;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParticipationConfService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterParticipation(Participation participation) throws SQLException {
        String query = "INSERT INTO participation_conference (id_user, id_conference, mode_paiement) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setInt(1, participation.getId_user());
        preparedStatement.setInt(2, participation.getId_conference());
        preparedStatement.setString(3, participation.getMode_paiement());
        preparedStatement.executeUpdate();
        System.out.println("participation ajouté");
    }
}

package Services;

import Entities.*;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterArticle(Article article) throws SQLException {

        String query = "INSERT INTO article (emplacement_pdf, nom_pdf, status, titre, conference_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, article.getEmplacement_pdf());
        preparedStatement.setString(2, article.getNom_pdf());
        preparedStatement.setString(3, article.getStatus().toString());
        preparedStatement.setString(4, article.getTitre());
        preparedStatement.setInt(5, article.getConference_id());
        preparedStatement.executeUpdate();
        System.out.println("article ajouté");
    }

    public List<Article> afficherArticle() {

        List<Article> list = new ArrayList<>();
        try {
            String query = "Select * from article";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                Article article = new Article();
                article.setId_article(RS.getInt("id_article"));
                article.setEmplacement_pdf(RS.getString("emplacement_pdf"));
                article.setNom_pdf(RS.getString("nom_pdf"));
                article.setStatus(StatusArticle.valueOf(RS.getString("status")));
                article.setTitre(RS.getString("titre"));
                article.setConference_id(RS.getInt("conference_id"));
                article.setMembre_sc_id(RS.getInt("membre_sc_id"));

                list.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Article> GetArticlesAuteur(int id_user) {

        List<Article> list = new ArrayList<>();
        List<Integer> ids_articles = new ArrayList<>();
        try {
            String req = "Select * from user_articles where users_id_user = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_user);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ids_articles.add(RS.getInt("articles_id_article"));
            }
            for (int i=0; i<ids_articles.size(); i++)
            {
                String req2 = "Select * from article where id_article = ?";
                PreparedStatement ps2 = cnx.prepareStatement(req2);
                ps2.setInt(1, ids_articles.get(i));
                ResultSet RS2 = ps2.executeQuery();
                while (RS2.next()) {
                    Article article = new Article();
                    article.setId_article(RS2.getInt("id_article"));
                    article.setEmplacement_pdf(RS2.getString("emplacement_pdf"));
                    article.setNom_pdf(RS2.getString("nom_pdf"));
                    article.setStatus(StatusArticle.valueOf(RS2.getString("status")));
                    article.setTitre(RS2.getString("titre"));
                    article.setConference_id(RS2.getInt("conference_id"));
                    article.setMembre_sc_id(RS2.getInt("membre_sc_id"));

                    list.add(article);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Article> afficherArticleConf(int id_conf) {

        List<Article> list = new ArrayList<>();
        try {
            String query = "Select * from article where conference_id ="+ id_conf;
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                Article article = new Article();
                article.setId_article(RS.getInt("id_article"));
                article.setEmplacement_pdf(RS.getString("emplacement_pdf"));
                article.setNom_pdf(RS.getString("nom_pdf"));
                article.setStatus(StatusArticle.valueOf(RS.getString("status")));
                article.setTitre(RS.getString("titre"));
                article.setConference_id(RS.getInt("conference_id"));
                article.setMembre_sc_id(RS.getInt("membre_sc_id"));

                list.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void affectArticleToMembreSc(Article article, int id) {

        try {
            String query = "UPDATE article SET emplacement_pdf=? , nom_pdf=? , status=? , titre=? , conference_id=? , membre_sc_id=?  WHERE id_article=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, article.getEmplacement_pdf());
            preparedStatement.setString(2, article.getNom_pdf());
            preparedStatement.setString(3, StatusArticle.UE.toString());
            preparedStatement.setString(4, article.getTitre());
            preparedStatement.setInt(5, article.getConference_id());
            preparedStatement.setInt(6, article.getMembre_sc_id());
            preparedStatement.setInt(7, id);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("article affected!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> GetAuteursArticle(int id_article) {

        List<User> list = new ArrayList<>();
        List<Integer> ids_users = new ArrayList<>();
        try {
            String req = "Select * from user_articles where articles_id_article = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_article);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ids_users.add(RS.getInt("users_id_user"));
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
                    user.setInstitution(RS2.getString("institution"));
                    user.setMdp(RS2.getString("mdp"));

                    list.add(user);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Article> afficherArticleAcc() {

        List<Article> list = new ArrayList<>();
        try {
            String query = "Select * from article where status = 'ACC'";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                Article article = new Article();
                article.setId_article(RS.getInt("id_article"));
                article.setEmplacement_pdf(RS.getString("emplacement_pdf"));
                article.setNom_pdf(RS.getString("nom_pdf"));
                article.setStatus(StatusArticle.valueOf(RS.getString("status")));
                article.setTitre(RS.getString("titre"));
                article.setConference_id(RS.getInt("conference_id"));
                article.setMembre_sc_id(RS.getInt("membre_sc_id"));

                list.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void modifierArticle(Article article, int id) {

        try {
            String query = "UPDATE article SET emplacement_pdf=? , nom_pdf=? , status=? , titre=? , conference_id=?, membre_sc_id=?  WHERE id_article=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, article.getEmplacement_pdf());
            preparedStatement.setString(2, article.getNom_pdf());
            preparedStatement.setString(3, article.getStatus().toString());
            preparedStatement.setString(4, article.getTitre());
            preparedStatement.setInt(5, article.getConference_id());
            preparedStatement.setInt(6, article.getMembre_sc_id());
            preparedStatement.setInt(7, id);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Conferencier updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

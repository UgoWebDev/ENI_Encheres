package fr.eni.enchere.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
    public static final String SELECT_BY_MAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur FROM UTILISATEURS WHERE email = ?";
    public static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur FROM UTILISATEURS WHERE pseudo = ?";


    private Utilisateur getUtilisateurByLogin(String login,String requete) {
        Utilisateur user = null;
        try (Connection cnx = ConnectionProvider.getConnection();
                PreparedStatement pstmt = cnx.prepareStatement(requete)) {
            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
                            rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
                            rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
                            rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

	@Override
	public Utilisateur getUtilisateurByPseudo(String login) {
        return getUtilisateurByLogin(login, SELECT_BY_PSEUDO);
    }

	@Override
	public Utilisateur getUtilisateurByMail(String login) {
		 return getUtilisateurByLogin(login, SELECT_BY_MAIL);
	}
}
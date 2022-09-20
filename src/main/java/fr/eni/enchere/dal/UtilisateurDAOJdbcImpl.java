package fr.eni.enchere.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	public static final String SELECT_BY_MAIL    	= "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,rue ,code_postal ,ville ,mot_de_passe ,credit ,administrateur "
			+ "FROM UTILISATEURS u, ADRESSES a where u.no_adresse = a.no_adresse AND email = ?";
	public static final String SELECT_BY_PSEUDO 	= "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,rue ,code_postal ,ville ,mot_de_passe ,credit ,administrateur "
			+ "FROM UTILISATEURS u, ADRESSES a where u.no_adresse = a.no_adresse AND pseudo = ?";
	public static final String INSERT_ADRESSE 		= "INSERT INTO ADRESSES (rue,code_postal,ville) VALUES (?,?,?)";
	public static final String INSERT_UTILISATEUR 	= "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,no_adresse,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?)";


	private Utilisateur getUtilisateurByLogin(String login,String requete) {
		Utilisateur user = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(requete)
				) {
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
			System.out.println("erreur de connection ligne 34 UtilisateurDAOJdbcImpl");
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

	@Override
	public Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException {
		if (user == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(Cod);
		}
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(requete)
				) {
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
}
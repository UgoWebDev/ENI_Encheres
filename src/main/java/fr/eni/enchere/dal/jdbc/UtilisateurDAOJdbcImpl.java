package fr.eni.enchere.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.AdresseManager;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	public static final String SELECT_BY_MAIL    	= "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,no_adresse ,mot_de_passe ,credit ,administrateur "
			+ "FROM UTILISATEURS u, ADRESSES a where u.no_adresse = a.no_adresse AND email = ?";
	public static final String SELECT_BY_PSEUDO 	= "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,no_adresse ,mot_de_passe ,credit ,administrateur "
			+ "FROM UTILISATEURS u, ADRESSES a where u.no_adresse = a.no_adresse AND pseudo = ?";
	public static final String SELECT_BY_NO    	= "SELECT no_utilisateur ,pseudo ,nom ,prenom ,email ,telephone ,no_adresse ,mot_de_passe ,credit ,administrateur "
			+ "FROM UTILISATEURS u, ADRESSES a where u.no_adresse = a.no_adresse AND no_utilisateur = ?";
	public static final String INSERT_ADRESSE 		= "INSERT INTO ADRESSES (rue,code_postal,ville) VALUES (?,?,?)";
	public static final String INSERT_UTILISATEUR 	= "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,no_adresse,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?)";
	


	private Utilisateur getUtilisateurByLogin(String login,String requete,String type) throws BusinessException{
		Utilisateur user = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(requete)
				) {
			if (type == "S") {
				pstmt.setString(1, login);
			}
			if (type == "I") {
				pstmt.setInt(1, Integer.parseInt(login));
			}
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new Utilisateur(
							rs.getInt("no_utilisateur"), 
							rs.getString("pseudo"), 
							rs.getString("nom"),
							rs.getString("prenom"), 
							rs.getString("email"), 
							rs.getString("telephone"),
							AdresseManager.getInstance().getAdresseByNo(rs.getInt("no_adresse")),
							rs.getString("mot_de_passe"), 
							rs.getInt("credit"), 
							rs.getBoolean("administrateur"),null,null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Utilisateur getUtilisateurByPseudo(String login) throws BusinessException{
		return getUtilisateurByLogin(login, SELECT_BY_PSEUDO,"S");
	}

	@Override
	public Utilisateur getUtilisateurByMail(String login) throws BusinessException{
		return getUtilisateurByLogin(login, SELECT_BY_MAIL,"S");
	}

	@Override
	public Utilisateur getUtilisateurByNo(Integer noUtilisateur) throws BusinessException {
		String login = String.valueOf(noUtilisateur);
		
		return getUtilisateurByLogin(login, SELECT_BY_NO,"I");
	}

	@Override
	public Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException {
		if (user == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			int idAdresse=0;
			try 			// enregistrement de l'adresse
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if (user.getNoUtilisateur()==null) 
				{
					pstmt = cnx.prepareStatement(INSERT_ADRESSE,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, user.getAdresse().getRue());
					pstmt.setString(2, user.getAdresse().getCodePostal());
					pstmt.setString(3, user.getAdresse().getVille());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						idAdresse = rs.getInt(1);
					}
					rs.close();
					pstmt.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
			try 
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if (idAdresse != 0) 
				{
					pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, user.getPseudo());
					pstmt.setString(2, user.getNom());
					pstmt.setString(3, user.getPrenom());
					pstmt.setString(4, user.getEmail());
					pstmt.setString(5, user.getTelephone());
					pstmt.setInt(6, idAdresse);
					pstmt.setString(7, user.getMotDePasse());
					pstmt.setInt(8, user.getCredit());
					pstmt.setBoolean(9, user.isAdministrateur());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						user.setNoUtilisateur(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
					cnx.commit();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
		return user;
	}
}
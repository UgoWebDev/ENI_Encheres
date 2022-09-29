package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.dal.AdresseDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;

public class AdresseDAOJdbcImpl implements AdresseDAO {

	public static final String SELECT_ADRESSE_BY_ID    	= "SELECT no_adresse ,rue, code_postal, ville FROM ADRESSES where no_adresse = ?";
	public static final String INSERT_ADRESSE			= "INSERT INTO ADRESSES (rue, code_postal, ville) VALUES (?,?,?)";
	public static final String UPDATE_ADRESSE 			= "UPDATE ADRESSES SET rue = ?,code_postal = ?,ville = ? WHERE no_adresse = ?";

	@Override
	public Adresse getAdresseByNo(Integer noAdresse) {
		Adresse adresse = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ADRESSE_BY_ID)
				) {
			pstmt.setInt(1, noAdresse);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					adresse = new Adresse(
							rs.getInt("no_adresse"), 
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public Adresse insertAdresse(Adresse adresse) throws BusinessException {

		if (adresse == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			try 			// enregistrement de la catégorie
			{
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(INSERT_ADRESSE,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, adresse.getRue());
				pstmt.setString(2, adresse.getCodePostal());
				pstmt.setString(3, adresse.getVille());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					adresse.setNoAdresse(rs.getInt(1)); 
				}
				rs.close();
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
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

		return adresse;
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) throws BusinessException {

		if (adresse == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.MODIF_ADRESSE_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			try 			// enregistrement de la catégorie
			{
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(UPDATE_ADRESSE);
				pstmt.setString(1, adresse.getRue());
				pstmt.setString(2, adresse.getCodePostal());
				pstmt.setString(3, adresse.getVille());
				pstmt.setInt(4, adresse.getNoAdresse());
				pstmt.executeUpdate();
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.MODIF_ADRESSE_ECHEC);
				throw be;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.MODIF_ADRESSE_ECHEC);
			throw businessException;
		}

		return adresse;
	}
}



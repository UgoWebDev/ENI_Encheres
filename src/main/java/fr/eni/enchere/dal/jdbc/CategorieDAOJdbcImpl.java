package fr.eni.enchere.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	public static final String SELECT_CATEGORIE_BY_LIBELLE    	= "SELECT no_categorie ,libelle FROM CATEGORIES where libelle = ?";
	public static final String SELECT_ALL_CATEGORIES    	= "SELECT no_categorie ,libelle FROM CATEGORIES";
	public static final String INSERT_CATEGORIE 	= "INSERT INTO CATEGORIES (libelle) VALUES (?)";
	public static final String DELETE_CATEGORIE 	= "DELETE FROM CATEGORIES WHERE libelle = ?";

	@Override
	public Categorie getCategorie(String libelle) {
		Categorie categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_LIBELLE)
				) {
			pstmt.setString(1, libelle);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"), null);

					// il reste à ajouter le tableau d'articles de la catégorie quand ArticleManager sera prêt
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}


	@Override
	public List<Categorie> getCategories() {
		ArrayList<Categorie> categories = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES)
				) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"), null));

					// il reste à ajouter le tableau d'articles de la catégorie quand ArticleManager sera prêt
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}


	@Override
	public Categorie insertCategorie(String libelle) throws BusinessException {
		Categorie categorie = null;
		
		if (libelle == null || libelle == "") {
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
				pstmt = cnx.prepareStatement(INSERT_CATEGORIE,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, libelle);
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					categorie = new Categorie(rs.getInt(1), libelle, null);
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

		return categorie;
	}


	@Override
	public void deleteCategorie(String libelle) {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE)
				) {
			pstmt.setString(1, libelle);
			try (ResultSet rs = pstmt.executeQuery()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
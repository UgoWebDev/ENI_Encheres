package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	public static final String SELECT_ENCHERE_BY_NO = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_enchere = ?";
	public static final String SELECT_ENCHERES_BY_UTILISATEUR 	= "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur  FROM ENCHERES WHERE no_utilisateur = ?";
	public static final String SELECT_ENCHERES_BY_ARTICLE 	= "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur  FROM ENCHERES WHERE no_article = ?";
	public static final String SELECT_ENCHERES_BY_UTILISATEUR_BY_CONTENT 	= "SELECT no_enchere, date_enchere, montant_enchere, e.no_article, e.no_utilisateur  FROM ENCHERES e, ARTICLES a WHERE"
			+ "e.no_article=a.no_article AND e.no_utilisateur = ? AND a.nom_article LIKE ?";

	@Override
	public Enchere insertEnchere(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			try 			// enregistrement de l'enchére
			{
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(INSERT_ENCHERE,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setDate(1, (Date) enchere.getDateEnchere());
				pstmt.setInt(2, enchere.getMontantEnchere());
				pstmt.setInt(3, enchere.getArticleEnchere().getNoArticle());
				pstmt.setInt(4, enchere.getUtilisateurEnchere().getNoUtilisateur());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					enchere.setNoEnchere(rs.getInt(1)); 
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

		return enchere;
	}

	@Override
	public Enchere getEnchereByNo(Integer noEnchere) throws BusinessException {
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_NO)
				) {
			pstmt.setInt(1, noEnchere);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					enchere = new Enchere(
						rs.getInt("no_enchere"), 
						rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"),
						UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
						ArticleManager.getInstance().getArticleByNo(rs.getInt("no_article"))
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public List<Enchere> getEncheresByUtilisateur(Utilisateur utilisateur) throws BusinessException{
		ArrayList<Enchere> encheres = null; 
		  
		  try
		  (Connection cnx = ConnectionProvider.getConnection(); 
				  PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_BY_UTILISATEUR) 
				) {
			  pstmt.setInt(1, utilisateur.getNoUtilisateur());
			  try (ResultSet rs = pstmt.executeQuery()) {
				  if (rs.next()){ encheres.add(new Enchere(
							rs.getInt("no_enchere"), 
							rs.getDate("date_enchere"),
							rs.getInt("montant_enchere"),
							UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
							ArticleManager.getInstance().getArticleByNo(rs.getInt("no_article"))
					 ));
				  } 
				} 
		  } catch (SQLException e) { 
			  e.printStackTrace();
		  } 
		  	return encheres;
	}

	@Override
	public List<Enchere> getEncheresByArticle(Article article) throws BusinessException{
		ArrayList<Enchere> encheres = null; 
		  
		  try
		  (Connection cnx = ConnectionProvider.getConnection(); 
				  PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_BY_ARTICLE) 
				) {
			  pstmt.setInt(1, article.getNoArticle());
			  try (ResultSet rs = pstmt.executeQuery()) {
				  if (rs.next()){ encheres.add(new Enchere(
							rs.getInt("no_enchere"), 
							rs.getDate("date_enchere"),
							rs.getInt("montant_enchere"),
							UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
							ArticleManager.getInstance().getArticleByNo(rs.getInt("no_article"))
					 ));
				  } 
				} 
		  } catch (SQLException e) { 
			  e.printStackTrace();
		  } 
		  	return encheres;
	}

	@Override
	public List<Enchere> getEncheresByUtilisateurByContent(Utilisateur utilisateur, String contient)
			throws BusinessException {
		ArrayList<Enchere> encheres = null; 
		  
		  try
		  (Connection cnx = ConnectionProvider.getConnection(); 
				  PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_BY_UTILISATEUR_BY_CONTENT) 
				) {
			  pstmt.setInt(1, utilisateur.getNoUtilisateur());
			  pstmt.setString(2, "%"+contient+"%");
			  try (ResultSet rs = pstmt.executeQuery()) {
				  if (rs.next()){ encheres.add(new Enchere(
							rs.getInt("no_enchere"), 
							rs.getDate("date_enchere"),
							rs.getInt("montant_enchere"),
							UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
							ArticleManager.getInstance().getArticleByNo(rs.getInt("no_article"))
					 ));
				  } 
				} 
		  } catch (SQLException e) { 
			  e.printStackTrace();
		  } 
		  	return encheres;
	}
	
	

}

package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.Article.EtatsVente;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;


public class ArticleDAOJdbcImpl implements ArticleDAO {

	
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie, no_adresse) VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_ADRESSE = "INSERT INTO ADRESSES (rue,code_postal,ville) VALUES (?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE no_article = ?";
	public static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie, no_adresse FROM ARTICLES  ";
	public static final String SELECT_ARTICLE_BY_NO 	= "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie, no_adresse FROM ARTICLES WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ?, no_adresse = ? WHERE no_article = ?";

	
	@Override
	public Article insertArticle(Article article) throws BusinessException {
		BusinessException be = new BusinessException();
		if (article == null) {
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			try 			// enregistrement de l'adresse
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if (!(article.getNomArticle()==null || article.getNomArticle() == "") )
				{
					System.out.println(getClass() + " : Entrée dans l'enregistrement de l'adresse");
					pstmt = cnx.prepareStatement(INSERT_ADRESSE,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getRetrait().getRue());
					pstmt.setString(2, article.getRetrait().getCodePostal());
					pstmt.setString(3, article.getRetrait().getVille());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						article.getRetrait().setNoAdresse(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ADRESSE);
				throw be;
			}
			
			try 
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if (article.getRetrait().getNoAdresse() != 0) 
				{
					pstmt = cnx.prepareStatement(INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getNomArticle());
					pstmt.setString(2, article.getDescription());
					pstmt.setDate(3, convertJavaDateToSqlDate(article.getDateDebutEncheres()) );
					pstmt.setDate(4, convertJavaDateToSqlDate(article.getDateFinEncheres()));
					pstmt.setInt(5, article.getMiseAPrix());
					pstmt.setInt(6, 0);
					pstmt.setInt(7, article.getEtatVente().ordinal());
					pstmt.setInt(8, article.getVendeur().getNoUtilisateur());
					pstmt.setInt(9, article.getCategorie().getNoCategorie());
					pstmt.setInt(10, article.getRetrait().getNoAdresse());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						article.setNoArticle(rs.getInt(1));
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
				be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE);
				throw be;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE);
			throw be;
		}
		
		return article;
	}

	@Override
	public Article getArticleByNo(Integer noArticle) throws BusinessException  {
		BusinessException be = new BusinessException();
		Article article = null;
		try (
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_NO)
				) {
			pstmt.setInt(1, noArticle);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					article = new Article (
							rs.getInt("no_article"),
							rs.getString("nom_article"), 
							rs.getString("description"),
							convertSQLDateToJAVADate(rs.getDate("date_debut_encheres")) , 
							convertSQLDateToJAVADate(rs.getDate("date_fin_encheres")) , 
							rs.getInt("prix_initial"), 
							rs.getInt("prix_vente"),
							EtatsVente.values()[rs.getInt("etat_vente")], 
							UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
							CategorieManager.getInstance().getCategorieByNo(rs.getInt("no_categorie")), 
							null,
							AdresseManager.getInstance().getAdresseByNo(rs.getInt("no_adresse"))
							);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_BY_NO);
				throw be;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_BY_NO);
			throw be;
		}
		return article;
	}

	@Override

	public List<Article> getArticles(Categorie categorie, Utilisateur utilisateur, String recherche)  throws BusinessException{
		BusinessException be = new BusinessException();
		ArrayList<Article> articles = new ArrayList<>(); 
		String whereString[];

		whereString = construitWhere(categorie, utilisateur, recherche);
		String selectAllArticles = SELECT_ALL_ARTICLES + whereString[0];

		try (
				Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement pstmt = cnx.prepareStatement(selectAllArticles)
				) {
			int i = 1;
			if (whereString[1] == "CT") {
				pstmt.setInt(1, categorie.getNoCategorie());
				i++;
			}
			if (whereString[2] == "UT") {
				pstmt.setInt(i, utilisateur.getNoUtilisateur());
				i++;
			}
			if (whereString[3] == "RT") {
				pstmt.setString(i, "%"+recherche+"%");
				i++;
			}
			if (i == 1) {
				pstmt.setInt(i, 0);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()){ 

					articles.add(new Article(
							rs.getInt("no_article"),
							rs.getString("nom_article"), 
							rs.getString("description"),
							convertSQLDateToJAVADate(rs.getDate("date_debut_encheres")) , 
							convertSQLDateToJAVADate(rs.getDate("date_fin_encheres")) , 
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							EtatsVente.values()[rs.getInt("etat_vente")], 
							UtilisateurManager.getInstance().getUtilisateurByNo(rs.getInt("no_utilisateur")),
							CategorieManager.getInstance().getCategorieByNo(rs.getInt("no_categorie")), 
							null,
							AdresseManager.getInstance().getAdresseByNo(rs.getInt("no_adresse"))
							));
				} 
			} 
		} catch (SQLException e) { 
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.SELECT_ARTICLES);
			throw be;
		} 
		return articles;
	}

	
	private String[] construitWhere(Categorie categorie, Utilisateur utilisateur, String recherche) {
		boolean existAvant  = false;
		String[] whereTab = new String[] {" WHERE no_article != ?","CF","UF","RF"};

		if (!(categorie==null && utilisateur==null && (recherche == null || recherche == ""))) {
			whereTab[0] = " WHERE ";
			if (categorie!=null) {
				whereTab[0] += " no_categorie = ? ";
				existAvant = true;
				whereTab[1] = "CT";
			}  	
			if (utilisateur != null) {
				if (existAvant) {
					whereTab[0] += " AND ";
				}
				whereTab[0] += " no_utilisateur = ? ";
				existAvant = true;
				whereTab[2] = "UT";
			}
			if (recherche != null && recherche !="") {
				if (existAvant) {
					whereTab[0] += " AND ";
				}
				whereTab[0] += " nom_article LIKE ? ";
				whereTab[3] = "RT";
			}
		}
		return whereTab;
	}

	@Override
	public void deleteArticle(Article article) throws BusinessException{
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE)
				) {
			pstmt.setInt(1, article.getNoArticle() );
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE);
			throw be;
		}
	}
	
	@Override
	public Article updateArticle(Article article) throws BusinessException {
		BusinessException be = new BusinessException();
		if (article == null) {
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw be;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			try 
			{
				PreparedStatement pstmt;
				
				pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, convertJavaDateToSqlDate(article.getDateDebutEncheres()) );
				pstmt.setDate(4, convertJavaDateToSqlDate(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getCategorie().getNoCategorie());
				pstmt.setInt(7, article.getRetrait().getNoAdresse());
				pstmt.setInt(8, article.getNoArticle());

				pstmt.executeUpdate();

				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE);
				throw be;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE);
			throw be;
		}

		return article;
	}
	
	private static java.util.Date convertSQLDateToJAVADate(java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }
	
	private java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	}

	
}
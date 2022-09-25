package fr.eni.enchere.dal.jdbc;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.ConnectionProvider;


public class ArticleDAOJdbcImpl implements ArticleDAO {

	
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse) VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_ADRESSE = "INSERT INTO ADRESSES (rue,code_postal,ville) VALUES (?,?,?)";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE no_article = ?";
	public static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse FROM ?";
	public static final String SELECT_BY_NO_ARTICLE 	= "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse WHERE no_article = ?";
	
	@Override
	public Article insertArticle(Article article) throws BusinessException {
		if (article == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
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
				if (article.getNomArticle()==null || article.getNomArticle() == "") 
				{
					pstmt = cnx.prepareStatement(INSERT_ADRESSE,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getRetrait().getRueRetrait());
					pstmt.setString(2, article.getRetrait().getCodePostalRetrait());
					pstmt.setString(3, article.getRetrait().getVilleRetrait());
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
					pstmt = cnx.prepareStatement(INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getNomArticle());
					pstmt.setString(2, article.getDescription());
					pstmt.setDate(3, (Date) article.getDateDebutEncheres());
					pstmt.setDate(4, (Date) article.getDateFinEncheres());
					pstmt.setInt(5, article.getMiseAPrix());
					pstmt.setInt(6, 0);
					pstmt.setInt(7, article.getVendeur().getNoUtilisateur());
					pstmt.setInt(8, article.getCategorie().getNoCategorie());
					pstmt.setInt(9, idAdresse);
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						article.setNoArticle(rs.getInt(1));
						article.setRetrait();
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
		
		return article;
	}


	@Override
	public Article getArticleByNoArticle(String nomArticle) {
		return getArticleByNoArticle(nomArticle, SELECT_BY_NO_ARTICLE);
	}

	@Override
	public List<Article> getArticles() {
		ArrayList<Article> articles = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				Statement pstmt = cnx.createStatement()
				) {
			
			try (ResultSet rs = pstmt.executeQuery(SELECT_ALL_ARTICLES)) {
				if (rs.next()) {
					articles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getEtatsVente("etat_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_adresse")));

					// il reste à ajouter le tableau d'articles de la catégorie quand ArticleManager sera prêt
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	@Override
	public void deleteArticle(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE)
				) {
			pstmt.setInt(1, noArticle);
			try (ResultSet rs = pstmt.executeQuery()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
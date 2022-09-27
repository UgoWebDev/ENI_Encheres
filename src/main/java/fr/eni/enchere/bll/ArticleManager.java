package fr.eni.enchere.bll;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;


public class ArticleManager {
	
	private static ArticleManager instance;
	private ArticleDAO articleDAO;
	Date dateDebut = null;
	Date dateFin = null;

	public Article insertArticle(String nomArticle, String description, String categorie, String image,
			String miseAPrix, String dateDebutEncheres, String dateFinEncheres, String rue, String codePostal,
			String ville) throws BusinessException{
		Article article = null;
		BusinessException be = new BusinessException();
		
		if(isValid(dateDebutEncheres)){
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			try {
				dateDebut = formatter.parse(dateDebutEncheres);
			    System.out.println("Date début : " + dateDebut);
			    System.out.println("Date début après formatter : " + formatter.format(dateDebut));

			} catch (ParseException e) {
			    e.printStackTrace();
			}
		}else {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE_DEBUT);
		}
		
		if(isValid(dateFinEncheres)){
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			try {
				dateFin = formatter.parse(dateDebutEncheres);
			    System.out.println("Date fin : " + dateFin);
			    System.out.println("Date fin après formatter : " + formatter.format(dateFin));

			} catch (ParseException e) {
			    e.printStackTrace();
			}
		}else {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE_FIN);
		}
					

		this.compareDate(dateDebut, dateFin, be);
		
		if(!be.hasErreurs())
		{
			article = articleDAO.insertArticle(article);
			System.out.println("insertArticle OK");	
		}
		else
		{
			throw be;
		}
		
		return article;
	}

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public Article getArticleByNo(Integer noArticle)  {
		return articleDAO.getArticleByNo(noArticle);
	}
	
	public List<Article> getArticles(String categorie, String utilisateur)  {
		
		return articleDAO.getArticles();
	}
	
	public void deleteArticle(Integer noArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		this.notExists(noArticle, be);
		if(!be.hasErreurs())
		{
			articleDAO.deleteArticle(noArticle);
			System.out.println("deleteArticle OK");
		}
		else
		{
			throw be;
		}
	}
	
	
	

	/**
	
	 * @throws BusinessException
	 * 
	 * Vérifie l'exactitude des dates
	 */
	private boolean isValid(String dateString) {
			System.out.println("Date isValid : " + dateString);
	        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	        sdf.setLenient(false);
	        try {
	            sdf.parse(dateString);
	        } catch (ParseException e) {
	        	return false;	        
	        }
	    return true;    
	}
	
	private void compareDate(Date dateDebut, Date dateFin, BusinessException be) throws BusinessException {
		
		int dateCompare = dateDebut.compareTo(dateFin);
		
		System.out.println("Date début : " + dateDebut);
		System.out.println("Date de fin : " + dateFin);
		System.out.println("Date comparé : " + dateCompare);
	
		  if (dateCompare < 1) {
		  be.ajouterErreur(CodesResultatBLL.ARTICLE_DATE_DEBUT_SUPERIEUR_DATE_FIN); 
		  }
		 
	}
	
	private void notExists(Integer noArticle, BusinessException be) {
		if(articleDAO.getArticleByNo(noArticle) == null) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_NON_EXISTANT);
		}
	}
	
}

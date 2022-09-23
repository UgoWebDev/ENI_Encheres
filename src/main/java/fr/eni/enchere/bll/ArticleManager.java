package fr.eni.enchere.bll;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				dateDebut = formatter.parse(dateDebutEncheres);
			    System.out.println(dateDebut);
			    System.out.println(formatter.format(dateDebut));

			} catch (ParseException e) {
			    e.printStackTrace();
			}
		}else {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE_DEBUT);
		}
		
		if(isValid(dateFinEncheres)){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				dateFin = formatter.parse(dateDebutEncheres);
			    System.out.println(dateFin);
			    System.out.println(formatter.format(dateFin));

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
	
	public Article getArticle(String noArticle) throws BusinessException {
		return articleDAO.getArticleByNoArticle(noArticle);
	}
	
	/**
	
	 * @throws BusinessException
	 * 
	 * Vérifie l'exactitude des dates
	 */
	private boolean isValid(String dateString) {
	        DateFormat sdf = new SimpleDateFormat();
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
	
}

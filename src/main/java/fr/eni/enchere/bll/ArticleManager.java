package fr.eni.enchere.bll;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.Utilitaires;


public class ArticleManager {
	
	private static ArticleManager instance;
	private ArticleDAO articleDAO;

	public Article insertArticle(String nomArticle, String description, String categorie, String image,
			String miseAPrix, String dateDebutEncheres, String dateFinEncheres, String rue, String codePostal,
			String ville, Utilisateur utilisateur) throws BusinessException{
		
		Article article = null;
		BusinessException be = new BusinessException();
		
		Date dateDebut = parseDate(dateDebutEncheres,be); 
		Date dateFin = parseDate(dateFinEncheres,be);
		int miseAPrixInt = parseInt(miseAPrix,be);
		Categorie categorieObj = parseCategorie(categorie,be);
		Adresse adresse = parseAdresse(rue,codePostal,ville,be);

		this.compareDate(dateDebut, dateFin, be);
		
		if(!be.hasErreurs())
		{
			article = new Article(nomArticle, description, dateDebut, dateFin, miseAPrixInt, 0, Article.EtatsVente.CREATION, utilisateur, categorieObj, null, adresse);
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
	
	public Article getArticleByNo(Integer noArticle) throws BusinessException  {
		return articleDAO.getArticleByNo(noArticle);
	}
	
	public List<Article> getArticles(String categorie, String utilisateur) throws BusinessException  {
		return articleDAO.getArticles();
	}
	
	public void deleteArticle(Article article) throws BusinessException {
		BusinessException be = new BusinessException();
		this.notExists(article, be);
		if(!be.hasErreurs())
		{
			articleDAO.deleteArticle(article);
			System.out.println("deleteArticle OK");
		}
		else
		{
			throw be;
		}
	}
	
	public Article updateArticle(Article articleInitial, String[] modifications) throws BusinessException{
		BusinessException be = new BusinessException();
			
		String nomArticle = modifications[0];
		String description = modifications[1];
		Date dateDebut = parseDate(modifications[2],be); 
		Date dateFin = parseDate(modifications[3],be);
		int miseAPrixInt = parseInt(modifications[4],be);
		Categorie categorie = parseCategorie(modifications[5],be);
		

		this.compareDate(dateDebut, dateFin, be);
		Article article = new Article(articleInitial.getNoArticle(), nomArticle, description, dateDebut, dateFin, miseAPrixInt, articleInitial.getPrixVente(), articleInitial.getEtatVente(), articleInitial.getVendeur(), categorie, null, articleInitial.getRetrait());
		
		this.valideArticle(article, be);
			
		if(!be.hasErreurs())
		{
			article = articleDAO.updateArticle(article);
			System.out.println("modifArticle OK");
		}
		else
		{
			throw be;
		}

		return article;
	}
	
	

	private void valideArticle(Article article, BusinessException be) {
		if (article.getNomArticle() == null || article.getNomArticle() == "") {be.ajouterErreur(CodesResultatBLL.ARTICLE_UPDATE_NOM);}
		if (article.getDescription() == null || article.getDescription() == "") {be.ajouterErreur(CodesResultatBLL.ARTICLE_UPDATE_DESCRIPTION);}
		if (article.getDateDebutEncheres() == null) {be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE);}
		if (article.getDateFinEncheres() == null) {be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE_FIN);}
		if (article.getCategorie() == null) {be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_CATEGORIE);}
		if (article.getRetrait() == null) {be.ajouterErreur(CodesResultatBLL.ARTICLE_UPDATE_RETRAIT);}
		
	}


	private Adresse parseAdresse(String rue, String codePostal, String ville, BusinessException be) {

		if (!Utilitaires.isAlphaNumeric(rue)) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_RUE);
		} 
		if (!StringUtils.isInteger(codePostal)) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_CODE_POSTAL);
		} 
		if (!Utilitaires.isAlphaNumeric(ville)) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_VILLE);
		} 
		
		Adresse adresse = new Adresse(rue, codePostal, ville);

		return adresse;
	}

	private int parseInt(String strPrix, BusinessException be) {
		int prix = 0;
		try {
			if (!StringUtils.isInteger(strPrix)) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_PRIX);
			} else {
				prix = Integer.parseInt(strPrix);
				}
		} catch (Exception e) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_PRIX);
		}
		
		return prix;
	}

	private Categorie parseCategorie(String categorieStr, BusinessException be) {
		Categorie categorie = null;
		int noCategorie = 0;
		
		try {
			if (!StringUtils.isInteger(categorieStr)) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_CATEGORIE);
			} else {
				noCategorie = Integer.parseInt(categorieStr);
				categorie = CategorieManager.getInstance().getCategorieByNo(noCategorie);
				}
		} catch (Exception e) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_CATEGORIE);
		}
		
		return categorie;
	}

	private Date parseDate(String dateString, BusinessException be) throws BusinessException {
		String[] dateArray = dateString.split("-");
		Date date = new Date();
		int annee = 0, mois = 0, jour = 0;
		try {
			if (!StringUtils.isInteger(dateArray[0])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_ANNEE);
			} else {
				annee = Integer.parseInt(dateArray[0]);
				}
			if (!StringUtils.isInteger(dateArray[1])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_MOIS);
			} else {
				mois = Integer.parseInt(dateArray[1]);
				}
			if (!StringUtils.isInteger(dateArray[2])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_JOUR);
			} else {
				jour = Integer.parseInt(dateArray[2]);
				}
			
			Calendar calendar = new GregorianCalendar(annee, mois-1, jour);
			date = calendar.getTime();
		} catch (Exception e) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE);
		}
		
		return date;
	}

	
	private void compareDate(Date dateDebut, Date dateFin, BusinessException be) throws BusinessException {

		int dateCompare = dateDebut.compareTo(dateFin);

		if (dateCompare >= 0) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_DATE_DEBUT_SUPERIEUR_DATE_FIN); 
		}
	}

	private void notExists(Article article, BusinessException be) throws BusinessException {
		if(article == null) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_NON_EXISTANT);
		}
	}
	
}

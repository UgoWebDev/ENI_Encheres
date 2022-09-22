package fr.eni.enchere.bll;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class ArticleManager {
	// Utilisation du pattern Singleton

	private static ArticleManager instance;
	private UtilisateurDAO utilisateurDAO;

	public ArticleManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public  Article insertArticle(Article article) throws BusinessException {
		BusinessException be = new BusinessException();
		
		
		
		
		if(!be.hasErreurs())
		{
		//	article = utilisateurDAO.insertArticle(article);
		//	System.out.println("insertUtilisateur OK");
		}
		else
		{
			throw be;
		}



		return article;
	}

	private void valideArticle(Article article, BusinessException be) {


	}
}

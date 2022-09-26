package fr.eni.enchere.dal;

import fr.eni.enchere.dal.jdbc.AdresseDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO()
	{
		return new ArticleDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static AdresseDAO getAdresseDAO() {
		return new AdresseDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

}
	
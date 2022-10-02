package fr.eni.enchere.dal;


import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {

	Article insertArticle(Article article) throws BusinessException;

	List<Article> getArticles(Categorie categorie, Utilisateur utilisateur, String recherche) throws BusinessException;

	void deleteArticle(Article article) throws BusinessException;

	Article getArticleByNo(Integer noArticle) throws BusinessException ;

	Article updateArticle(Article article) throws BusinessException;


}

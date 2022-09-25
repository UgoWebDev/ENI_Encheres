package fr.eni.enchere.dal;


import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;

public interface ArticleDAO {

	Article insertArticle(Article article) throws BusinessException;

	List<Article> getArticles();

	void deleteArticle(int noArticle);

	Article getArticleByNoArticle(String nomArticle);


}

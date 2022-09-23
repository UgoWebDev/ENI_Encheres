package fr.eni.enchere.dal;


import fr.eni.enchere.bo.Article;

public interface ArticleDAO {

	Article getArticleByNoArticle(String noArticle);

	Article insertArticle(Article article);

	

}

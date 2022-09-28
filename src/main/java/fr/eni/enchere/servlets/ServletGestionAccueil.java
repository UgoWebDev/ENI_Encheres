package fr.eni.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;

/**
 * Servlet implementation class ServletGestionAccueil
 */
@WebServlet("/accueil")
public class ServletGestionAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Avant le setAttribute listeCategories");
		request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
		List<Article> test = new ArrayList<>();
		test = ArticleManager.getInstance().getArticles(null, null);
		for (Article article : test) {
			System.out.println(article);
		}
		
		System.out.println("Avant le setAttribute listeArticle : " + test);
		request.setAttribute("listeArticle", test);

		System.out.println("Avant le forward : " + test);
		request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

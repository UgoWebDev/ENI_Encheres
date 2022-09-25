package fr.eni.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.enchere.bll.ArticleManager;

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
		List<String> listeCategories = new ArrayList<>();
		listeCategories.add("Ameublement");
		listeCategories.add("Info");
		listeCategories.add("Video");
		
		request.setAttribute("listeCategories",listeCategories);
		
		request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null, null));

		
		request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

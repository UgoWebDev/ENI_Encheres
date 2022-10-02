package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;

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
		
		
		
		try {
			request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
			request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null, null,null));
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}

		request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String recherche = request.getParameter("chaineRecherche");
		String noCategorieString = request.getParameter("categorie");
		
		switch (action) {
		case "rechercher":
			try {
				request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
				request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(noCategorieString, null,recherche));
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			break;
		}
	}
}

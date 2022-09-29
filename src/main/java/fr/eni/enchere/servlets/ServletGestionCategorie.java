package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.CategorieManager;

/**
 * Servlet implementation class ServletGestionCategorie
 */
@WebServlet("/categorie")
public class ServletGestionCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
		request.getRequestDispatcher("/WEB-INF/jsp/GestionCategorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelle = request.getParameter("libelle");
		String action = request.getParameter("action");
		
		switch (action) {
		case "creation":
			try {
				CategorieManager.getInstance().insertCategorie(libelle);
				response.sendRedirect(request.getContextPath() + "/categorie");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionCategorie.jsp").forward(request, response);
			}
			break;
			
		case "suppression":
			try {
				CategorieManager.getInstance().deleteCategorie(libelle);
				response.sendRedirect(request.getContextPath() + "/categorie");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionCategorie.jsp").forward(request, response);
			}	
			break;
			
		case "annulation":
			response.sendRedirect(request.getContextPath() + "/accueil");
			
			
			break;

		default:
			break;
		}
		
	}

}

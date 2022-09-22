package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

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
		request.getRequestDispatcher("/WEB-INF/jsp/GestionCategorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelle = request.getParameter("libelle");
		String action = request.getParameter("action");
		Categorie categorie = null;
		
		switch (action) {
		case "creation":
			try {
				categorie = CategorieManager.getInstance().insertCategorie(libelle);
				response.sendRedirect(request.getContextPath() + "/categorie");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionCategorie.jsp").forward(request, response);
			}
			break;
			
		case "suppression":
			response.sendRedirect(request.getContextPath() + "/profil");
			
			
			break;
			
		case "annulation":
			response.sendRedirect(request.getContextPath() + "/accueil");
			
			
			break;

		default:
			break;
		}
		
	}

}

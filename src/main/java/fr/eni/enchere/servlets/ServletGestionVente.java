package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletGestionVente
 */
@WebServlet("/vente")
public class ServletGestionVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/GestionVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		String image = request.getParameter("image");
		String miseAPrix = request.getParameter("miseAPrix");
		String dateDebutEncheres = request.getParameter("dateDebutEncheres");
		String dateFinEncheres = request.getParameter("dateFin");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		Utilisateur article = null;
		
		switch (action) {
		case "enregistrer":
			try {
				article = ArticleManager.insertArticle(nomArticle, description, categorie, image, miseAPrix, dateDebutEncheres, dateFinEncheres, rue, codePostal, ville);
				response.sendRedirect("accueil");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			}
			break;
			
		case "annuler":
			response.sendRedirect("accueil");	
		break;
		
		case "annulerVente":
			try {
				article = ArticleManager.getInstance().article(login, mdp);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("http://www.google.com");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
				
			}
		break;

		default:
		break;
		}
	}

}

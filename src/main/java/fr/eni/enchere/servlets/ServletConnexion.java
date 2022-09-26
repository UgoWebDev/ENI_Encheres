package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("deconnexion")) {
			request.getSession().setAttribute("user", null);
			response.sendRedirect(request.getContextPath() + "/accueil");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String action = request.getParameter("action");
		Utilisateur user = null;

		switch (action) {
		case "connexion":
			try {
				user = UtilisateurManager.getInstance().seConnecter(login, mdp);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("accueil"); //repasse par la servlet et non directement vers la jsp
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
			}
			break;

		case "inscription":
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

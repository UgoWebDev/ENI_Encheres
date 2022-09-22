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
 * Servlet implementation class ServletInscription
 */
@WebServlet("/profil")
public class ServletGestionProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String prenom = request.getParameter("prenom");
		String tel = request.getParameter("tel");
		String codepostal = request.getParameter("codepostal");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String password = request.getParameter("password");

		String action = request.getParameter("action");
		

		System.out.println("action : "+ " | "  + action + "données : " + login + " | " + mdp + " | " +  prenom + " | " +  nom + " | " +  tel + " | " +  codepostal + " | " +  email + " | " +  rue + " | " +  ville + " | " +  password + " | " );
		Utilisateur user = null;
		
		switch (action) {
		case "creation":

			try {
				user = new Utilisateur(login, nom, prenom, email, tel, rue, codepostal, ville, mdp, 100, false);
				user = UtilisateurManager.getInstance().insertUtilisateur(user,password);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("http://www.google.com");
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}
			break;

		case "suppression":
			request.getRequestDispatcher("/WEB-INF/jsp/EnCours.jsp").forward(request, response);


			break;
			
		case "modification":
			request.getRequestDispatcher("/WEB-INF/jsp/EnCours.jsp").forward(request, response);


			break;
			
		case "annulation":
			request.getRequestDispatcher("/WEB-INF/jsp/EnCours.jsp").forward(request, response);


			break;

		default:
			break;
		}
	}
}

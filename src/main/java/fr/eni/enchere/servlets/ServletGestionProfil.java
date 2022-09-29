package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Adresse;
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
		String pseudo = request.getParameter("pseudo");
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
		

		System.out.println("action : " + action);
		System.out.println("pseudo : " + pseudo); 
		System.out.println("prenom : " +  prenom);
		System.out.println("nom : " +  nom);
		System.out.println("tel : " +  tel);
		System.out.println("codepostal : " +  codepostal);
		System.out.println("email : " +  email);
		System.out.println("rue : " +  rue);
		System.out.println("ville : " +  ville);
		System.out.println("mdp : " + mdp);
		System.out.println("password : " +  password);
		Utilisateur user = null;
		Adresse adresse = null;
		
		switch (action) {
		case "creation":

			try {
				adresse = new Adresse(rue, codepostal, ville);
				user = new Utilisateur(pseudo, nom, prenom, email, tel, adresse, mdp, 100, false,null,null);
				user = UtilisateurManager.getInstance().insertUtilisateur(user,password);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}
			break;
		
		case "enregistrer":

			try {
				
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}


			break;

		case "suppression":
			request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);


			break;
			
		case "modification":
			request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);


			break;
			
		case "annulation":
			response.sendRedirect("accueil");
			
			break;

		default:
			break;
		}
	}
}

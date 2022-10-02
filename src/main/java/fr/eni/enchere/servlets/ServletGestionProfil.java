package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
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
		

		Utilisateur user = null;
		Adresse adresse = null;
		
		switch (action) {
		case "creation":

			try {
				adresse = new Adresse(rue, codepostal, ville);
				user = new Utilisateur(pseudo, nom, prenom, email, tel, adresse, mdp, 100, false,null,null);
				user = UtilisateurManager.getInstance().insertUtilisateur(user,password);
				request.getSession().setAttribute("user", user);
				try {
					request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
					request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null, null,null));
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}
			break;
		
		case "modification":
			try {
				Utilisateur userConnected = (Utilisateur) request.getSession().getAttribute("user");
				adresse = new Adresse(rue, codepostal, ville);
				adresse = AdresseManager.getInstance().updateAdresse(adresse,userConnected.getAdresse());
				user = new Utilisateur(userConnected.getNoUtilisateur(), pseudo, nom, prenom, email, tel, adresse, mdp, userConnected.getCredit(), false,null,null);
				user = UtilisateurManager.getInstance().updateUtilisateur(user,userConnected,password);
				request.getSession().setAttribute("user", user);
				request.setAttribute("OK", 1);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}


			break;

		case "suppression":
			try {
				UtilisateurManager.getInstance().deleteUtilisateur((Utilisateur) request.getSession().getAttribute("user"));
				request.getSession().setAttribute("user", null);
				try {
					request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
					request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null,null, null));
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}


			break;
			
		case "annulation":
			response.sendRedirect("accueil");
			
			break;

		default:
			break;
		}
	}
}

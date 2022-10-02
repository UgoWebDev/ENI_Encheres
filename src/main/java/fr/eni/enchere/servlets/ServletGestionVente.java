package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.Utilitaires;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Article.EtatsVente;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletGestionVente
 */
@WebServlet("/vente")
public class ServletGestionVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private Article article = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("listeCategories",CategorieManager.getInstance().getCategories());
		if(request.getParameter("noArticle") != null) {
			try {
				article = ArticleManager.getInstance().getArticleByNo(Integer.valueOf(request.getParameter("noArticle")));
				request.setAttribute("article", article);
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
			}
		}
		
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
		String miseAPrix = request.getParameter("miseAPrix");
		String dateDebutEncheres = request.getParameter("dateDebutEncheres");
		String dateFinEncheres = request.getParameter("dateFinEncheres");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		
		
		switch (action) {
		case "enregistrer":
			try {
				Article article =  ArticleManager.getInstance().insertArticle(nomArticle, description, categorie, null, miseAPrix, dateDebutEncheres, dateFinEncheres, rue, codePostal, ville,(Utilisateur) request.getSession().getAttribute("user"));
				try {
					
					request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
					request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null, null,null));
					request.setAttribute("article", article);
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
				Categorie cat = CategorieManager.getInstance().getCategorieByNo(Integer.parseInt(categorie));
				Adresse adresse = new Adresse(article.getRetrait().getNoAdresse(), rue, codePostal, ville);
				Article article = null;
				try {
					article = new Article(nomArticle, description, Utilitaires.parseDate(dateDebutEncheres), Utilitaires.parseDate(dateFinEncheres), Integer.parseInt(miseAPrix), 0, EtatsVente.CREATION, user, cat, null, adresse);
				} catch (NumberFormatException | BusinessException e1) {
					article = new Article(nomArticle, description, null, null, Integer.parseInt(miseAPrix), 0, EtatsVente.CREATION, user, cat, null, adresse);
				}
				request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
				request.setAttribute("article", article);
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionVente.jsp").forward(request, response);
			}
			break;
			
		case "modification":
			try {
				Adresse retrait = new Adresse(article.getRetrait().getNoAdresse(), rue, codePostal, ville);
				retrait = AdresseManager.getInstance().updateAdresse(retrait, article.getRetrait());
				article.setRetrait(retrait);
				String [] articleModifie = new String[] {nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, categorie};
				article = ArticleManager.getInstance().updateArticle(article, articleModifie);
				request.getSession().setAttribute("article", article);
				try {
					request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
					request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null,null, null));
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("article", article);
				request.getRequestDispatcher("/WEB-INF/jsp/GestionProfil.jsp").forward(request, response);
			}


			break;
			
		case "annulerVente":
			try {
				ArticleManager.getInstance().deleteArticle(article);
				request.getSession().setAttribute("noArticle", null);
				try {
					request.setAttribute("listeCategories", CategorieManager.getInstance().getCategories());
					request.setAttribute("listeArticle", ArticleManager.getInstance().getArticles(null,null, null));
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/GestionAccueil.jsp").forward(request, response);
				
			}
		break;

		default:
		break;
		
		case "annuler":
			response.sendRedirect("accueil");	
		break;
		}
	}

}

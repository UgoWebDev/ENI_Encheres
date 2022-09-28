package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Article;

/**
 * Servlet implementation class GestionDetailsVente
 */
@WebServlet("/detail")
public class ServletGestionDetailsVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article;
		
		String strArticle = request.getParameter("noArticle");
		System.out.println(strArticle);
		
		int noArticle = Integer.parseInt(strArticle);
		System.out.println(noArticle);
		
		article = ArticleManager.getInstance().getArticleByNo(noArticle);
		
		System.out.println(article);
		
		request.setAttribute("article", article) ;
		request.getRequestDispatcher("/WEB-INF/jsp/GestionDetailsVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action == "encherir") {
			response.sendRedirect("profil");
		}
	}

}

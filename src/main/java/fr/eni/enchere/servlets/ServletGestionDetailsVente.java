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
		
		article = ArticleManager.getInstance().getArticleByNo(Integer.parseInt(request.getParameter("noArticle")));

		request.setAttribute("article", article) ;
		request.getRequestDispatcher("/WEB-INF/jsp/GestionDetailVente.jsp").forward(request, response);
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

package fr.eni.projetenchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.ArticleVenduManager;
import fr.eni.projetenchere.bll.ArticleVenduManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;

/**
 * Servlet implementation class ServletSupprimerArticle
 */
@WebServlet("/ServletSupprimerArticle")
public class ServletSupprimerArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArticleVenduManager articleVenduManager = ArticleVenduManagerSing.getInstanceArticle();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSupprimerArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idArticle;
		ArticleVendu articleASupprimer = null;

		HttpSession session = request.getSession();
		if (session.getAttribute("articleAModifier") == null) {
			idArticle = Integer.parseInt(request.getParameter("idArticle"));
			try {
				articleASupprimer = articleVenduManager.selectParIdArticle(idArticle);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		} else {
			articleASupprimer = (ArticleVendu) session.getAttribute("articleAModifier");
		}
		try {
			articleVenduManager.supprimerArticleVendu(articleASupprimer);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/ServletAjoutArticle").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession().getAttribute("id");

		HttpSession session = request.getSession();
		ArticleVendu articleASupprimer = (ArticleVendu) session.getAttribute("articleAManipuler");
		
		System.out.println(articleASupprimer);
		
		try {
			articleVenduManager.supprimerArticleVendu(articleASupprimer);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./ServletModifierArticle");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

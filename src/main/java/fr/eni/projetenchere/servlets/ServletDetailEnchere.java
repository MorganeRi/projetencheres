package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.ArticleVenduManager;
import fr.eni.projetenchere.bll.ArticleVenduManagerSing;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.EnchereManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;

/**
 * Servlet implementation class ServletDetailEnchere
 */
@WebServlet("/ServletDetailEnchere")
public class ServletDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();
	private static ArticleVenduManager articleVenduManager = ArticleVenduManagerSing.getInstanceArticle();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailEnchere() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer idArticle = (Integer) session.getAttribute("idArticle");
		ArticleVendu article;
		List<Enchere> listEncheres = new ArrayList<>();

		try {
			article = articleVenduManager.selectParIdArticle(idArticle);
			listEncheres = enchereManager.selectEnchereById(article);
			request.setAttribute("listeEnchere", listEncheres);
			System.out.println(listEncheres);

		} catch (BusinessException e) {

			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailEnchere.jsp");
		rd.forward(request, response);
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

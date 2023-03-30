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

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.ArticleVenduManager;
import fr.eni.projetenchere.bll.ArticleVenduManagerSing;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.CategorieManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueil() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticleVenduManager article = ArticleVenduManagerSing.getInstanceArticle();

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		try {
		articles = article.selectToutArticle();
			// System.out.println(articles.toString());
			request.setAttribute("listToutArticle", articles);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Categorie> listCategorie = new ArrayList<>();
		listCategorie=affichageCategorie();
		
		request.setAttribute("listCategorie", listCategorie);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Categorie> listCategorie = new ArrayList<>();
		listCategorie=affichageCategorie();
		
		request.setAttribute("listCategorie", listCategorie);
		
		String recherche;
		Integer noCategorie;
		
		recherche = request.getParameter("search");
		ArticleVenduManager article = ArticleVenduManagerSing.getInstanceArticle();

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		String noCat;
		System.out.println((request.getParameter("Categorie")));
		noCat=request.getParameter("Categorie");
		if (noCat.equals("Selectionner une categorie")) {
			try {
				articles = article.selectParNomArticle(recherche);

				if (articles==null) {
					request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
				}else {
					request.setAttribute("listArticle", articles);
				}
				



				
			} catch (BusinessException e) {

				e.printStackTrace();
			}
		} else {
		noCategorie = Integer.parseInt(request.getParameter("Categorie"));
			
			try {
				articles = article.selectParNomArticleParCat(recherche, noCategorie);

				if (articles==null) {
					request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
				}else {
					request.setAttribute("listArticle", articles);
				}

			} catch (BusinessException e) {

				e.printStackTrace();
			}
			
			
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
	
	private List<Categorie>  affichageCategorie() {
		
		List<Categorie> listCategorie = new ArrayList<>();
		CategorieManager catMan = CategorieManagerSing.getInstanceCategorieImpl();
		try {

			listCategorie = catMan.selectAllCategorie();
			

			
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listCategorie;
		
	}
	
	

}

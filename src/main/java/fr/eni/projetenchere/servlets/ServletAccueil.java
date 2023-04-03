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

			request.setAttribute("listToutArticle", articles);

		} catch (BusinessException e) {

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
		Integer noCategorie= null;
		String achatOuVente = request.getParameter("options");
		HttpSession session = request.getSession();
		
		recherche = request.getParameter("search");
		ArticleVenduManager article = ArticleVenduManagerSing.getInstanceArticle();

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		String noCat;
		noCat=request.getParameter("Categorie");
		


		
        String formulaire = request.getParameter("form");

        if(formulaire == null) {
            this.doGet(request, response);
        }
        
        if(formulaire.equals("form1")) {

            if ((noCat.equals("Selectionner une categorie")) && recherche.isBlank()) {
            	try {

					articles = article.selectToutArticle();
					if (articles==null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche 1");
					}else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (recherche.isBlank()) {
            	try {
					articles =  article.selectParCategorieArticle(noCat);
					if (articles==null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche 2");
					}else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (noCat == null || noCat.isBlank()) {
            	try {
					articles =  article.selectParNomArticle(recherche);
					if (articles==null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche 3");
					}else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
            	try {
					articles =  article.selectParNomArticleParCat(recherche, noCat);
					if (articles==null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche 4");
					}else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        	
        }
        
		

			
//		if ("achats".equals(achatOuVente)) {
//			
//			try {
//				articles=article.selectParNomArticleParCatSaufUtil(recherche, noCategorie, (Integer)session.getAttribute("id"));
//			} catch (BusinessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} else if ("ventes".equals(achatOuVente)) {
//			
//			try {
//				articles=article.selectParNomArticleParCatParUtil(recherche, noCategorie, (Integer)session.getAttribute("id"));
//			} catch (BusinessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
			
				
	
			
		
		

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

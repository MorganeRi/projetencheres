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
		listCategorie = affichageCategorie();

		request.setAttribute("listCategorie", listCategorie);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp" );
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Categorie> listCategorie = new ArrayList<>();
		listCategorie = affichageCategorie();

		request.setAttribute("listCategorie", listCategorie);

		String recherche;
		HttpSession session = request.getSession();

		recherche = request.getParameter("search");
		ArticleVenduManager article = ArticleVenduManagerSing.getInstanceArticle();

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		String noCat;
		noCat = request.getParameter("Categorie");

		String formulaire = request.getParameter("form");

		if (formulaire == null) {
			this.doGet(request, response);
		}
		// Si l'utilisateur fait une recherche par nom et/ ou catégorie
		if (formulaire.equals("form1")) {

			if ((noCat.equals("Selectionner une categorie")) && recherche.isBlank()) {
				try {

					articles = article.selectToutArticle();
					if (articles == null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
					} else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {

					e.printStackTrace();
				}
			} else if (recherche.isBlank()) {
				try {
					articles = article.selectParCategorieArticle(noCat);
					if (articles == null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
					} else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {

					e.printStackTrace();
				}
			} else if (noCat.equals("Selectionner une categorie")) {
				try {
					articles = article.selectParNomArticle(recherche);
					if (articles == null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
					} else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {

					e.printStackTrace();
				}
			} else {
				try {
					articles = article.selectParNomArticleParCat(recherche, noCat);
					if (articles == null) {
						request.setAttribute("PasArticle", "Il n'y a pas d'article correspondant à votre recherche");
					} else {
						request.setAttribute("listArticle", articles);
					}
				} catch (BusinessException e) {

					e.printStackTrace();
				}
			}

		}

		// Si l'utilisateur fait une recherche via les checks Box
		else if (formulaire.equals("form2")) {

			Integer idUtil = (Integer) session.getAttribute("id");
			String check = request.getParameter("check");
			if (check != null) {

				switch (check) {
				case "encheres_ouvertes":
					try {

						articles = article.affichageArticlesEnVente(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle", "Vous n'avez pas d'article en vente");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				case "mes_encheres":
					try {

						articles = article.articleEncherie(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle", "Vous n'avez pas encheris sur un article");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				case "encheres_remportees":
					try {

						articles = article.articleEnchereRemporte(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle", "Vous n'avez pas remporte d'enchere");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				case "ventes_cours":
					try {

						articles = article.ventesEnCours(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle", "Vous n'avez pas de vente en cours");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				case "ventes_non_debutees":
					try {

						articles = article.ventesNonDebutees(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle",
									"Vous n'avez d'article en attente d'être mis aux enchères");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				case "ventes_terminees":
					try {

						articles = article.ventesTerminees(idUtil);
						if (articles == null) {
							request.setAttribute("PasArticle", "Vous n'avez pas encore vendu d'article");
						} else {
							request.setAttribute("listArticle", articles);
						}
					} catch (BusinessException e) {

						e.printStackTrace();
					}
					break;
				}

			} else {
				request.setAttribute("PasArticle", "Veuillez cocher une option");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);

	}

	private List<Categorie> affichageCategorie() {

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

package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
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
import fr.eni.projetenchere.bll.RetraitManager;
import fr.eni.projetenchere.bll.RetraitManagerSing;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/ServletAjoutArticle")
public class ServletAjoutArticle extends HttpServlet {
	private static final String ARTICLE_A_MANIPULER = "articleAManipuler";
	private static final String LIST_CATEGORIE = "listCategorie";
	private static final String UTILISATEUR = "Utilisateur";
	private static final long serialVersionUID = 1L;
	
	private static CategorieManager categorieManager = CategorieManagerSing.getInstanceCategorieImpl();

	private static UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();

	private static ArticleVenduManager articleVenduManager = ArticleVenduManagerSing.getInstanceArticle();
	
	private static RetraitManager retraitManager = RetraitManagerSing.getInstanceRetraitImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAjoutArticle() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		gérer la récupération de la session en cours 
		HttpSession session = request.getSession();
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		
//	 	gérer la récupération de session utilisateur
		Integer noUtilisateur;
		Utilisateur utilisateur = new Utilisateur();
		
// 		récupérer la liste des catégories disponibles dans ma BDD
		List<Categorie> listCategorie = new ArrayList<>();
		
		
		if (idUtilisateur == null) {
			// Rediriger vers la page de connexion
			response.sendRedirect("./seConnecter");
		} else {
			try {

				listCategorie = categorieManager.selectAllCategorie();

				request.setAttribute(LIST_CATEGORIE, listCategorie);
				
				noUtilisateur = (Integer) request.getSession().getAttribute("id");
				utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
				request.setAttribute(UTILISATEUR, utilisateur);
			} catch (BusinessException e1) {

				e1.printStackTrace();
			}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nomArticle = null;
		String description = null;
		Integer noCategorie = null;
		LocalDateTime dateDebutEnchere = null;
		LocalDateTime dateFinEnchere = null;
		Integer prixInitial = null;
		String rue = null;
		String codePostal = null;
		String nomVille = null;
		
		Categorie categorie = null;

		Retrait retrait=null;
		
		Integer noUtilisateur;
		Utilisateur utilisateur = new Utilisateur();
		
		ArticleVendu articleVendu = null;
		
		Integer resultatComparaisonDates = null;
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		

		String photo = null;
		


		try {
//			récupérer les données rentrées par l'utilisateur pour ajouter article 
			nomArticle = request.getParameter("nomArticle");
			description = request.getParameter("Description");
			noCategorie = Integer.parseInt(request.getParameter("Categorie"));
			dateDebutEnchere = LocalDateTime.parse(request.getParameter("DebutEnchere"));
			dateFinEnchere = LocalDateTime.parse(request.getParameter("FinEnchere"));
			prixInitial = Integer.parseInt(request.getParameter("prixDepart"));
			
			rue = request.getParameter("nomRue");
			codePostal = request.getParameter("codePostal");
			nomVille = request.getParameter("nomVille");

//			méthode pour récupérer l'utilisateur qui créer l'article
			noUtilisateur = (Integer) request.getSession().getAttribute("id");
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
			
			categorie = categorieManager.selectCategorieParId(noCategorie);
			
			

			String test = request.getParameter("imageArticle");
			Boolean photoOuPas= true;
			
			articleVendu = new ArticleVendu(nomArticle, description, dateDebutEnchere, dateFinEnchere,
					prixInitial, utilisateur, categorie);
			
			if (test.equals("")) {
				
				photoOuPas = false;
			}


			articleVendu = new ArticleVendu(nomArticle, description, dateDebutEnchere, dateFinEnchere,
					prixInitial, utilisateur, categorie);
			if (photoOuPas) {
				
				photo = request.getParameter("imageArticle");
				
				articleVendu.setPhoto(photo);
				
			}
			retrait = new Retrait(rue,codePostal,nomVille,articleVendu);
			
			resultatComparaisonDates = dateDebutEnchere.compareTo(dateFinEnchere);
			if(resultatComparaisonDates > 0) {
//				la date de début est postérieure à la date de fin 
				listeCodesErreur.add(CodesResultatServlets.ERREUR_DATE_POSTERIEUR);
				request.setAttribute("listeCodesErreur", listeCodesErreur);


			} else if (resultatComparaisonDates == 0) {
//				les deux dates sont égales
				listeCodesErreur.add(CodesResultatServlets.ERREUR_DATES_IDENTIQUES);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				
			}else if(resultatComparaisonDates < 0){
//				la date de début est antérieure à la date de fin 
//				appel à la BLL pour ajouter Article + Retrait
				articleVenduManager.ajouterArticleVendu(articleVendu);
				retraitManager.ajouterRetrait(retrait);
				request.setAttribute("articleAManipuler", articleVendu);

//				permettre d'instancier un attribut dans la session pour le recuperer
//				dans une autre Servlet
				
				session.setAttribute(ARTICLE_A_MANIPULER, articleVendu);
			}
			


		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}

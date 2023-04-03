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
 * Servlet ServletModifierArticle qui permet de :
 * -Afficher toutes les infos actuelles de l'article
 * -Permettre de changer les infos dans chaque champ
 * -Mettre à jour les changements d'infos de l'article
 * -Supprimer l'article 
 */
@WebServlet("/ServletModifierArticle")
public class ServletModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LIST_CATEGORIE = "listCategorie";
//	private static final String UTILISATEUR = "Utilisateur";
	
	private static CategorieManager cateegorieManager = CategorieManagerSing.getInstanceCategorieImpl();
	
	private static UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
	
	private static ArticleVenduManager articleVenduManager = ArticleVenduManagerSing.getInstanceArticle();
	
	private static RetraitManager retraitManager = RetraitManagerSing.getInstanceRetraitImpl();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModifierArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Ici mon doGet va permettre lors de l'appel de la Servlet de : 
	 * - récupérer la session en cours, si pas de session on renvoie direct à la ServletConnexion
	 * - On va aller chercher les catégories en BDD pour les réafficher dans le déroulant de la JSP
	 * - On va récupérer l'idArticle stocké dans la session (qui permet d'acceder au modifierArticle
	 * quand on est sur le detail de l'article)
	 * - On récupère les infos retrait en fonction de l'idArticle
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		gérer la récupération de session utilisateur
		HttpSession session = request.getSession();
        Integer idUtilisateur = (Integer) session.getAttribute("id");
        List<Categorie> listCategorie = new ArrayList<>(); 
       
		Integer noArticle = null;
		ArticleVendu articleAAfficher = new ArticleVendu();
		Retrait retrait = null;
        
        if (idUtilisateur == null) {
            response.sendRedirect("ServletConnexion");
            return;
        } else {
			try {
				listCategorie = cateegorieManager.selectAllCategorie();
				request.setAttribute(LIST_CATEGORIE, listCategorie);
				
				noArticle = (Integer) session.getAttribute("idArticle");
				articleAAfficher = articleVenduManager.selectParIdArticle(noArticle);
				request.setAttribute("articleAManipuler", articleAAfficher);
				
				retrait = retraitManager.selectParIdRetrait(noArticle);
				request.setAttribute("retrait", retrait);
				
			} catch (BusinessException e1) {
				
				e1.printStackTrace();
			}
			session.setAttribute("articleAModifier", articleAAfficher);

			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierArticle.jsp");
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
		Integer noUtilisateur = null;
		Utilisateur utilisateur = new Utilisateur();
		
		ArticleVendu articleAManipuler = (ArticleVendu) session.getAttribute("articleAModifier");
		Integer noCategorie = null;
		Categorie categorie = null;
		Retrait retrait = new Retrait();
		
		Integer resultatComparaisonDates = null;
		
		LocalDateTime dateDebutEnchere = null;
		LocalDateTime dateFinEnchere = null;
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		try {
			articleAManipuler.setNomArticle(request.getParameter("nomArticle"));
			articleAManipuler.setDescription(request.getParameter("Description"));
			
			noCategorie = Integer.parseInt(request.getParameter("Categorie"));
			categorie = cateegorieManager.selectCategorieParId(noCategorie);
			
			articleAManipuler.setCategorie(categorie);
			articleAManipuler.setDateDebutEnchere(LocalDateTime.parse(request.getParameter("DebutEnchere")));
			articleAManipuler.setDateFinEnchere(LocalDateTime.parse(request.getParameter("FinEnchere")));
			articleAManipuler.setPrixInitial(Integer.parseInt(request.getParameter("prixDepart")));
			
			noUtilisateur = (Integer) request.getSession().getAttribute("id");
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
			articleAManipuler.setUtilisateur(utilisateur);
			
			retrait.setArticle(articleAManipuler);
			retrait.setRue(request.getParameter("nomRue"));
			retrait.setCodePostal(request.getParameter("codePostal"));
			retrait.setVille(request.getParameter("nomVille"));
			
			dateDebutEnchere = articleAManipuler.getDateDebutEnchere();
			dateFinEnchere = articleAManipuler.getDateFinEnchere();
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
				articleVenduManager.majArticleVendu(articleAManipuler);
				retraitManager.majRetrait(retrait);
				request.setAttribute("articleModifie", articleAManipuler);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}
}

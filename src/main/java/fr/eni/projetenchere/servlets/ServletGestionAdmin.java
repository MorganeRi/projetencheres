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

import fr.eni.projetenchere.bll.ArticleVenduManager;
import fr.eni.projetenchere.bll.ArticleVenduManagerSing;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.CategorieManagerSing;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.EnchereManagerSing;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletGestionAdmin
 */
@WebServlet("/ServletGestionAdmin")
public class ServletGestionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String LIST_UTILISATEUR = "listUtilisateur";
	private static final String LIST_CATEGORIE = "listCategorie";
	
	private static CategorieManager categorieManager = CategorieManagerSing.getInstanceCategorieImpl();
	private static UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		Boolean estAdmin = (Boolean) session.getAttribute("admin");
		
		List<Categorie> listCategorie = new ArrayList<>();
		List<Utilisateur> listUtilisateur = new ArrayList<>();
		
//		|| !estAdmin
		if(idUtilisateur == null  || !estAdmin ) {
			// Rediriger vers la page de connexion
			response.sendRedirect("ServletConnexion") ;
		} else {
			try {
//				récupérer toutes les catégories présentes dans la BDD
				listCategorie = categorieManager.selectAllCategorie();
				
				request.setAttribute(LIST_CATEGORIE, listCategorie);
				session.setAttribute("listeCategories", listCategorie);
				
//				récupérer tous les utilisateurs présents dans la BDD
				listUtilisateur = utilisateurManager.getAllUtilisateur();
				
				request.setAttribute(LIST_UTILISATEUR, listUtilisateur);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AdminGestion.jsp");
			rd.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Categorie categorieARajouter,categorieAModifier,categorieASupprimer = null; 
		String nomCategorie = null;
		Integer noCategorie, noUtilisateur = null;
		Utilisateur utilisateur = new Utilisateur();
		Utilisateur utilisateurASupprimer,utilisateurOnOff = null;
		
		String actionCategorie = request.getParameter("action");
		String actionUtilisateur = request.getParameter("actionUtilisateur");
		
		List<Categorie> listCategorie = null;
		
		try {
			
			
			if("Ajout".equals(actionCategorie)) {
//				Traitement pour ajouter une catégorie
				nomCategorie = request.getParameter("nomCategorie");
				categorieARajouter = new Categorie(nomCategorie) ;
				
				listCategorie = (List<Categorie>) session.getAttribute("listeCategories");

//				on vérifie que la catégorie n'existe pas déjà : 
//				- stream expression lambda java pour parcourir la liste des catégories existantes
//				- méthode AnyMatch() qui vérifie si au moins 1 catégorie ds la liste a le même nom (boolean qui renverrait true ou false)
//				- equalsIgnoreCase permet d'ignorer la casse
 				Boolean categorieExisteDeja = listCategorie.stream()
				        .anyMatch(c -> c.getLibelle().equalsIgnoreCase(categorieARajouter.getLibelle()));
				
				// Si la catégorie n'existe pas encore, l'ajouter aux catégories
				if (!categorieExisteDeja) {
					categorieManager.ajouterCategorie(categorieARajouter);
//					permettre d'avoir accès à cet attribut depuis la JSP pour afficher message
//					de validation lors de l'insertion de la categorie
					request.setAttribute("categorieARajouter", categorieARajouter);
				} else {
					request.setAttribute("categorieAlreadyExists", categorieARajouter);
				    
				}
				
				
			} else if ("Modifier".equals(actionCategorie)) {
//				Traitement pour modifier une catégorie en recuperant son ancien libelle
				noCategorie = Integer.parseInt(request.getParameter("CategorieAModifier"));
				categorieAModifier = categorieManager.selectCategorieParId(noCategorie);
				
				categorieAModifier.setLibelle(request.getParameter("NouveauNomCategorie"));
				categorieManager.majCategorie(categorieAModifier);
				
//				permettre d'avoir accès à cet attribut depuis la JSP pour afficher message
//				de validation lors de la modification de la categorie
				request.setAttribute("categorieAModifier", categorieAModifier);
			} else if ("Supprimer".equals(actionCategorie)) {
//				Traitement pour supprimer la catégorie
				noCategorie = Integer.parseInt(request.getParameter("CategorieASupprimer"));
				
				if(noCategorie != null) {
					categorieASupprimer = categorieManager.selectCategorieParId(noCategorie);
					categorieASupprimer = categorieManager.supprimerCategorie(categorieASupprimer);
	//				permettre d'avoir accès à cet attribut depuis la JSP pour afficher message
	//				de validation lors de la suppression de la categorie
					
					request.setAttribute("categorieASupprimer", categorieASupprimer);
				}
			}
			
			if("Supprimer".equals(actionUtilisateur)) {
				noUtilisateur = Integer.parseInt(request.getParameter("choixUtilisateur"));
				
				if(noUtilisateur != null) {
					utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);

					utilisateurASupprimer = utilisateurManager.supprimerUtilisateur(utilisateur);
					request.setAttribute("utilisateurASupprimer", utilisateurASupprimer);
					
				}
			} else if ("ActiverDesactiver".equals(actionUtilisateur)){
				noUtilisateur = Integer.parseInt(request.getParameter("choixUtilisateur"));
				
				if(noUtilisateur != null) {
					utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
					
					utilisateurOnOff = utilisateurManager.userOnOff(utilisateur);
					request.setAttribute("utilisateurOnOff",utilisateurOnOff);
					EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();
					ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstanceArticle();
					List<ArticleVendu> listArtUtil = new ArrayList<ArticleVendu>();
					Enchere ench = null;
					listArtUtil = articleManager.articleEncherie(noUtilisateur);
					
					
					for (ArticleVendu articleVendu : listArtUtil) {
						ench = enchereManager.selectMaxEnchere(articleVendu);
						
						if (ench.getUtilisateur().getNoUtilisateur()==noUtilisateur) {
							utilisateur.setCredit(utilisateur.getCredit() + ench.getMontantEnchere());
							utilisateurManager.majMontantCredit(utilisateur);
							enchereManager.majEnchere(ench);
						}
					}
					
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

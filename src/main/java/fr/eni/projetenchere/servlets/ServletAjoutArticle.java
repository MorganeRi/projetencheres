package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
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
	private static CategorieManager CATEGORIE_MANAGER = CategorieManagerSing.getInstanceCategorieImpl();
	
	private static UtilisateurManager UTILISATEUR_MANAGER = UtilistateurManagerSing.getInstanceUtilisateur();
			
	private static ArticleVenduManager ARTICLE_VENDU_MANAGER = ArticleVenduManagerSing.getInstanceArticle();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutArticle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Integer idUtilisateur = (Integer) session.getAttribute("id");
        if (idUtilisateur == null) {
            // Rediriger vers la page de connexion
            response.sendRedirect("ServletConnexion");
            return;
        } else {
	//		récupérer la liste des catégories disponibles dans ma BDD
			List<Categorie> listCategorie = new ArrayList<>(); 
			System.out.println("coucou");
			try {
	//			System.out.println("coucou2");
				listCategorie = CATEGORIE_MANAGER.selectAllCategorie();
	//			System.out.println("coucou3");
				for(Categorie categorie : listCategorie) {
					System.out.println(categorie.toString());
				}
				
			request.setAttribute(LIST_CATEGORIE, listCategorie);
			} catch (BusinessException e1) {
				
				e1.printStackTrace();
			}
			
	//		gérer la récupération de session utilisateur
			Integer noUtilisateur;
			Utilisateur utilisateur = new Utilisateur();
	
			noUtilisateur = (Integer) request.getSession().getAttribute("id");
//			noUtilisateur = 1;
			System.out.println(noUtilisateur);
			try {
	//			
				utilisateur = UTILISATEUR_MANAGER.selectParNoUtilisateur(noUtilisateur);
				System.out.println(utilisateur.toString());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute(UTILISATEUR, utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
			rd.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = null;
		String description = null;
		Integer noCategorie = null;
		LocalDate dateDebutEnchere = null;
		LocalDate dateFinEnchere = null;
		Integer prixInitial = null;
		String rue = null;
		String codePostal = null;
		String nomVille = null;
		Categorie categorie = null;
		
		try {
			
			nomArticle = request.getParameter("nomArticle");
//			System.out.println(nomArticle);
			description = request.getParameter("Description");
//			System.out.println(description);
//			cast en integer 
			noCategorie = Integer.parseInt(request.getParameter("Categorie"));
//			System.out.println(noCategorie);
			dateDebutEnchere = LocalDate.parse(request.getParameter("DebutEnchere"));
//			System.out.println(dateDebutEnchere);
			dateFinEnchere = LocalDate.parse(request.getParameter("FinEnchere"));
//			System.out.println(dateFinEnchere);
			prixInitial = Integer.parseInt(request.getParameter("prixDepart"));
//			System.out.println(prixInitial);
			rue = request.getParameter("nomRue");
			codePostal = request.getParameter("codePostal");
			nomVille = request.getParameter("nomVille");
			
			Integer noUtilisateur;
			Utilisateur utilisateur = new Utilisateur();

			noUtilisateur = (Integer) request.getSession().getAttribute("id");
//			noUtilisateur = 2;
//			System.out.println(noUtilisateur);
			try {
				utilisateur = UTILISATEUR_MANAGER.selectParNoUtilisateur(noUtilisateur);
//				System.out.println(utilisateur.toString());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			categorie = CATEGORIE_MANAGER.selectCategorieParId(noCategorie);
//			System.out.println(categorie);
			ArticleVendu articleVendu = new ArticleVendu(nomArticle,description,dateDebutEnchere,
					dateFinEnchere,prixInitial,utilisateur,categorie);
//			System.out.println(articleVendu.toString());
			ARTICLE_VENDU_MANAGER.ajouterArticleVendu(articleVendu);
			request.setAttribute("articleAManipuler", articleVendu);
//			System.out.println("test");
			
//			permettre d'instancier un attribut dans la session pour le recuperer
//			dans une autre Servlet
			HttpSession session = request.getSession();
			session.setAttribute(ARTICLE_A_MANIPULER, articleVendu);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}

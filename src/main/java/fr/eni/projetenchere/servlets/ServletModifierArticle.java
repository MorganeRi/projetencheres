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
 * Servlet implementation class ServletModifierArticle
 */
@WebServlet("/ServletModifierArticle")
public class ServletModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CategorieManager CATEGORIE_MANAGER = CategorieManagerSing.getInstanceCategorieImpl();
	private static final String LIST_CATEGORIE = "listCategorie";
	private static UtilisateurManager UTILISATEUR_MANAGER = UtilistateurManagerSing.getInstanceUtilisateur();
	private static final String UTILISATEUR = "Utilisateur";
	private static ArticleVenduManager ARTICLE_VENDU_MANAGER = ArticleVenduManagerSing.getInstanceArticle();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModifierArticle() {
		super();
		// TODO Auto-generated constructor stub
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
		
			List<Categorie> listCategorie = new ArrayList<>(); 
			
			try {
				listCategorie = CATEGORIE_MANAGER.selectAllCategorie();
				request.setAttribute(LIST_CATEGORIE, listCategorie);
			} catch (BusinessException e1) {
				
				e1.printStackTrace();
			}
			
	//		gérer la récupération de session utilisateur
			Integer noUtilisateur = null;;
			Utilisateur utilisateur = new Utilisateur();
			
			noUtilisateur = (Integer) request.getSession().getAttribute("id");
//			noUtilisateur = 1;
//			System.out.println(noUtilisateur);
			try {
	//			
				utilisateur = UTILISATEUR_MANAGER.selectParNoUtilisateur(noUtilisateur);
				request.setAttribute(UTILISATEUR, utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
	//		récupérer les infos d'un article 
			Integer noArticle = null;
			ArticleVendu articleAAfficher = new ArticleVendu();
			System.out.println(articleAAfficher);
			
	//		TODO : Finir code pour récupérer l'id de l'article depuis la page de liste d'articles
			noArticle =23;
			
			try {
				articleAAfficher = ARTICLE_VENDU_MANAGER.selectParIdArticle(noArticle);
				request.setAttribute("articleAManipuler", articleAAfficher);
			} catch (Exception e) {
				e.printStackTrace();
			}

			session.setAttribute("articleAManipuler", articleAAfficher);

			
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
		
		ArticleVendu articleAManipuler = (ArticleVendu) session.getAttribute("articleAModifier");
		try {
			articleAManipuler.setNomArticle(request.getParameter("nomArticle"));
			articleAManipuler.setDescription(request.getParameter("Description"));
			
			Integer noCategorie = Integer.parseInt(request.getParameter("Categorie"));
			Categorie categorie = CATEGORIE_MANAGER.selectCategorieParId(noCategorie);
			articleAManipuler.setCategorie(categorie);
			articleAManipuler.setDateDebutEnchere(LocalDate.parse(request.getParameter("DebutEnchere")));
			articleAManipuler.setDateFinEnchere(LocalDate.parse(request.getParameter("FinEnchere")));
			articleAManipuler.setPrixInitial(Integer.parseInt(request.getParameter("prixDepart")));
			Integer noUtilisateur = null;
			Utilisateur utilisateur = new Utilisateur();
			noUtilisateur = (Integer) request.getSession().getAttribute("id");
			utilisateur = UTILISATEUR_MANAGER.selectParNoUtilisateur(noUtilisateur);
			articleAManipuler.setUtilisateur(utilisateur);

			String rue = null;
			String codePostal = null  ;
			String nomVille = null;
			
			rue = request.getParameter("nomRue");
			codePostal = request.getParameter("codePostal");
			nomVille = request.getParameter("nomVille");


			try {
				ARTICLE_VENDU_MANAGER.majArticleVendu(articleAManipuler);
				request.setAttribute("articleAManipuler", articleAManipuler);
			} catch (Exception e) {
				e.printStackTrace();
			}

//			permettre d'instancier un attribut dans la session pour le recuperer
//			dans une autre Servlet

			System.out.println(articleAManipuler);
			session.setAttribute("articleAManipuler", articleAManipuler.getNoArticle());

	        Integer article = (Integer) session.getAttribute("articleAManipuler");
			System.out.println("article modifier : " + article);

		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}
}

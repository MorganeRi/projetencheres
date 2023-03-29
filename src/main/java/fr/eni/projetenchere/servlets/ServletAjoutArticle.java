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

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.ArticleVenduManager;
import fr.eni.projetenchere.bll.ArticleVenduManagerImpl;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.CategorieManagerSing;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilisateurManagerImpl;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/ServletAjoutArticle")
public class ServletAjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CategorieManager categorieManager = CategorieManagerSing.getInstanceCategorieImpl();
       
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
		List<Categorie> listCategorie = new ArrayList<>();
		System.out.println("coucou");
		try {
//			System.out.println("coucou2");
			listCategorie = categorieManager.selectAllCategorie();
//			System.out.println("coucou3");
			for(Categorie categorie : listCategorie) {
//				System.out.println(categorie.toString());
			}
			
			request.setAttribute("listCategorie", listCategorie);
		} catch (BusinessException e1) {
			
			e1.printStackTrace();
		}
		
		Integer noUtilisateur;
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManager utilisateurManager = new UtilisateurManagerImpl();

//		noUtilisateur = (Integer) request.getSession().getAttribute("id");
		noUtilisateur = 2;
		System.out.println(noUtilisateur);
		try {
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
			System.out.println(utilisateur.toString());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("Utilisateur", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle;
		String description;
		Integer categorie;
		
		LocalDate dateDebutEnchere;
		LocalDate dateFinEnchere;
		Integer prixInitial;
		String rue;
		String codePostal;
		String nomVille;
		
		
		try {
			nomArticle = request.getParameter("nomArticle");
			description = request.getParameter("Description");
//			cast en integer 
			categorie = Integer.parseInt(request.getParameter("Categorie"));
			dateDebutEnchere = LocalDate.parse(request.getParameter("DebutEnchere"));
			dateFinEnchere = LocalDate.parse(request.getParameter("FinEnchere"));
			prixInitial = Integer.parseInt(request.getParameter("prixDepart"));
			rue = request.getParameter("nomRue");
			codePostal = request.getParameter("codePostal");
			nomVille = request.getParameter("nomVille");
			
			ArticleVenduManager articleManager = new ArticleVenduManagerImpl();
			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) request.getAttribute("Utilisateur");
//			ArticleVendu articleVendu = new ArticleVendu(nomArticle,description,dateDebutEnchere,
//					dateFinEnchere,prixInitial,utilisateur,categorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
		rd.forward(request, response);
		doGet(request, response);
	}

}

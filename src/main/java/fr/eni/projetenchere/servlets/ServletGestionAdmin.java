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

import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.CategorieManagerSing;
import fr.eni.projetenchere.bo.Categorie;

/**
 * Servlet implementation class ServletGestionAdmin
 */
@WebServlet("/ServletGestionAdmin")
public class ServletGestionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LIST_CATEGORIE = "listCategorie";
	private static CategorieManager categorieManager = CategorieManagerSing.getInstanceCategorieImpl();
       
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
		
		List<Categorie> listCategorie = new ArrayList<>();
		
		if(idUtilisateur == null) {
			// Rediriger vers la page de connexion
			response.sendRedirect("ServletConnexion");
		} else {
			try {
				listCategorie = categorieManager.selectAllCategorie();
				
				request.setAttribute(LIST_CATEGORIE, listCategorie);
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
		Categorie categorie,ancienneCategorie = null; 
		String nomCategorie,action = null;
		Integer noCategorie = null;
		
		
		try {
			action = request.getParameter("action");
			
			if("Ajout Categorie".equals(action)) {
//				Traitement pour ajouter une catégorie
				nomCategorie = request.getParameter("nomCategorie");
				
				categorie = new Categorie(nomCategorie);
//				System.out.println(categorie);
				
				categorieManager.ajouterCategorie(categorie);
//				permettre d'avoir accès à cet attribut depuis la JSP pour afficher message
//				de validation lors de l'insertion de la categorie
				request.setAttribute("categorieARajouter", categorie);
			} else if ("Modifier la catégorie".equals(action)) {
//				Traitement pour modifier une catégorie en recuperant son ancien libelle
				noCategorie = Integer.parseInt(request.getParameter("Categorie"));
				ancienneCategorie = categorieManager.selectCategorieParId(noCategorie);
				
				ancienneCategorie.setLibelle(request.getParameter("NouveauNomCategorie"));
				System.out.println(ancienneCategorie.toString());
				categorieManager.majCategorie(ancienneCategorie);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}

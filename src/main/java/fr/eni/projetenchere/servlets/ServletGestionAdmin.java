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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

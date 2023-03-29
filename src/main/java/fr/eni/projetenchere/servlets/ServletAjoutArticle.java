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
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.CategorieManagerSing;
import fr.eni.projetenchere.bo.Categorie;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> listCategorie = new ArrayList<>();
		System.out.println("coucou");
		try {
			System.out.println("coucou2");
			listCategorie = categorieManager.selectAllCategorie();
			System.out.println("coucou3");
			for(Categorie categorie : listCategorie) {
				System.out.println(categorie.toString());
			}
			
			request.setAttribute("listCategorie", listCategorie);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle;
		String description;
		
		
		
		
		
		LocalDate dateDebutEnchere;
		LocalDate dateFinEnchere;
		Integer prixInitial;
		
		
		try {
			nomArticle = request.getParameter("nomArticle");
			description = request.getParameter("Description");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AjoutArticle.jsp");
		rd.forward(request, response);
		
	}

}

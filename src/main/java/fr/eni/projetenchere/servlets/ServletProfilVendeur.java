package fr.eni.projetenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfilVendeur
 */
@WebServlet("/ServletProfilVendeur")
public class ServletProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfilVendeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer noUtilisateur;
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();

		noUtilisateur = (Integer) request.getSession().getAttribute("id");
		try {
			// TO DO : Changer id pour mettre id vendeur
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		request.setAttribute("Utilisateur", utilisateur);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ProfilVendeur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

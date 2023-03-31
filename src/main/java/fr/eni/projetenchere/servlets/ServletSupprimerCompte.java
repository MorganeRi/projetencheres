package fr.eni.projetenchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet("/ServletSupprimerCompte")
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSupprimerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		if (idUtilisateur == null) {
			// Rediriger vers la page de connexion
			response.sendRedirect("ServletConnexion");
			return;
		} else {

			UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
			Utilisateur util = new Utilisateur();
			try {
				util = utilisateurManager.selectParNoUtilisateur((Integer) request.getSession().getAttribute("id"));
				// util = utilisateurManager.selectParNoUtilisateur(2);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				utilisateurManager.supprimerUtilisateur(util);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().invalidate();
			response.sendRedirect("./ServletAccueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

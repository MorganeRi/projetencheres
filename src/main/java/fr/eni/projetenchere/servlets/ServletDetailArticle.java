package fr.eni.projetenchere.servlets;

import java.io.IOException;

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
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailArticle() {
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

		ArticleVendu art = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		Retrait retrait = new Retrait();
		ArticleVenduManager ArticleVenduManager = ArticleVenduManagerSing.getInstanceArticle();
		UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();

		Integer idArticle = Integer.parseInt(request.getParameter("idArticle"));


		try {
//TODO Modifier 
			art = ArticleVenduManager.selectParIdArticle(idArticle);
			utilisateur = utilisateurManager.selectParNoUtilisateur(art.getUtilisateur().getNoUtilisateur());
			retrait.setRue(utilisateur.getRue());
			retrait.setCodePostal(utilisateur.getCodePostal());
			retrait.setVille(utilisateur.getVille());
			
			art.setRetrait(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		request.setAttribute("article", art);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailArticle.jsp");
		rd.forward(request, response); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

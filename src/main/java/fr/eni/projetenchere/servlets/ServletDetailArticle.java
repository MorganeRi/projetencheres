package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.EnchereManagerSing;
import fr.eni.projetenchere.bll.RetraitManager;
import fr.eni.projetenchere.bll.RetraitManagerSing;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVendu art = new ArticleVendu();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailArticle() {
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

			ArticleVendu art = new ArticleVendu();
			Utilisateur utilisateur = new Utilisateur();
			Retrait retrait = new Retrait();
			Integer idArticle = null;
			ArticleVenduManager ArticleVenduManager = ArticleVenduManagerSing.getInstanceArticle();
			UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
			RetraitManager retMan = RetraitManagerSing.getInstanceRetraitImpl();

			if ((request.getParameter("idArticle")) != null) {
				idArticle = Integer.parseInt(request.getParameter("idArticle"));
				request.getSession().setAttribute("idArticle", idArticle);
			} else {
				idArticle = (Integer) request.getSession().getAttribute("idArticle");

			}
			try {

				art = ArticleVenduManager.selectParIdArticle(idArticle);
				utilisateur = utilisateurManager.selectParNoUtilisateur(art.getUtilisateur().getNoUtilisateur());
				// retrait = retMan.selectParIdRetrait(idArticle);

				retrait.setRue(utilisateur.getRue());
				retrait.setCodePostal(utilisateur.getCodePostal());
				retrait.setVille(utilisateur.getVille());

				art.setRetrait(retrait);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			request.setAttribute("article", art);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailArticle.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer montantEnchere = null;
		Integer noArticle;
		ArticleVendu article = null;
		Utilisateur utilisateur = null;
		Integer noUtilisateur;
		Enchere enchere;
		LocalDateTime dateEnchere = LocalDateTime.now();
		List<Integer> listeCodesErreur = new ArrayList<>();
		ArticleVenduManager articleManager = ArticleVenduManagerSing.getInstanceArticle();
		UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
		EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();

		try {
			String enchereString = request.getParameter("enchere");
			montantEnchere = Integer.parseInt(enchereString);
			noArticle = (Integer) (request.getSession().getAttribute("idArticle"));
			article = articleManager.selectParIdArticle(noArticle);
			HttpSession session = request.getSession();
			noUtilisateur = (Integer) session.getAttribute("id");
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
			enchere = new Enchere(dateEnchere, montantEnchere, article, utilisateur);
			enchereManager.insertEnchere(enchere);
			request.setAttribute("enchere", enchere);

		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.INSERT_ENCHERE_ERREUR);
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());

		}

		doGet(request, response);

	}
}
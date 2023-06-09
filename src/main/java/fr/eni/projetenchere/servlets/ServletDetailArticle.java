package fr.eni.projetenchere.servlets;

import java.io.IOException;
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

	private static UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();
	private static EnchereManager enchereManager = EnchereManagerSing.getInstanceEnchereImpl();
	private static ArticleVenduManager articleVenduManager = ArticleVenduManagerSing.getInstanceArticle();

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
			Enchere enchereMax = new Enchere();
			Retrait retrait = new Retrait();
			Integer idArticle = null;
			Utilisateur utilisateurActuelMax = null;

			if ((request.getParameter("idArticle")) != null) {
				idArticle = Integer.parseInt(request.getParameter("idArticle"));
				request.getSession().setAttribute("idArticle", idArticle);
			} else {
				idArticle = (Integer) request.getSession().getAttribute("idArticle");

			}
			try {

				art = articleVenduManager.selectParIdArticle(idArticle);
				utilisateur = utilisateurManager.selectParNoUtilisateur(art.getUtilisateur().getNoUtilisateur());
				// retrait = retMan.selectParIdRetrait(idArticle);
				if (enchereManager.selectMaxEnchere(art) != null
						&& enchereManager.selectMaxEnchere(art).getUtilisateur().getActif() == true) {
					enchereMax = enchereManager.selectMaxEnchere(art);
				} else {
					enchereMax.setMontantEnchere(0);
				}
				retrait.setRue(utilisateur.getRue());
				retrait.setCodePostal(utilisateur.getCodePostal());
				retrait.setVille(utilisateur.getVille());

				art.setRetrait(retrait);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			try {
				if (enchereManager.selectMaxEnchere(art) != null) {

					if (enchereManager.selectMaxEnchere(art).getUtilisateur().getActif() == false) {
						utilisateurActuelMax = enchereMax.getUtilisateur();
						request.setAttribute("utilisateurActuelMax", utilisateurActuelMax);
					} else {
						request.setAttribute("utilisateurActuelMax", null);
					}
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			request.setAttribute("article", art);
			request.setAttribute("enchereMax", enchereMax);

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
		Integer noArticle = null;
		ArticleVendu article = null;
		Utilisateur utilisateur = null;
		Integer noUtilisateur = null;
		Enchere enchere = null;
		Enchere enchereMax = null;
		Utilisateur utilisateurActuelMax = null;
		Integer creditUtilisateur = null;
		LocalDateTime dateDuJour = LocalDateTime.now();
		List<Integer> listeCodesErreur = new ArrayList<>();

		try {

			String enchereString = request.getParameter("enchere");
			montantEnchere = Integer.parseInt(enchereString);
			noArticle = (Integer) (request.getSession().getAttribute("idArticle"));
			article = articleVenduManager.selectParIdArticle(noArticle);
			HttpSession session = request.getSession();
			noUtilisateur = (Integer) session.getAttribute("id");
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
			enchere = new Enchere(dateDuJour, montantEnchere, article, utilisateur);
			LocalDateTime dateFinEnchere = article.getDateFinEnchere();

			enchereMax = enchereManager.selectMaxEnchere(article);
			if (enchereMax != null && enchereMax.getMontantEnchere() == null) {
				enchereMax.setMontantEnchere(0);
			}

			creditUtilisateur = utilisateur.getCredit();
			if (creditUtilisateur >= montantEnchere) {

				if (enchereMax != null) {
					utilisateurActuelMax = enchereMax.getUtilisateur();
					utilisateurActuelMax.setCredit(utilisateurActuelMax.getCredit() + enchereMax.getMontantEnchere());
					utilisateurManager.majMontantCredit(utilisateurActuelMax);
				}

				enchereManager.insertEnchere(enchere);
				request.setAttribute("enchere", enchere);

				utilisateur.setCredit(creditUtilisateur - montantEnchere);
				utilisateurManager.majMontantCredit(utilisateur);

				// La date de fin de l'enchère est arrivée il faut donc déterminer qui est
				// l'acquéreur et quel est le prix de vente final

				if (dateFinEnchere.isAfter(dateDuJour)) {
					article.setNoAcquereur(
							enchereManager.selectMaxEnchere(article).getUtilisateur().getNoUtilisateur());
					article.setPrixDeVente(enchereManager.selectMaxEnchere(article).getMontantEnchere());
					articleVenduManager.majPxVenteArticleVendu(article);
					articleVenduManager.majNoAcquereur(article);
				}

			} else {
				listeCodesErreur.add(CodesResultatServlets.CREDIT_INSUFFISANT);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}

		} catch (

		BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.INSERT_ENCHERE_ERREUR);
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());

		}

		doGet(request, response);

	}
}
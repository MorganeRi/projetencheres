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

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjoutUtilisateur
 */
@WebServlet("/ServletAjoutUtilisateur")
public class ServletAjoutUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_UTILISATEUR_MAIL = "mail";
	private static final String SESSION_UTILISATEUR_ID = "id";
	private static final String SESSION_UTILISATEUR_PSEUDO = "pseudo";
	private static final String SESSION_UTILISATEUR_ADMIN = "admin";
	private static final String SESSION_UTILISATEUR_ACTIF = "actif";

	private static UtilisateurManager utilisateurManager = UtilistateurManagerSing.getInstanceUtilisateur();

	public ServletAjoutUtilisateur() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		if (idUtilisateur != null) {
			// Rediriger vers la page de connexion
			response.sendRedirect("./Profil");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreationUtilisateur.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pseudo = null;
		String prenom = null;
		String nom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String motDePasse = null;
		String confirmationMotDePasse = null;
		Integer credit = 100;
		Boolean administrateur = false;
		Boolean actif = true;
		List<Integer> listeCodesErreur = new ArrayList<>();

		try {
			pseudo = request.getParameter("Pseudo");
			prenom = request.getParameter("Prenom");
			nom = request.getParameter("Nom");
			email = request.getParameter("Email");
			telephone = request.getParameter("Telephone");
			rue = request.getParameter("Rue");
			codePostal = request.getParameter("CodePostal");
			ville = request.getParameter("Ville");
			motDePasse = request.getParameter("MotDePasse");
			confirmationMotDePasse = request.getParameter("ConfirmationMotDePasse");

			if (motDePasse.equals(confirmationMotDePasse)
					&& (utilisateurManager.selectParEmailUtilisateur(email).getNoUtilisateur() == null)
					&& (utilisateurManager.selectParPseudoUtilisateur(pseudo).getNoUtilisateur() == null)) {

				Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
						motDePasse, credit, administrateur);
				utilisateur.setActif(actif);
				utilisateurManager.createUtilisateur(utilisateur);
				request.setAttribute("utilisateur", utilisateur);
				HttpSession session = request.getSession();
				session.setAttribute(SESSION_UTILISATEUR_ID, utilisateur.getNoUtilisateur());
				session.setAttribute(SESSION_UTILISATEUR_MAIL, email);
				session.setAttribute(SESSION_UTILISATEUR_PSEUDO, pseudo);
				session.setAttribute(SESSION_UTILISATEUR_ADMIN, utilisateur.getAdministrateur());
				session.setAttribute(SESSION_UTILISATEUR_ACTIF, utilisateur.getActif());

			} else {

				if (utilisateurManager.selectParEmailUtilisateur(email).getNoUtilisateur() != null) {
					listeCodesErreur.add(CodesResultatServlets.MAIL_DOUBLON_ERREUR);
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
				if (utilisateurManager.selectParPseudoUtilisateur(pseudo).getNoUtilisateur() != null) {
					listeCodesErreur.add(CodesResultatServlets.PSEUDO_DOUBLON_ERREUR);
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			}
			if (!motDePasse.equals(confirmationMotDePasse)) {
				listeCodesErreur.add(CodesResultatServlets.MOTDEPASSE_ERREUR);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

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
import fr.eni.projetenchere.bll.UtilisateurManagerImpl;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierUtilisateur
 */
@WebServlet("/ServletModifierUtilisateur")
public class ServletModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModifierUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer noUtilisateur;
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManager utilisateurManager = new UtilisateurManagerImpl();

		noUtilisateur = (Integer) request.getSession().getAttribute("id");
		//noUtilisateur = 2;
		try {
			utilisateur = utilisateurManager.selectParNoUtilisateur(noUtilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("Utilisateur", utilisateur);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id;
		String pseudo;
		String prenom;
		String nom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasseAtcuel;
		String nouveauMotDePasse;
		String confirmationMotDePasse;
		Integer credit;
		Boolean administrateur;

		BusinessException businessException = new BusinessException();
		UtilisateurManager utilisateurManager = new UtilisateurManagerImpl();
		Utilisateur util = new Utilisateur();

		try {
			util = utilisateurManager.selectParNoUtilisateur((Integer) request.getSession().getAttribute("id"));
			//util = utilisateurManager.selectParNoUtilisateur(2);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			id = (Integer) request.getSession().getAttribute("id");
			//id = 2;
			pseudo = request.getParameter("Pseudo");
			prenom = request.getParameter("Prenom");
			nom = request.getParameter("Nom");
			email = request.getParameter("Email");
			telephone = request.getParameter("Telephone");
			rue = request.getParameter("Rue");
			codePostal = request.getParameter("CodePostal");
			ville = request.getParameter("Ville");
			motDePasseAtcuel = request.getParameter("MotDePasseActuel");
			nouveauMotDePasse = request.getParameter("MotDePasseNouveau");
			confirmationMotDePasse = request.getParameter("ConfirmationMotDePasse");
			credit = util.getCredit();
			administrateur = util.getAdministrateur();
			if (motDePasseAtcuel.equals(util.getMotDePasse())) {
				if (nouveauMotDePasse.equals(confirmationMotDePasse)) {
					Utilisateur utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue,
							codePostal, ville, nouveauMotDePasse, credit, administrateur);
					utilisateurManager.majUtilisateur(utilisateur);
				} else {
					businessException.ajouterErreur(CodesResultatServlets.MOTDEPASSE_ERREUR);
				}
			} else {
				businessException.ajouterErreur(CodesResultatServlets.MOTDEPASSE_ERREUR);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}

		doGet(request, response);
	}
}

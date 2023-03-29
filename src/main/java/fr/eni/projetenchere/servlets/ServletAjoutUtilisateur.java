package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletAjoutUtilisateur
 */
@WebServlet("/ServletAjoutUtilisateur")
public class ServletAjoutUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAjoutUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo;
		String prenom;
		String nom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		String confirmationMotDePasse;

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
			
			UtilisateurManager utilisateurManager = new UtilisateurManagerImpl();
			
			Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse);
			utilisateurManager.createUtilisateur(utilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}

		doGet(request, response);
	}

}

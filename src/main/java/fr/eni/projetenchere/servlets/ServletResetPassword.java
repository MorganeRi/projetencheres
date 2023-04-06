package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;

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
 * Servlet implementation class ServletResetPassword
 */
@WebServlet("/ServletResetPassword")
public class ServletResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager UTILMAN = UtilistateurManagerSing.getInstanceUtilisateur();
	Utilisateur utilMail = new Utilisateur();
	Utilisateur utilPseudo = new Utilisateur();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Récupération des données du formulaire de demande de ré-initialisation de mot
		// de passe
		String email = request.getParameter("email");
		System.out.println(email);
		// Vérification de l'existence de l'utilisateur dans la base de données
		boolean userExists = checkUserExists(email);

		if (userExists) {

			// Génération d'un token unique pour la ré-initialisation de mot de passe
			String resetToken = generateResetToken();

			// Stockage du token dans la base de données
			storeResetToken(email, resetToken);

			// Envoi d'un e-mail contenant le lien de ré-initialisation de mot de passe à
			// l'utilisateur
			sendResetPasswordEmail(email, resetToken);

			// Affichage d'un message de confirmation à l'utilisateur
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<p>Votre demande de ré-initialisation de mot de passe a été prise en compte.</p>");
			out.println("<p>Un e-mail contenant un lien de ré-initialisation de mot de passe vous a été envoyé.</p>");
			out.println("</body></html>");
		} else {
			// Affichage d'un message d'erreur à l'utilisateur si l'e-mail n'existe pas dans
			// la base de données
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<p>L'e-mail que vous avez saisi n'existe pas dans notre système.</p>");
			out.println("</body></html>");
		}
	}

	private boolean checkUserExists(String email) {
		// Vérification de l'existence de l'utilisateur dans la base de données

		try {
			utilMail = UTILMAN.selectParEmailUtilisateur(email);
			System.out.println(utilMail);
			utilPseudo = UTILMAN.selectParPseudoUtilisateur(email);
			System.out.println(utilPseudo);
		} catch (BusinessException e) {

			e.printStackTrace();
		}

		if (utilMail.getNoUtilisateur() == null && utilPseudo.getNoUtilisateur() == null) {
			return false;
		} else {

			return true;
		}
	}

	private String generateResetToken() {
		// Génération d'un token unique pour la ré-initialisation de mot de passe
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int tokenLength = 20;
		Random rand = new SecureRandom();
		StringBuilder sb = new StringBuilder(tokenLength);

		for (int i = 0; i < tokenLength; i++) {
			sb.append(characters.charAt(rand.nextInt(characters.length())));
		}

		return sb.toString();

	}

	private void storeResetToken(String email, String resetToken) {
		// Stockage du token dans la base de données
		// Code à implémenter
	}

	private void sendResetPasswordEmail(String email, String resetToken) {
		// Envoi d'un e-mail contenant le lien de ré-initialisation de mot de passe à
		// l'utilisateur
		// Code à implémenter
	}
}

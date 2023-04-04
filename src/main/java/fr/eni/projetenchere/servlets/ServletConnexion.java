package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.bll.UtilistateurManagerSing;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.messages.LecteurMessage;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_UTILISATEUR_MAIL = "mail";
	private static final String SESSION_UTILISATEUR_ID = "id";
	private static final String SESSION_UTILISATEUR_PSEUDO = "pseudo";
	private static final String SESSION_UTILISATEUR_ADMIN = "admin";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String champ;
		String mdp;
		Utilisateur util =null;
		String mail=null;
		String pseudo=null;
		String rememberMe=null;

		UtilisateurManager utilMan = UtilistateurManagerSing.getInstanceUtilisateur();
		try
		{
	

			rememberMe=request.getParameter("rememberMe");
			champ = request.getParameter("email");
			mdp = request.getParameter("mdp");

			
			//Vérifier si c'est un pseudo ou un mail
			if (champ.contains("@")) {
				mail=champ;
				util=new Utilisateur(mail, mdp);
				utilMan.authentifierUtilisateurMail(util);
				request.setAttribute("Utilisateur",util);
				souvenirCookie(rememberMe,util, request, response);

			} else {
				pseudo=champ;
				util=new Utilisateur();
				util.setPseudo(pseudo);
				util.setMotDePasse(mdp);
				utilMan.authentifierUtilisateurPseudo(util);
				request.setAttribute("Utilisateur",util);
				souvenirCookie(rememberMe,util, request, response);

			}
			
			
			
			
		} catch (BusinessException e) {

			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());

			// se substituer à la problématique d'appel statique depuis l'EL
			request.setAttribute("listeMessagesErreur", 
					e.getListeCodesErreur()
						.stream()
						.map(x -> LecteurMessage.getMessageErreur(x))
						.collect(Collectors.toList())
			);
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			rd.forward(request, response);	
			return;
		}
		
		HttpSession session = request.getSession();
        session.setAttribute(SESSION_UTILISATEUR_ID,util.getNoUtilisateur() );
        session.setAttribute(SESSION_UTILISATEUR_MAIL, util.getEmail());
        session.setAttribute(SESSION_UTILISATEUR_PSEUDO, util.getPseudo());
        session.setAttribute(SESSION_UTILISATEUR_ADMIN, util.getAdministrateur());

		
		response.sendRedirect("./Accueil");
	}
	
	
	// Methode qui cree un cookie valable 5 min si l'utilisateur a cocher la case "se souvenir de moi"
	private void souvenirCookie(String rememberMe,Utilisateur util, HttpServletRequest request, HttpServletResponse response) {

		
		if (rememberMe != null && rememberMe.equals("on")) {
			Cookie cookie = new Cookie("username", util.getPseudo());
		    System.out.println("je suis un cookie");
		      // Définit la durée de validité du cookie à 24 heures
		      cookie.setMaxAge(60*60*24);
		    
		      // Ajoute le cookie à la réponse HTTP
		      response.addCookie(cookie);
			
		}
		
	}
	
	}



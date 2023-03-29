package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bll.UtilisateurManager;
import fr.eni.projetenchere.messages.LecteurMessage;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_UTILISATEUR_PSEUDO = "pseudo";
	private static final String SESSION_UTILISATEUR_ID = "id";
       
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modules/module6/demonstrations/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pseudo;
		String mdp;
		try
		{
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			
			UtilisateurManager utilisateurManager = new UtilisateurManagerSing();
			utilisateurManager.authentifierUtilisateur(pseudo, mdp); // déclenche une BusinessException si les coordonnées utilisateur sont erronées
			
		} catch (BusinessException e) {

			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());

			// se substituer à la problématique d'appel statique depuis l'EL
			request.setAttribute("listeMessagesErreur", 
					e.getListeCodesErreur()
						.stream()
						.map(x -> LecteurMessage.getMessageErreur(x))
						.collect(Collectors.toList())
			);
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modules/module6/demonstrations/connexion.jsp");
			rd.forward(request, response);	
			return;
		}
		
		HttpSession session = request.getSession();
        session.setAttribute(SESSION_UTILISATEUR_ID, pseudo);
        session.setAttribute(SESSION_UTILISATEUR_PSEUDO, pseudo);
		
		response.sendRedirect("./ServletAccueil");
	}
	}



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
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.UtilisateurDAO;
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
		String mail;
		String mdp;
		Utilisateur util =null;
		try
		{
	
			
			mail = request.getParameter("email");
			
			mdp = request.getParameter("mdp");
			util=new Utilisateur(mail, mdp);
			UtilisateurDAO utilDAO = DAOFactory.getUtilisateurDAO();
			
			utilDAO.connectUtilisateur(util);
			request.setAttribute("Utilisateur",util);
			
//			UtilisateurManager utilisateurManager = new UtilisateurManager();
//			utilisateurManager.authentifier(pseudo, mdp); // déclenche une BusinessException si les coordonnées utilisateur sont erronées
			
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
        session.setAttribute(SESSION_UTILISATEUR_MAIL, mail);
        session.setAttribute(SESSION_UTILISATEUR_PSEUDO, mail);
		
		response.sendRedirect("./ServletConnexion");
	}
	}



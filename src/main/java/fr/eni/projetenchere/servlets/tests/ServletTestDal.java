package fr.eni.projetenchere.servlets.tests;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.ArticleVenduDAO;
import fr.eni.projetenchere.dal.ArticleVenduDAOJdbcImpl;
import fr.eni.projetenchere.dal.EnchereDAO;
import fr.eni.projetenchere.dal.EnchereDAOJdbcImpl;
import fr.eni.projetenchere.dal.UtilisateurDAO;
import fr.eni.projetenchere.dal.UtilisateurDAOJdbcImpl;



/**
 * Servlet implementation class ServletTestDal
 */
@WebServlet("/ServletTestDal")
public class ServletTestDal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestDal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		Utilisateur utilisateur = new Utilisateur ("Momo","Richou","Morgane", "jesuismomo51354536@gmail.com", "0666666666", "rue de Lionel Richou", "66666", "LA", "coucouCmoi",100,false);
		
		UtilisateurDAO utilDAO = new UtilisateurDAOJdbcImpl();
		
		try {
			utilDAO.insertUtilisateur(utilisateur);
			utilisateur.setAdministrateur(true);
			utilDAO.updateUtilisateur(utilisateur);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Categorie cat = new Categorie(1, "multimedia");
		
		ArticleVendu a1 = new ArticleVendu("banane", "banane",LocalDate.of(2023, 12, 12),LocalDate.of(2023, 12, 12),12, utilisateur, cat );
		
		ArticleVenduDAO dao = new ArticleVenduDAOJdbcImpl();
		
		try {
			dao.insertArticleVendu(a1);
			a1.setNomArticle("prout");
			dao.updateArticleVendu(a1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Enchere enchere = new Enchere(LocalDate.of(2023, 12, 12), 100, a1, utilisateur);
		
		EnchereDAO enchDAO = new EnchereDAOJdbcImpl();
		
		try {
			enchDAO.insertEnchere(enchere);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			response.getWriter().append("Insertion de mon utilisateur dont l'iD est : ").append(utilisateur.getNoUtilisateur()+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.getWriter().append("Insertion de mon article dont l'utilisateur dont l'iD est : ").append(a1.getNomArticle()+" "+utilisateur.getNoUtilisateur()+"\n");
			response.getWriter().append("Insertion d'une enchere : ").append(enchere.getNoEnchere()+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		try {
//			List<Liste> listListe = dao.getAllNameList();
//			for(Liste s : listListe) {
//				System.out.println(s.toString());
//			}
//			
//			Liste articleByList = dao.getAllArticleByList(r.getIdListe());
//				System.out.println(articleByList);
//				for(Article s : articleByList.getListeArticles()) {
//					System.out.println(s);
//				}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			dao.deleteArticle(a3.getIdArticle());
//			System.out.println("l'article a bien été supprimé");
//			dao.deleteListe(r3.getIdListe());
//			System.out.println("La liste a bien été supprimée");
//			dao.cocherArticle(a2.getIdArticle());
//			System.out.println("l'article a bien été coché");
//			dao.cocherArticle(a1.getIdArticle());
//			dao.decocherArticle(a1.getIdArticle());
//			System.out.println("l'article a bien été décoché");
//			String nomListe = dao.selectListName(numR);
//			System.out.println(nomListe);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
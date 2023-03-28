package fr.eni.projetenchere.servlets.tests;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Liste r = new Liste("Liste fruits");
//		Liste r2 = new Liste("Liste legumes");
//		Liste r3 = new Liste("Liste feculents");
//		
//		ListeDAOImpl dao = new ListeDAOImpl();
//		
//		try {
//			dao.createList(r);
//			dao.createList(r2);
//			dao.createList(r3);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		Integer numR = r.getIdListe();
//		Integer numR2 = r2.getIdListe();
//		Integer numR3 = r3.getIdListe();
//		
//		Article a1 = new Article("banane");
//		Article a2 = new Article("courgette");
//		Article a3 = new Article("pates");
//	
//		try {
//			dao.insertArticle(a1, numR);
//			dao.insertArticle(a2, numR2);
//			dao.insertArticle(a3, numR3);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		response.getWriter().append("Insertion de mon repas dont l'iD est : ").append(r.getIdListe()+"\n");
//		response.getWriter().append("Insertion de mon article dans le repas dont l'iD est : ").append(a1.getNomArticle()+" "+r.getIdListe()+"\n");
//		response.getWriter().append("Insertion de mon article dans le repas dont l'iD est : ").append(a2.getNomArticle()+" "+r2.getIdListe()+"\n");
//		response.getWriter().append("Insertion de mon article dans le repas dont l'iD est : ").append(a3.getNomArticle()+" "+r3.getIdListe()+"\n");
//		
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
////			String nomListe = dao.selectListName(numR);
////			System.out.println(nomListe);
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

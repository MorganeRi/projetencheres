package fr.eni.projetenchere.servlets.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.CategorieDAO;
import fr.eni.projetenchere.dal.CategorieDAOJdbcImpl;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {




//		Utilisateur utilisateur = new Utilisateur("Momo", "Richou", "Morgane", "dfde.com", "0666666666","rue de Lionel Richou", "66666", "LA", "coucouCmoi", 100, false);
//
//		UtilisateurDAO utilDAO = new UtilisateurDAOJdbcImpl();
//
//		try {
//			utilDAO.insertUtilisateur(utilisateur);
//			utilisateur.setCredit(56435454);
//			utilDAO.updateMontantCredit(utilisateur);
//
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Categorie cat = new Categorie(2, "multimedia");
//
//		ArticleVendu a1 = new ArticleVendu("banane", "banane", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12, 12),
//				12, utilisateur, cat);
//
//		ArticleVenduDAO dao = new ArticleVenduDAOJdbcImpl();
//
//		try {
//			dao.insertArticleVendu(a1);
//			a1.setPrixDeVente(100);
//			dao.updatePxVenteArticleVendu(a1);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Enchere enchere = new Enchere(LocalDateTime.of(2023, 12, 12,12,12), 180, a1, utilisateur);
//
//		EnchereDAO enchDAO = new EnchereDAOJdbcImpl();
//
//		try {
//			enchDAO.insertEnchere(enchere);
//		} catch (BusinessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		try {
//			response.getWriter().append("Insertion de mon utilisateur dont l'iD est : ")
//					.append(utilisateur.getNoUtilisateur() + "\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			
//			response.getWriter().append("Insertion de mon article dont l'utilisateur dont l'iD est : ")
//					.append(a1.getNomArticle() + " " + utilisateur.getNoUtilisateur() + "\n");
//			response.getWriter().append("Insertion d'une enchere : ").append(enchere.getNoEnchere() + "\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			Utilisateur ut = new Utilisateur();
//			ut = utilDAO.selectByIdUtilisateur(5);
//			List<ArticleVendu> nart = new ArrayList<ArticleVendu>();
//			nart= dao.selectByNomArticle("ane");
//			List<Enchere> ench = new ArrayList<Enchere>();
//			ench = enchDAO.selectEnchereByIdArticle(a1);
//			System.out.println(ut.toString());
//			System.out.println(nart.toString());
//			System.out.println(ench.toString());
//			Enchere en = ench.get(0);
//			en= enchDAO.selectMaxEnchere(a1);
//			System.out.println("max"+en.toString());
//			List<Categorie> cate = new ArrayList<Categorie>();
//			CategorieDAO cateDao = new CategorieDAOJdbcImpl();
//			cate = cateDao.selectAllCategorie();
//			System.out.println(cate.toString());
//			Retrait retrait = new Retrait("rue de Lionel Richou", "66666", "LA", a1);
//			RetraitDAO retDAO = new RetraitDAOJdbcImpl();
//			retDAO.insertRetrait(retrait);
//			retrait.setCodePostal("55555");
//			retDAO.UpdateRetrait(retrait);
//			retrait=retDAO.selectByIdRetrait(a1.getNoArticle());
//			
//			System.out.println(retrait.toString());
//			
//			Categorie categorie ;
//			categorie = cateDao.selectCategorieByID(2);
//			System.out.println(categorie);
//
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			for (Integer code : e.getListeCodesErreur()) {
//				System.err.println("Code erreur rencontré DAL : " + code);
//			}
//		}
//		Categorie categorie = new Categorie("alimentaire");
//		System.out.println(categorie);
//		
//		CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();
//		
//		
//		try {
//			categorieDAO.insertCategorie(categorie);
//			System.out.println("bien insérée");
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<Utilisateur> listUtilisateur = new ArrayList<>();
		UtilisateurDAO utilisateurDao = new UtilisateurDAOJdbcImpl();
		
		try {
			listUtilisateur = utilisateurDao.selectAllUtilisateur();
			System.out.println(listUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

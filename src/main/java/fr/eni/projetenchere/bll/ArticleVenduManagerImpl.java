package fr.eni.projetenchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;

import fr.eni.projetenchere.dal.ArticleVenduDAO;
import fr.eni.projetenchere.dal.DAOFactory;

public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private ArticleVenduDAO dao = DAOFactory.getArticleVenduDAO();

	@Override
	public ArticleVendu ajouterArticleVendu(ArticleVendu article) throws BusinessException {

//		BusinessException businessException = new BusinessException();
//		if (!businessException.hasErreurs()) {
		dao.insertArticleVendu(article);
		return article;
//		} else {
//			throw businessException;
//		}

	}

	@Override
	public void majArticleVendu(ArticleVendu article) throws BusinessException {
		
		dao.updateArticleVendu(article);

	}

	@Override
	public void supprimerArticleVendu(ArticleVendu article) throws BusinessException {
		
		dao.deleteArticleVendu(article);

	}

	@Override
	public List<ArticleVendu> selectParCategorieArticle(String LibCat) throws BusinessException {
		return dao.selectByCategorieArticle(LibCat);
	}

	@Override
	public List<ArticleVendu> selectParNomArticle(String nom) throws BusinessException {

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectByNomArticle(nom);

		
		return articles;
	}

	@Override
	public void majPxVenteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArticleVendu selectParIdArticle(Integer id) throws BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		
		articleVendu = dao.selectByIdArticle(id);
		
		return articleVendu;
	}

	@Override

	public List<ArticleVendu> selectToutArticle() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectAllArticle();

		
		return articles;

	}
	
	
	@Override
	public List<ArticleVendu> selectParNomArticleParCat(String nom, String lib) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectByNomArticleByCat(nom, lib);

		return articles;
	}

	@Override
	public List<ArticleVendu> selectParNomArticleParCatSaufUtil(String nom, Integer idArticle, Integer idUtilisateur)
			throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectByNomArticleByCatSaufUtil(nom, idArticle, idUtilisateur);

		return articles;
	}

	@Override
	public List<ArticleVendu> selectParNomArticleParCatParUtil(String nom, Integer idArticle, Integer idUtilisateur)
			throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectByNomArticleByCatByUtil(nom, idArticle, idUtilisateur);

		return articles;                  
	}

	@Override
	public List<ArticleVendu> affichageArticlesEnVente(Integer idUtil) throws BusinessException {

		return dao.affichageArticlesEnVente(idUtil);
	}

	@Override
	public List<ArticleVendu> ventesEnCours(Integer idUtil) throws BusinessException {
		// TODO Auto-generated method stub
		return dao.ventesEnCours(idUtil);
	}
	@Override
	public List<ArticleVendu> ventesNonDebutees(Integer idUtil) throws BusinessException {
		// TODO Auto-generated method stub
		return dao.ventesNonDebutees(idUtil);
	}

	@Override
	public List<ArticleVendu> ventesTerminees(Integer idUtil) throws BusinessException {
		// TODO Auto-generated method stub
		return dao.ventesTerminees(idUtil);
	}


}

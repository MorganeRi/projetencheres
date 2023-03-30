package fr.eni.projetenchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.dal.ArticleVenduDAO;
import fr.eni.projetenchere.dal.DAOFactory;

public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private ArticleVenduDAO dao = DAOFactory.getArticleVenduDAO();

	@Override
	public ArticleVendu ajouterArticleVendu(ArticleVendu article) throws BusinessException {

//		BusinessException businessException = new BusinessException();
//		if (!businessException.hasErreurs()) {
		ArticleVendu nouvelArticle = new ArticleVendu();
		nouvelArticle = dao.insertArticleVendu(article);
		return nouvelArticle;
//		} else {
//			throw businessException;
//		}

	}

	@Override
	public void majArticleVendu(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerArticleVendu(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ArticleVendu> selectParCategorieArticle(Categorie categorie) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public List<ArticleVendu> selectToutArticle() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		articles=dao.selectAllArticle();

		
		return articles;

	}

}

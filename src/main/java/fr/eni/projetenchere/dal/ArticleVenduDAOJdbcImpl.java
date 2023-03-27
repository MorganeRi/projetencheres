package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String UPDATE_ARTICLE = "update article_vendu set nom_article=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_initial=? where no_article =?";
	private static final String INSERT_ARTICLE =  "insert into article_vendu(nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?)";
	private static final String DELETE_ARTICLE = "delete from article_vendu where no_article=?";
	private static final String SELECT_BY_CATEGORIE ="select no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_de_vente, no_utilisateur, no_categorie from article_vendu where no_categorie=?";
	private static final String SELECT_BY_NOM="select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, no_categorie from article_vendu where nom_article='%?%'";
	
	
	
	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVendu> selectByCategorieArticle(Categorie categorie) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> selectByNomArticle(String nom) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

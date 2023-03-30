package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;

public interface ArticleVenduDAO {

	// Mise à jour d'un article
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException;

	// Insérer un nouveal article
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException;

	// supprimer un article
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException;

	// Séléction par catégorie
	public List<ArticleVendu> selectByCategorieArticle(Categorie categorie) throws BusinessException;

	// Séléction par nom
	public List<ArticleVendu> selectByNomArticle(String nom) throws BusinessException;
	
	//Modification du prix de vente
	public void updatePxVenteArticleVendu (ArticleVendu articleVendu) throws BusinessException;
	
	//Sélection par ID
	public ArticleVendu selectByIdArticle (Integer id) throws BusinessException;
	
	//Select All
	public List<ArticleVendu> selectAllArticle() throws BusinessException;
	

}

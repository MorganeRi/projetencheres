package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;

public interface ArticleVenduManager {
	
	/* Contraintes : 
	 * - Appeller la méthode selectUtilisateur pour récupérer adresse puis Appeler la méthode insertRetrait pour l'ajouter
	 *  */
	public ArticleVendu ajouterArticleVendu (ArticleVendu article)throws BusinessException;
	
	// Contrainte : on peut faire une MAJ que si la date de début de l'enchère n'a pas encore débuté
	public void majArticleVendu (ArticleVendu article)throws BusinessException;
	
	public void supprimerArticleVendu (ArticleVendu article)throws BusinessException;
	
	public List<ArticleVendu> selectParCategorieArticle (String LibCat)throws BusinessException;
	
	public List<ArticleVendu> selectParNomArticle (String nom)throws BusinessException;

	//On stock ici le prix final, cad la plus haute enchère une fois la date de fin d'enchère atteinte
	public void majPxVenteArticleVendu (ArticleVendu articleVendu)throws BusinessException;
	
	public ArticleVendu selectParIdArticle (Integer id)throws BusinessException;
	
	public List<ArticleVendu> selectToutArticle ()throws BusinessException;
	
	public List<ArticleVendu> selectParNomArticleParCat(String nom, String lib) throws BusinessException;
	
	//Selection des articles SAUF ceux de l'uilisateur connecté
	
	public List<ArticleVendu> selectParNomArticleParCatSaufUtil (String nom,Integer idArticle, Integer idUtilisateur) throws BusinessException;
	
	//Selection des articles QUE ceux de l'uilisateur connecté
	
	public List<ArticleVendu> selectParNomArticleParCatParUtil (String nom,Integer idArticle, Integer idUtilisateur) throws BusinessException;
	
	public List<ArticleVendu> affichageArticlesEnVente(Integer idUtil) throws BusinessException;
	
	public List<ArticleVendu> ventesEnCours(Integer idUtil) throws BusinessException;
	
	public List<ArticleVendu> ventesNonDebutees(Integer idUtil) throws BusinessException;
	
	public List<ArticleVendu> ventesTerminees(Integer idUtil) throws BusinessException;
	
	public List<ArticleVendu> articleEncherie(Integer idUtil) throws BusinessException;
	
	public List<ArticleVendu> articleEnchereRemporte(Integer idUtil) throws BusinessException;
	
}

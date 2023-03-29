package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere) throws BusinessException;
	
	
	public List<Enchere> selectEnchereByIdArticle(ArticleVendu articleVendu) throws BusinessException;
	
	public Enchere selectMaxEnchere (ArticleVendu articleVendu) throws BusinessException;
	
	public List<Enchere> selectEnchereByIdUtilisateur(ArticleVendu articleVendu) throws BusinessException;
	
	public List<Enchere> selectAllEnchereGagneeByIdU (Utilisateur util) throws BusinessException;
	
	public void updateEnchereGagnante (Enchere enchere) throws BusinessException;
	
}

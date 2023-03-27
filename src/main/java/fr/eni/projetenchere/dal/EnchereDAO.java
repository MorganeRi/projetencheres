package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere) throws BusinessException;
	
	
	public Enchere selectById(ArticleVendu articleVendu) throws BusinessException;

}

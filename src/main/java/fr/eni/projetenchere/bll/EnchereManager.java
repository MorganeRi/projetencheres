package fr.eni.projetenchere.bll;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;

public interface EnchereManager {

	
	public void insertEnchere(Enchere enchere) throws BusinessException;

	public List<Enchere> selectEnchereById(ArticleVendu articleVendu) throws BusinessException;

	public Enchere selectMaxEnchere(ArticleVendu articleVendu) throws BusinessException;
	
	public Boolean finEnchere (ArticleVendu articleVendu) throws BusinessException;
	
	public void suppEnchere (ArticleVendu art) throws BusinessException;
	
	public void majEnchere (Enchere ench) throws BusinessException;
	

}

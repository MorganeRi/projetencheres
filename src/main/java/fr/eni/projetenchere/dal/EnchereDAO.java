package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere) throws BusinessException;
	
	
	public List<Enchere> selectEnchereById(ArticleVendu articleVendu) throws BusinessException;
	
	public Enchere selectMaxEnchere (ArticleVendu articleVendu) throws BusinessException;
	
}

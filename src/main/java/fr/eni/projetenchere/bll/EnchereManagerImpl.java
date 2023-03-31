package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.EnchereDAO;

public class EnchereManagerImpl implements EnchereManager {
	private EnchereDAO enchereDao;

	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		this.enchereDao.insertEnchere(enchere);
		
	}

	@Override
	public List<Enchere> selectEnchereById(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectMaxEnchere(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean finEnchere(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

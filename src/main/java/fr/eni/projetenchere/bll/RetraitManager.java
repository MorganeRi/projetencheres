package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Retrait;

public interface RetraitManager {

	public void ajouterRetrait (Retrait retrait) throws BusinessException;
	
	public Retrait selectParIdRetrait (Integer idArticle) throws BusinessException;
	
	public void majRetrait (Retrait retrait) throws BusinessException;
	
}

package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public interface CategorieManager {

	public List<Categorie> selectAllCategorie() throws BusinessException;
	
}

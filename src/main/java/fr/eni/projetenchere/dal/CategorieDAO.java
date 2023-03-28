package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public interface CategorieDAO {

	public List<Categorie> selectAllCategorie() throws BusinessException;

}

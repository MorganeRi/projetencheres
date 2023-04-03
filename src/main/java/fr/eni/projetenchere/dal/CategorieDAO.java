package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public interface CategorieDAO {
	
	public Categorie insertCategorie(Categorie categorie) throws BusinessException;
	
	public Categorie updateCategorie(Categorie categorie) throws BusinessException;
	
	public List<Categorie> selectAllCategorie() throws BusinessException;
	
	public Categorie selectCategorieByID(Integer idCategorie) throws BusinessException;

}

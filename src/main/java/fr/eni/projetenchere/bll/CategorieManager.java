package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public interface CategorieManager {
	
	
	public Categorie ajouterCategorie(Categorie categorie) throws BusinessException;
	
	public Categorie majCategorie(Categorie categorie) throws BusinessException;
	
	public Categorie supprimerCategorie (Categorie categorie) throws BusinessException;

	public List<Categorie> selectAllCategorie() throws BusinessException;
	
	public Categorie selectCategorieParId(Integer idCategorie) throws BusinessException;
}
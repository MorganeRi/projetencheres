package fr.eni.projetenchere.bll;

import java.util.List;


import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

import fr.eni.projetenchere.dal.CategorieDAO;
import fr.eni.projetenchere.dal.DAOFactory;

public class CategorieManagerImpl implements CategorieManager{
	private CategorieDAO dao = DAOFactory.getCategorieDAO();
	
	@Override
	public Categorie ajouterCategorie(Categorie categorie) throws BusinessException {
		dao.insertCategorie(categorie);
		return categorie;
	}

	
	@Override
	public Categorie majCategorie(Categorie categorie) throws BusinessException {
		dao.updateCategorie(categorie);
		return categorie;
	}

	
	@Override
	public Categorie supprimerCategorie(Categorie categorie) throws BusinessException {
		dao.deleteCategorie(categorie);
		return categorie;
	}

	@Override
	public List<Categorie> selectAllCategorie() throws BusinessException {
		try {
			return dao.selectAllCategorie();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.FAIL_SELECT_CATEGORIE);
			throw businessException;
		}
	}

	@Override
	public Categorie selectCategorieParId(Integer idCategorie) throws BusinessException {
		try {
			return dao.selectCategorieByID(idCategorie);
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.FAIL_SELECT_CATEGORIE_BY_ID);
			throw businessException;
		}
	}


	
	
	

}

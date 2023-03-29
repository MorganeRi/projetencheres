package fr.eni.projetenchere.bll;

import java.util.List;


import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

import fr.eni.projetenchere.dal.CategorieDAO;
import fr.eni.projetenchere.dal.DAOFactory;

public class CategorieManagerImpl implements CategorieManager{
	private CategorieDAO dao = DAOFactory.getCategorieDAO();

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

}

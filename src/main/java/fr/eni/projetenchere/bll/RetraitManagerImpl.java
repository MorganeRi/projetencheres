package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.RetraitDAO;

public class RetraitManagerImpl implements RetraitManager {

	private RetraitDAO dao = DAOFactory.getRetraitDAO();
	
	@Override
	public void ajouterRetrait(Retrait retrait) throws BusinessException {
		dao.insertRetrait(retrait);
		
	}

	@Override
	public Retrait selectParIdRetrait(Integer idArticle) throws BusinessException {
		Retrait retrait = new Retrait();
		
		retrait = dao.selectByIdRetrait(idArticle);
		return retrait;
	}

	@Override
	public void majRetrait(Retrait retrait) throws BusinessException {
		dao.UpdateRetrait(retrait);
		
	}

}

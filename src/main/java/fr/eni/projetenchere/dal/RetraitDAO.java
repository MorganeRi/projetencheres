package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Retrait;

public interface RetraitDAO {

	// Ajout d'un retrait
	public void insertRetrait(Retrait retrait) throws BusinessException;

	// Afficher un point de retrait par id
	public Retrait selectByIdRetrait(Integer id) throws BusinessException;

	// Updater un point de retrait
	public void UpdateRetrait(Retrait retrait) throws BusinessException;
}

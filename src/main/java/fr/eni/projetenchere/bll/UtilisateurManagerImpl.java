package fr.eni.projetenchere.bll;


import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.UtilisateurDAO;

public class UtilisateurManagerImpl implements UtilisateurManager{
	private UtilisateurDAO utilisateurDao ;
	
	/**
	 * Le constructeur permet d'initialiser la variable UtilisateurDAOJdbcImpl pour 
	 * permettre une communication avec la base de données. 
	 */
	public UtilisateurManagerImpl() {
		this.utilisateurDao=DAOFactory.getUtilisateurDAO();
	}
	
	/*Contraintes createUtilisateur : 
	 * - email non null et pas de doublon -> Créer méthode pour réutiliser
	 * - pseudo unique, accepte uniquement les caractères alphanumérique
	 * - Crédit de 100 points par défaut à la création */
	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	
	// voir méthode du prof
	@Override
	public void authentifierUtilisateur(String email, String motDePasse) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	//Même contraintes que pour la création
	@Override
	public void majUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerEmail(Utilisateur utilisateur, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerPseudo(Utilisateur utilisateur, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		
		
		try {
			this.utilisateurDao.deleteUtilisateur(utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public Utilisateur selectParNoUtilisateur(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void majMontantCredit(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}

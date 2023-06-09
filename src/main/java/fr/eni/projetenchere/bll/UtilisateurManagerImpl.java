package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.UtilisateurDAO;

public class UtilisateurManagerImpl implements UtilisateurManager {
	
	private UtilisateurDAO utilisateurDao;

	/**
	 * Le constructeur permet d'initialiser la variable UtilisateurDAOJdbcImpl pour
	 * permettre une communication avec la base de données.
	 */
	public UtilisateurManagerImpl() {
		this.utilisateurDao = DAOFactory.getUtilisateurDAO();
	}

	/*
	 * Contraintes createUtilisateur : - email non null et pas de doublon -> Créer
	 * méthode pour réutiliser - pseudo unique, accepte uniquement les caractères
	 * alphanumérique - Crédit de 100 points par défaut à la création
	 */
	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDao.insertUtilisateur(utilisateur);
	}

	// authentifier par mail
	@Override
	public void authentifierUtilisateurMail(Utilisateur util) throws BusinessException {
		this.utilisateurDao.connectUtilisateur(util);

	}

	// authentifier par pseudo
	@Override
	public void authentifierUtilisateurPseudo(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDao.connectUtilisateurPseudo(utilisateur);

	}

	// Même contraintes que pour la création
	@Override
	public void majUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDao.updateUtilisateur(utilisateur);

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
	public Utilisateur supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
			utilisateurDao.deleteUtilisateur(utilisateur);
		return utilisateur;
	}

	@Override
	public Utilisateur selectParNoUtilisateur(Integer id) throws BusinessException {
		return this.utilisateurDao.selectByIdUtilisateur(id);

	}

	@Override
	public void majMontantCredit(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDao.updateMontantCredit(utilisateur);

	}

	@Override
	public Utilisateur selectParEmailUtilisateur(String email) throws BusinessException {
		return this.utilisateurDao.selectByEmailUtilisateur(email);
	}

	@Override
	public Utilisateur selectParPseudoUtilisateur(String pseudo) throws BusinessException {
		return this.utilisateurDao.selectByPseudoUtilisateur(pseudo);
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() throws BusinessException {
		try {
			return utilisateurDao.selectAllUtilisateur();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.FAIL_SELECT_ALL_UTILISATEUR);
			throw businessException;
		}
	}

	@Override
	public Utilisateur userOnOff(Utilisateur utilisateur) throws BusinessException {
		return utilisateurDao.actifOrNot(utilisateur);
	}
}

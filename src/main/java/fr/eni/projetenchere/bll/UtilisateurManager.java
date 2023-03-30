package fr.eni.projetenchere.bll;


import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;


public interface UtilisateurManager {
	
	
	/*Contraintes createUtilisateur : 
	 * - email non null et pas de doublon -> Créer méthode pour réutiliser
	 * - pseudo unique, accepte uniquement les caractères alphanumérique
	 * - Crédit de 100 points par défaut à la création */
	public void createUtilisateur (Utilisateur utilisateur) throws BusinessException;
	
	
	// voir méthode du prof
	public void authentifierUtilisateur (String email, String motDePasse)throws BusinessException;
	
	//Même contraintes que pour la création
	public void majUtilisateur (Utilisateur utilisateur) throws BusinessException;
	
	public void validerEmail (Utilisateur utilisateur, BusinessException businessException);
	
	public void validerPseudo (Utilisateur utilisateur, BusinessException businessException);
	
	public void supprimerUtilisateur (Utilisateur utilisateur) throws BusinessException;
		
	public Utilisateur selectParNoUtilisateur (Integer id) throws BusinessException;
	
	public void majMontantCredit (Utilisateur utilisateur)throws BusinessException;
	
	public Utilisateur selectParEmailUtilisateur (String email) throws BusinessException;

}

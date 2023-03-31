package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;

public interface UtilisateurDAO {

	// Inscription utilistateur
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;

	// Etape 1: uniquement avec le mail revenir plus tard pour ajouter pseudo
	public void connectUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	// Etape 1: uniquement avec le mail revenir plus tard pour ajouter pseudo
	public void connectUtilisateurPseudo(Utilisateur utilisateur) throws BusinessException;

	// modification de l'ensemble des informations de l'utilisateur
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;

	// suppression de l'ensemble des informations de l'utilisateur
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException;

	// afficher le profil des autres utilisateurs
	public Utilisateur selectByIdUtilisateur(Integer id) throws BusinessException;

	// Update du montant du crédit utilisateur
	public void updateMontantCredit(Utilisateur utilisateur) throws BusinessException;
	
	// afficher le profil des autres utilisateurs par email
	public Utilisateur selectByEmailUtilisateur(String email) throws BusinessException;

}

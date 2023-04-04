package fr.eni.projetenchere.dal;

import java.util.List;

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
	public Utilisateur deleteUtilisateur(Utilisateur utilisateur) throws BusinessException;

	// afficher le profil des autres utilisateurs
	public Utilisateur selectByIdUtilisateur(Integer id) throws BusinessException;

	// Update du montant du crédit utilisateur
	public void updateMontantCredit(Utilisateur utilisateur) throws BusinessException;
	
	// afficher le profil des autres utilisateurs par email
	public Utilisateur selectByEmailUtilisateur(String email) throws BusinessException;
	// afficher le profil des autres utilisateurs par pseudo
	Utilisateur selectByPseudoUtilisateur(String pseudo) throws BusinessException;
	
	//récupérer une liste qui contient tous les utilisateurs enregistrés dans la BDD
	public List<Utilisateur> selectAllUtilisateur() throws BusinessException;
 
}

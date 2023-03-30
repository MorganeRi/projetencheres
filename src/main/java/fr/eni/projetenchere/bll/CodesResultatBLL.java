package fr.eni.projetenchere.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	
	
//	Echec pour récupérer une liste de catégories
	public static final int FAIL_SELECT_CATEGORIE = 20000;
	
//	Echec pour récupérer une catégorie par ID
	public static final int FAIL_SELECT_CATEGORIE_BY_ID = 20001;
	
	public static final int  DOUBLON_MAIL_UTILISATEUR = 20002;
}

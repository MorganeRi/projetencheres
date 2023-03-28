package fr.eni.projetenchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	/**
	 * Echec général quand tentative d'ajouter un article null
	 */
	public static final int INSERT_ARTICLE_NULL=10000;
	
	/**
	 * Echec général quand tentative de supprimer un article null
	 */
	public static final int DELETE_ARTICLE_NULL=10010;
	/**
	 * Echec général quand tentative de update un article null
	 */
	public static final int UPDATE_ARTICLE_NULL=10020;
	/**
	 * Echec général quand tentative de update un article null
	 */
	public static final int UPDATE_PX_VENTE_ARTICLE_NULL=10030;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un article
	 */
	public static final int INSERT_ARTICLE_ECHEC=10001;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un article
	 */
	public static final int DELETE_ARTICLE_ECHEC=10011;
	/**
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int UPDATE_ARTICLE_ECHEC=10021;
	/**
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int UPDATE_PX_VENTE_ARTICLE_ECHEC=10031;
	/**
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int SELECT_ALL_ARTICLE_ECHEC=10003;
	/**
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int SELECT_ALL_LISTE_ECHEC=10005;
	/**
	 * Echec de l'authentification
	 */
	public static final int SELECT_UTILISATEUR_ECHEC=10002;
	
	public static final int SELECT_UTILISATEUR_MDP_ECHEC=10004;
	
	
	
}

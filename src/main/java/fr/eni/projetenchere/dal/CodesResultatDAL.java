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
	 * Echec général quand tentative d'ajouter un utilisateur null
	 */
	public static final int INSERT_UTILISATEUR_NULL=10100;
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
	 * Echec général quand erreur non gérée à l'insertion d'un utilisateur
	 */
	public static final int INSERT_UTILISATEUR_ECHEC=10101;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un utilisateur
	 */
	public static final int INSERT_UTILISATEUR_AUTHENTIFICATION_NULL=10102;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un article
	 */
	public static final int DELETE_ARTICLE_ECHEC=10011;
	/**
	 * Echec de l'update d'une article
	 */
	public static final int UPDATE_ARTICLE_ECHEC=10021;
	/**
	 * Echec de l'update du px de vente
	 */
	public static final int UPDATE_PX_VENTE_ARTICLE_ECHEC=10031;
	/**
	 * Echec du select by categorie
	 */
	public static final int SELECT_BY_CATEGORIE_ARTICLE_ECHEC=10003;
	/**
	 * Echec du select by nom d'article
	 */
	public static final int SELECT_BY_NOM_ECHEC=10005;
	/**
	 * Echec de l'authentification
	 */
	public static final int SELECT_UTILISATEUR_ECHEC=10002;
	
	public static final int SELECT_UTILISATEUR_MDP_ECHEC=10004;
	
	
	
}

package fr.eni.projetenchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	/**
	 * Echec général quand tentative d'ajouter un article null
	 */
	public static final int INSERT_ARTICLE_NULL = 10000;
	/**
	 * Echec général quand tentative d'ajouter un utilisateur null
	 */
	public static final int INSERT_UTILISATEUR_NULL = 10100;
	/**
	 * Echec général quand tentative de supprimer un article null
	 */
	public static final int DELETE_ARTICLE_NULL = 10010;
	/**
	 * Echec général quand tentative de supprimer un utilisateur null
	 */
	public static final int DELETE_UTILISATEUR_NULL = 10110;
	/**
	 * Echec général quand tentative de update un article null
	 */
	public static final int UPDATE_ARTICLE_NULL = 10020;
	/**
	 * Echec général quand tentative de update un utilisateur null
	 */
	public static final int UPDATE_UTILISATEUR_NULL = 10120;
	/**
	 * Le paramètre utilisateur rentré est null
	 */
	public static final int ACTIF_UTILISATEUR_NULL= 10208;
	
	public static final int UPDATE_USER_ACTIF_ECHEC = 10209;
	/**
	 * Echec général quand tentative de update un article null
	 */
	public static final int UPDATE_PX_VENTE_ARTICLE_NULL = 10030;

	/**
	 * Echec de l'insertion d'une enchere null
	 */
	public static final int INSERT_ENCHERE_NULL = 10006;

	/**
	 * Echec pour retrouver le paramètre d'article, retourne un null
	 */
	public static final int SELECT_ENCHERE_PARAMETER_NULL = 10008;

	/**
	 * Echec général quand erreur non gérée à l'insertion d'un article
	 */
	public static final int INSERT_ARTICLE_ECHEC = 10001;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un utilisateur
	 */
	public static final int INSERT_UTILISATEUR_ECHEC = 10101;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un retrait
	 */
	public static final int INSERT_RETRAIT_NULL = 10103;
	/**
	 * Echec null quand erreur non gérée à l'insertion d'un retrait
	 */
	public static final int UPDATE_RETRAIT_NULL = 10108;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un retrait
	 */
	public static final int INSERT_RETRAIT_ECHEC = 10104;
	/**
	 * Echec général quand erreur non gérée à l'insertion d'un retrait
	 */
	public static final int SELECT_BY_ID_RERTRAIT_ECHEC = 10107;
	/**
	 * Echec général quand erreur non gérée à l'update d'un retrait
	 */
	public static final int UPDATE_RETRAIT_ECHEC = 10109;

	/**
	 * Echec de l'insertion d'une enchere null
	 */
	public static final int INSERT_ENCHERE_ECHEC = 10007;

	/**
	 * Echec général quand erreur non gérée à l'insertion d'un utilisateur
	 */
	public static final int INSERT_UTILISATEUR_AUTHENTIFICATION_NULL = 10102;
	/**
	 * Echec général quand erreur non gérée à suppression d'un article
	 */
	public static final int DELETE_ARTICLE_ECHEC = 10011;
	/**
	 * Echec général quand erreur non gérée à suppression d'un utilisateur
	 */
	public static final int DELETE_UTILISATEUR_ECHEC = 10111;
	/**
	 * Echec général quand erreur non gérée à suppression d'un utilisateur
	 */
	public static final int DOUBLON_MAIL_UTILISATEUR = 10113;

	/**
	 * Echec de l'update d'une article
	 */
	public static final int UPDATE_ARTICLE_ECHEC = 10021;
	/**
	 * Echec de l'update d'un utilisateur
	 */
	public static final int UPDATE_UTILISATEUR_ECHEC = 10121;
	/**
	 * Echec de l'update d'un credit
	 */
	public static final int UPDATE_CREDIT_ECHEC = 10122;
	/**
	 * Echec de l'update du px de vente
	 */
	public static final int UPDATE_PX_VENTE_ARTICLE_ECHEC = 10031;
	/**
	 * Echec de l'update car article null
	 */
	public static final int UPDATE_NO_ACQUEREUR_NULL = 10032;
	/**
	 * Echec de l'update du no acquereur
	 */
	public static final int UPDATE_NO_ACQUEREUR_ECHEC = 10033;
	/**
	 * Echec du select by categorie
	 */
	public static final int SELECT_BY_CATEGORIE_ARTICLE_ECHEC = 10003;
	/**
	 * Echec du select by nom d'article
	 */
	public static final int SELECT_BY_NOM_ECHEC = 10005;
	/**
	 * Echec du select by id d'article
	 */
	public static final int SELECT_BY_ID_ARTICLE_ECHEC = 10015;
	/**
	 * Echec du select by id d'article
	 */
	public static final int SELECT_BY_EMAIL_UTILISATEUR_ECHEC = 10016;
	/**
	 * Echec du select by id utilisateur
	 */
	public static final int SELECT_BY_ID_UTILISATEUR_ECHEC = 10105;
	
	public static final int GET_ALL_UTILISATEUR_ECHEC = 10207;
	
	/**
	 * le paramètre categorie rentré est null
	 */
	public static final int INSERT_CATEGORIE_NULL = 10201;
	/**
	 * l'insert de categorie a échoué
	 */
	public static final int INSERT_CATEGORIE_ECHEC = 10202;
	/**
	 * le paramètre catégorie rentré est null
	 */
	
	public static final int UPDATE_CATEGORIE_NULL = 10203;
	/**
	 * Echec pour mettre à jour cette catégorie
	 */
	public static final int UPDATE_CATEGORIE_ECHEC = 10204;
	/**
	 * le paramètre catégorie rentré est null pour delete
	 */
	public static final int REMOVE_CATEGORIE_NULL = 10205;
	/**
	 * Echec pour supprimer cette catégorie
	 */
	public static final int REMOVE_CATEGORIE_ECHEC = 10206;

	public static final int SELECT_CATEGORIE_BY_ID_ECHEC = 10049;
	/**
	 * Echec du select all catégorie
	 */
	public static final int GET_ALL_CATEGORIE_ECHEC = 10106;

	/**
	 * Echec pour récupérer une liste d'enchère en fonction de l'id d'un Article
	 */
	public static final int ECHEC_SELECT_ENCHERE = 10009;

	/**
	 * Echec de l'authentification
	 */
	public static final int SELECT_UTILISATEUR_ECHEC = 10002;

	public static final int SELECT_UTILISATEUR_MDP_ECHEC = 10004;

	public static final int ECHEC_SELECT_ENCHERE_BY_ID_U = 10019;

	public static final int ECHEC_SELECT_MAX_ENCHERE = 10029;

	public static final int SELECT_ALL_ARTICLE = 10112;
	
	public static final int MAIL_DOUBLON_ERREUR=30003;
	
	public static final int MOTDEPASSE_ERREUR=30001;
}

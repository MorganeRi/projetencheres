package fr.eni.projetenchere.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */

public abstract class CodesResultatServlets {
	
	public static final int FORMAT_LISTE_ERREUR=30000;
	
	public static final int MOTDEPASSE_ERREUR=30001;
	
	public static final int MOTDEPASSEACTUEL_ERREUR=30002;
	
	public static final int MAIL_DOUBLON_ERREUR=30003;
	
	public static final int ARTICLE_A_MODIFIE_EQUIVALENT = 30011;
	
	public static final int ERREUR_DATE_POSTERIEUR = 30012;
	
	public static final int ERREUR_DATES_IDENTIQUES = 30013;
}

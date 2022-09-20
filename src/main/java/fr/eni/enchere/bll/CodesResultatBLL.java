package fr.eni.enchere.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int UTILISATEUR_LOGIN_MDP_KO=20000;
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */

	public static final int UTILISATEUR_CREATION_EMAIL_DUP = 20002;
	public static final int UTILISATEUR_CREATION_PSEUDO_DUP = 20003;
	public static final int UTILISATEUR_CREATION_PASSWORD_DIFF = 20004;
	
}

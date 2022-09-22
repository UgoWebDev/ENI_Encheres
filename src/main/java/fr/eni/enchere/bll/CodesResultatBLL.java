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
	public static final int UTILISATEUR_CREATION_NOM = 20005;
	public static final int UTILISATEUR_CREATION_PRENOM = 20006;
	public static final int UTILISATEUR_CREATION_PSEUDO = 20007;
	public static final int UTILISATEUR_CREATION_EMAIL = 20008;
	public static final int UTILISATEUR_CREATION_TELEPHONE = 20009;
	public static final int UTILISATEUR_CREATION_RUE = 20010;
	public static final int UTILISATEUR_CREATION_CODE_POSTAL = 20011;
	public static final int UTILISATEUR_CREATION_VILLE = 20012;
	public static final int UTILISATEUR_CREATION_MOT_DE_PASSE = 20013;
	
	//Erreur liée à la gestion des articles
	public static final int ARTICLE_CREATION_DATE = 20101;

	
}

	
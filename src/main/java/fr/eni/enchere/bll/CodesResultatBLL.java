package fr.eni.enchere.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	// Erreurs liées à la connexion
	public static final int UTILISATEUR_LOGIN_MDP_KO=20000;


	// Erreurs liées à l'utilisateur
	public static final int UTILISATEUR_CREATION_EMAIL_DUP = 20002;
	public static final int UTILISATEUR_CREATION_PSEUDO_DUP = 20003;
	public static final int UTILISATEUR_CREATION_PASSWORD_DIFF = 20004;
	public static final int UTILISATEUR_CREATION_NOM = 20005;
	public static final int UTILISATEUR_CREATION_PRENOM = 20006;
	public static final int UTILISATEUR_CREATION_PSEUDO = 20007;
	public static final int UTILISATEUR_CREATION_EMAIL = 20008;
	public static final int UTILISATEUR_CREATION_TELEPHONE = 20009;
	public static final int UTILISATEUR_CREATION_MOT_DE_PASSE = 20013;
	public static final int UTILISATEUR_INEXISTANT = 20014;
	public static final int UTILISATEUR_MODIF_PSEUDO = 20015;
	public static final int UTILISATEUR_MODIF_EMAIL = 20016;
	public static final int UTILISATEUR_MODIF_PASSWORD = 20017;
	
	
	
	//Erreurs liées à la gestion des articles
	public static final int ARTICLE_CREATION_DATE = 20101;
	public static final int ARTICLE_CREATION_DATE_FIN = 20102;
	public static final int ARTICLE_DATE_DEBUT_SUPERIEUR_DATE_FIN = 20103;
	public static final int ARTICLE_NON_EXISTANT = 20104;
	public static final int ARTICLE_CREATION_ANNEE = 20105;
	public static final int ARTICLE_CREATION_MOIS = 20106;
	public static final int ARTICLE_CREATION_JOUR = 20107;
	public static final int ARTICLE_CREATION_PRIX = 20108;
	public static final int ARTICLE_CREATION_CATEGORIE = 20109;
	public static final int ARTICLE_CREATION_RUE = 20110;
	public static final int ARTICLE_CREATION_CODE_POSTAL = 20111;
	public static final int ARTICLE_CREATION_VILLE = 20112;
	
	public static final int ARTICLE_UPDATE_NOM = 20113;
	public static final int ARTICLE_UPDATE_DESCRIPTION = 20114;
	public static final int ARTICLE_UPDATE_RETRAIT = 20115;




	
	//Erreurs liées à la gestion des catégories
	public static final int CATEGORIE_NON_CONFORME = 20201;
	public static final int CATEGORIE_DEJA_EXISTANTE = 20202;
	public static final int CATEGORIE_NON_EXISTANTE = 20203;
	
	//Erreurs liées à la gestion des adresses
	public static final int ADRESSE_CREATION_RUE = 20301;
	public static final int ADRESSE_CREATION_CODE_POSTAL = 20302;
	public static final int ADRESSE_CREATION_VILLE = 20303;
	


	
}

	
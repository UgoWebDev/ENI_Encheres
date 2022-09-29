package fr.eni.enchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec de l'insertion d'une adresse 
	 */
	public static final int INSERT_ARTICLE_ADRESSE=10001;
	
	/**
	 * Echec de l'insertion d'une adresse nulle
	 */
	public static final int INSERT_ARTICLE_NULL=10002;
	
	/**
	 * Echec de l'insertion d'un article
	 */
	public static final int INSERT_ARTICLE=10003;
	
	/**
	 * Echec de la sélection d'un article par son numéro
	 */
	public static final int SELECT_ARTICLE_BY_NO=10004;
	/**
	 * Echec de de la sélection de tous les articles
	 */
	public static final int SELECT_ARTICLES=10005;
	/**
	 * Echec de de la suppression d'un article
	 */
	public static final int DELETE_ARTICLE=10006;
	
	/**
	 * Echec de de l'insertion d'un objet
	 */
	public static final int INSERT_OBJET_ECHEC=10007;
	
	/**
	 * Echec de de l'insertion d'un objet
	 */
	public static final int DELETE_UTILISATEUR=10008;
	public static final int MODIF_ADRESSE_NULL=10009;
	public static final int MODIF_ADRESSE_ECHEC=10010;
	
	

	

}













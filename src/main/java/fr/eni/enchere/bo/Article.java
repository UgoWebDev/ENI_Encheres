package fr.eni.enchere.bo;

import java.util.Date;
import java.util.List;

public class Article {
	
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private EtatsVente etatVente;
	private Utilisateur vendeur;
	private Categorie categorie;
	private List<Enchere>  encherisseurs;
	private Retrait retrait;
	
	public Article(Integer noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int miseAPrix, int prixVente, EtatsVente etatVente, Utilisateur vendeur, Categorie categorie,
			List<Enchere> encherisseurs, Retrait retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.encherisseurs = encherisseurs;
		this.retrait = retrait;
	}
	
	public Article(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix,
			int prixVente, EtatsVente etatVente, Utilisateur vendeur, Categorie categorie, List<Enchere> encherisseurs, Retrait retrait) {
		this( null ,nomArticle, description, dateDebutEncheres, dateFinEncheres , miseAPrix, prixVente , etatVente, vendeur, categorie, encherisseurs, retrait);
	}


	public Integer getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public int getMiseAPrix() {
		return miseAPrix;
	}


	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}


	public int getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}


	public EtatsVente getEtatVente() {
		return etatVente;
	}


	public void setEtatVente(EtatsVente etatVente) {
		this.etatVente = etatVente;
	}


	public Utilisateur getvendeur() {
		return vendeur;
	}


	public void setvendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<Enchere> getEncherisseurs() {
		return encherisseurs;
	}


	public void setEncherisseurs(List<Enchere> encherisseurs) {
		this.encherisseurs = encherisseurs;
	}


	public Retrait getRetrait() {
		return retrait;
	}


	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}


	enum EtatsVente {
		CREATION,
		ENCOURS,
		TERMINEE,
		RETIREE
	}

}

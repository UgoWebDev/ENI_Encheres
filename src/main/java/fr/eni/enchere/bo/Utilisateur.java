package fr.eni.enchere.bo;

import java.util.List;

public class Utilisateur {

    private Integer noUtilisateur;		// pour pouvoir être null
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
	private String telephone;
    private Adresse adresse;
    private String motDePasse;
    private int credit;
    private boolean administrateur;
    private List<Enchere> encheresSoumises;
    private List<Article> vendArticles;
    
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, Adresse adresse,
			String motDePasse, int credit, boolean administrateur, List<Enchere> encheresSoumises, List<Article> vendArticles ) {
			this( null,pseudo,  nom,  prenom,  email,  telephone,  adresse,  motDePasse,  credit,  administrateur, encheresSoumises, vendArticles);

	}
	public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			Adresse adresse, String motDePasse, int credit, boolean administrateur, List<Enchere> encheresSoumises, List<Article> vendArticles ) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.encheresSoumises = encheresSoumises;
		this.vendArticles = vendArticles;
	}
	
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	public List<Enchere> getEncheresSoumises() {
		return encheresSoumises;
	}
	public void setEncheresSoumises(List<Enchere> encheresSoumises) {
		this.encheresSoumises = encheresSoumises;
	}
	public List<Article> getVendArticles() {
		return vendArticles;
	}
	public void setVendArticles(List<Article> vendArticles) {
		this.vendArticles = vendArticles;
	}

	
}

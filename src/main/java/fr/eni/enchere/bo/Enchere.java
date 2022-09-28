package fr.eni.enchere.bo;

import java.util.Date;

public class Enchere {
	

	private Integer noEnchere;
	private Date dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateurEnchere;
	private Article articleEnchere;
	
	public Enchere(Integer noEnchere, Date dateEnchere, int montantEnchere, Utilisateur utilisateurEnchere,
			Article articleEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateurEnchere = utilisateurEnchere;
		this.articleEnchere = articleEnchere;
	}
	
	public Enchere(Date dateEnchere, int montantEnchere, Utilisateur utilisateurEnchere, Article articleEnchere) {
		this(null,dateEnchere,montantEnchere,utilisateurEnchere, articleEnchere);
	}
	
	public Integer getNoEnchere() {
		return noEnchere;
	}
	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public Utilisateur getUtilisateurEnchere() {
		return utilisateurEnchere;
	}
	public void setUtilisateurEnchere(Utilisateur utilisateurEnchere) {
		this.utilisateurEnchere = utilisateurEnchere;
	}
	public Article getArticleEnchere() {
		return articleEnchere;
	}
	public void setArticleEnchere(Article articleEnchere) {
		this.articleEnchere = articleEnchere;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [noEnchere=");
		builder.append(noEnchere);
		builder.append(", dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append(", utilisateurEnchere=");
		builder.append(utilisateurEnchere);
		builder.append(", articleEnchere=");
		builder.append(articleEnchere);
		builder.append("]");
		return builder.toString();
	}
	

}

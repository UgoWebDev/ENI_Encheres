package fr.eni.enchere.bo;

import java.util.List;

public class Categorie {
	
	private Integer noCategorie;
	private String libelleCategorie;
	private List<Article> articles;
	
	public Categorie(Integer noCategorie, String libelleCategorie, List<Article> articles) {
		super();
		this.noCategorie = noCategorie;
		this.libelleCategorie = libelleCategorie;
		this.articles = articles;
	}
	
	public Integer getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelleCategorie() {
		return libelleCategorie;
	}
	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	

}

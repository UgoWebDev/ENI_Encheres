package fr.eni.enchere.bo;

public class Categorie {
	
	private Integer noCategorie;
	private String libelleCategorie;
	
	public Categorie(Integer noCategorie, String libelleCategorie) {
		super();
		this.noCategorie = noCategorie;
		this.libelleCategorie = libelleCategorie;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelleCategorie=");
		builder.append(libelleCategorie);
		builder.append("]");
		return builder.toString();
	}
}

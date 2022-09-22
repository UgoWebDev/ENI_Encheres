package fr.eni.enchere.bo;

public class Retrait {
	
	private String rueRetrait;
	private String codePostalRetrait;
	private String villeRetrait;
	private Article articleRetrait;
	
	public Retrait(String rueRetrait, String codePostalRetrait, String villeRetrait, Article articleRetrait) {
		super();
		this.rueRetrait = rueRetrait;
		this.codePostalRetrait = codePostalRetrait;
		this.villeRetrait = villeRetrait;
		this.articleRetrait = articleRetrait;
	}
	
	public String getRueRetrait() {
		return rueRetrait;
	}
	public void setRueRetrait(String rueRetrait) {
		this.rueRetrait = rueRetrait;
	}
	public String getCodePostalRetrait() {
		return codePostalRetrait;
	}
	public void setCodePostalRetrait(String codePostalRetrait) {
		this.codePostalRetrait = codePostalRetrait;
	}
	public String getVilleRetrait() {
		return villeRetrait;
	}
	public void setVilleRetrait(String villeRetrait) {
		this.villeRetrait = villeRetrait;
	}
	public Article getArticleRetrait() {
		return articleRetrait;
	}
	public void setArticleRetrait(Article articleRetrait) {
		this.articleRetrait = articleRetrait;
	}
	

}

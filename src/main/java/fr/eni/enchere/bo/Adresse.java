package fr.eni.enchere.bo;

public class Adresse {
	
	private Integer 	noAdresse;
	private String 		rue;
	private String 		codePostal;
	private String 		ville;
	
	public Adresse(Integer noAdresse, String rue, String codePostal, String ville) {
		super();
		this.noAdresse = noAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Adresse(String rue, String codePostal, String ville) {
		this(null,rue,codePostal,ville);
	}
	
	public Integer getNoAdresse() {
		return noAdresse;
	}
	public void setNoAdresse(Integer noAdresse) {
		this.noAdresse = noAdresse;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adresse [noAdresse=");
		builder.append(noAdresse);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}
}

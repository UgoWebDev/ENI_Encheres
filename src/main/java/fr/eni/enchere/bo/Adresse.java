package fr.eni.enchere.bo;

public class Adresse {
	
	private Integer 	noAdresse;
	private String 		rueAdresse;
	private String 		codePostalAdresse;
	private String 		villeAdresse;
	
	public Adresse(Integer noAdresse, String rueAdresse, String codePostalAdresse, String villeAdresse) {
		super();
		this.noAdresse = noAdresse;
		this.rueAdresse = rueAdresse;
		this.codePostalAdresse = codePostalAdresse;
		this.villeAdresse = villeAdresse;
	}
	
	public Adresse(String rueAdresse, String codePostalAdresse, String villeAdresse) {
		this(null,rueAdresse,codePostalAdresse,villeAdresse);
	}
	
	public Integer getNoAdresse() {
		return noAdresse;
	}
	public void setNoAdresse(Integer noAdresse) {
		this.noAdresse = noAdresse;
	}
	public String getRueAdresse() {
		return rueAdresse;
	}
	public void setRueAdresse(String rueAdresse) {
		this.rueAdresse = rueAdresse;
	}
	public String getCodePostalAdresse() {
		return codePostalAdresse;
	}
	public void setCodePostalAdresse(String codePostalAdresse) {
		this.codePostalAdresse = codePostalAdresse;
	}
	public String getVilleAdresse() {
		return villeAdresse;
	}
	public void setVilleAdresse(String villeAdresse) {
		this.villeAdresse = villeAdresse;
	}
}

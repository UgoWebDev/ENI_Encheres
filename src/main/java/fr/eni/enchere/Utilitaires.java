package fr.eni.enchere;

public class Utilitaires {
	
	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z 0-9]*$");
	}

}

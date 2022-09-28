package fr.eni.enchere;

public class Utilitaires {
	
/**
 * Vérifie qu'une chaine ne contient que des caractères alpha numériques
 * 
 * @param s : une chaine de caractère
 * @return 	: true si la chaine est alphanumérique, false sinon
 */
	
	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z 0-9]*$");
	}

}

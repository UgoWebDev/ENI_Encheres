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

	public static String toHtml(String chaine) {
		String[][] html = new String[][] {{" à","&agrave;"},{"À","&Agrave;"},{"á","&aacute;"},{"Á","&Aacute;"},{"â","&acirc; "},{"Â","&Acirc; "},{"ã","&atilde;"},{"Ã","&Atilde;"},{"ä","&auml; "},{"Ä","&Auml; "},{"å","&aring; "},{"Å","&Aring; "},{"æ","&aelig; "},{"Æ","&AElig; "},{"è","&egrave;"},{"È","&Egrave;"},{"é","&eacute;"},{"É","&Eacute;"},{"ê","&ecirc; "},{"Ê","&Ecirc; "},{"ë","&euml; "},{"Ë","&Euml; "},{"ì","&igrave;"},{"Ì","&Igrave;"},{"í","&iacute;"},{"Í","&Iacute;"},{"î","&icirc; "},{"Î","&Icirc; "},{"ï","&iuml; "},{"Ï","&Iuml; "},{"ò","&ograve;"},{"Ò","&Ograve;"},{"ó","&oacute;"},{"Ó","&Oacute;"},{"ô","&ocirc; "},{"Ô","&Ocirc; "},{"õ","&otilde;"},{"Õ","&Otilde;"},{"ö","&ouml; "},{"Ö","&Ouml; "},{"ø","&oslash;"},{"Ø","&Oslash;"},{"ù","&ugrave;"},{"Ù","&Ugrave;"},{"ú","&uacute;"},{"Ú","&Uacute;"},{"û","&ucirc; "},{"Û","&Ucirc; "},{"ü","&uuml; "},{"Ü","&Uuml; "},{"ñ","&ntilde;"},{"Ñ","&Ntilde;"},{"ç","&ccedil;"},{"Ç","&Ccedil;"},{"ý","&yacute;"},{"Ý","&Yacute;"},{"ß","&szlig; "},{"»","&laquo; "},{"»","&raquo; "},{"&","&amp;"},{"<","&lt;"},{"<","&gt;"},{"\"","&quot;"},{"§","&para; "},{"©","&copy; "},{"  ","&nbsp; "}};


		for (int i = 0; i < html.length; i++) {
			chaine.replaceAll(html[i][0], html[i][1]);
		}
		return chaine;
	}
}




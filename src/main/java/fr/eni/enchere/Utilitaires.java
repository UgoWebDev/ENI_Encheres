package fr.eni.enchere;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.eni.enchere.bll.CodesResultatBLL;

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
		String[][] html = new String[][] {{"à","&agrave;"},{"À","&Agrave;"},{"á","&aacute;"},{"Á","&Aacute;"},{"â","&acirc; "},{"Â","&Acirc; "},{"ã","&atilde;"},
			{"Ã","&Atilde;"},{"ä","&auml; "},{"Ä","&Auml; "},{"å","&aring; "},{"Å","&Aring; "},{"æ","&aelig; "},{"Æ","&AElig; "},{"è","&egrave;"},{"È","&Egrave;"},
			{"é","&eacute;"},{"É","&Eacute;"},{"ê","&ecirc; "},{"Ê","&Ecirc; "},{"ë","&euml; "},{"Ë","&Euml; "},{"ì","&igrave;"},{"Ì","&Igrave;"},{"í","&iacute;"},
			{"Í","&Iacute;"},{"î","&icirc; "},{"Î","&Icirc; "},{"ï","&iuml; "},{"Ï","&Iuml; "},{"ò","&ograve;"},{"Ò","&Ograve;"},{"ó","&oacute;"},{"Ó","&Oacute;"},
			{"ô","&ocirc; "},{"Ô","&Ocirc; "},{"õ","&otilde;"},{"Õ","&Otilde;"},{"ö","&ouml; "},{"Ö","&Ouml; "},{"ø","&oslash;"},{"Ø","&Oslash;"},{"ù","&ugrave;"},
			{"Ù","&Ugrave;"},{"ú","&uacute;"},{"Ú","&Uacute;"},{"û","&ucirc; "},{"Û","&Ucirc; "},{"ü","&uuml; "},{"Ü","&Uuml; "},{"ñ","&ntilde;"},{"Ñ","&Ntilde;"},
			{"ç","&ccedil;"},{"Ç","&Ccedil;"},{"ý","&yacute;"},{"Ý","&Yacute;"},{"ß","&szlig; "},{"»","&laquo; "},{"»","&raquo; "},{"&","&amp;"},{"<","&lt;"},{"<","&gt;"},
			{"\"","&quot;"},{"§","&para; "},{"©","&copy; "},{"  ","&nbsp; "}};


		for (int i = 0; i < html.length; i++) {
			chaine = chaine.replaceAll(html[i][0], html[i][1]);
		}
		return chaine;
	}
	
	public static Date parseDate(String dateString) throws BusinessException{
		String[] dateArray = dateString.split("-");
		Date date = new Date();
		int annee = 0, mois = 0, jour = 0;
		BusinessException be = new BusinessException();
		try {
			if (!StringUtils.isInteger(dateArray[0])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_ANNEE);
				throw be;
			} else {
				annee = Integer.parseInt(dateArray[0]);
				}
			if (!StringUtils.isInteger(dateArray[1])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_MOIS);
				throw be;

			} else {
				mois = Integer.parseInt(dateArray[1]);
				}
			if (!StringUtils.isInteger(dateArray[2])) {
				be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_JOUR);
				throw be;

			} else {
				jour = Integer.parseInt(dateArray[2]);
				}
			
			Calendar calendar = new GregorianCalendar(annee, mois-1, jour);
			date = calendar.getTime();
		} catch (Exception e) {
			be.ajouterErreur(CodesResultatBLL.ARTICLE_CREATION_DATE);
			throw be;

		}
		
		return date;
	}
}




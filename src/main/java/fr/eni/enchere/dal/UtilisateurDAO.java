package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur getUtilisateurByMail(String login);

	Utilisateur getUtilisateurByPseudo(String login);
	
	Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException;

}

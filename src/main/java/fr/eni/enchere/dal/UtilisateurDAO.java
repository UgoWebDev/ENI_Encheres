package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur getUtilisateurByMail(String login)throws BusinessException;

	Utilisateur getUtilisateurByPseudo(String login) throws BusinessException;
	
	Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException;

}

package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	Utilisateur getUtilisateurByNo(Integer noUtilisateur);

	Utilisateur getUtilisateurByMail(String login);

	Utilisateur getUtilisateurByPseudo(String login) ;
	
	Utilisateur insertUtilisateur(Utilisateur user) throws BusinessException;

	void deleteUtilisateurs(Utilisateur user) throws BusinessException;

	Utilisateur updateUtilisateur(Utilisateur user)throws BusinessException;

}

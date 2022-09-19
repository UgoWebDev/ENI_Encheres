package fr.eni.enchere.bll;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	// Utilisation du pattern Singleton

	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public Utilisateur seConnecter(String login, String mdp) throws BusinessException {
		Utilisateur user = null; 
		BusinessException be = null;

		if (login.contains("@")) {
			user = utilisateurDAO.getUtilisateurByMail(login);
		} else {
			user = utilisateurDAO.getUtilisateurByPseudo(login);
		}
		if (user == null || !user.getMotDePasse().equals(mdp)) {
			be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_LOGIN_MDP_KO);
			throw be;
		} 
		
		return user;
	}
}

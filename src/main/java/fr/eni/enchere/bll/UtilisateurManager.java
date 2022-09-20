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
	
	/**
	 * 
	 * @param login
	 * @param email
	 * @throws BusinessException
	 * 
	 * Vérifie l'unicité par email ou pseudo
	 */
	public void exists(String login, String email) throws BusinessException {

		BusinessException be = null;

		if (utilisateurDAO.getUtilisateurByMail(email)!=null) {
			be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_EMAIL_DUP);
			throw be;
		} else {
			if (utilisateurDAO.getUtilisateurByPseudo(login)!=null) {
				be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PSEUDO_DUP);
				throw be;
			}
		}
	}
	public  Utilisateur insererUtilisateur(Utilisateur user) throws BusinessException {

			user = utilisateurDAO.insertUtilisateur(user);

		return user;
	}
	public void compareMdp(String mdp, String password) throws BusinessException {
		BusinessException be = null;

		if (mdp != password) {
			be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PASSWORD_DIFF);
			throw be;
		} 
	}
}

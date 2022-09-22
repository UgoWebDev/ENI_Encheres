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
	
	public  Utilisateur insertUtilisateur(Utilisateur user, String password) throws BusinessException {
		BusinessException be = new BusinessException();
		
		this.compareMdp(user.getMotDePasse(), password, be);
		this.exists(user.getPseudo(), user.getEmail(), be);
		this.valideUtilisateur(user,be);
		
		
		if(!be.hasErreurs())
		{
			user = utilisateurDAO.insertUtilisateur(user);
			System.out.println("insertUtilisateur OK");
		}
		else
		{
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
	private void exists(String login, String email, BusinessException be) throws BusinessException {
	
		if (utilisateurDAO.getUtilisateurByMail(email)!=null) {
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_EMAIL_DUP);
		} else {
			if (utilisateurDAO.getUtilisateurByPseudo(login)!=null) {
				be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PSEUDO_DUP);
			}
		}
	}
	private void compareMdp(String mdp, String password, BusinessException be) throws BusinessException {
		
		
		System.out.println("Mot de passe : " + mdp);
		System.out.println("Mot de passe : " + password);
		
		if (!mdp.equals(password)) {
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PASSWORD_DIFF);
		} 
	}
	private void valideUtilisateur(Utilisateur user, BusinessException be) throws BusinessException{
		
		if (user.getNom() == null || user.getNom() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_NOM);}
		if (user.getPrenom() == null || user.getPrenom() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PRENOM);}
		if (user.getPseudo() == null || user.getPseudo() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PSEUDO);}
		if (user.getEmail() == null || user.getEmail() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_EMAIL);}
		if (user.getTelephone() == null || user.getTelephone() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_TELEPHONE);}
		if (user.getRue() == null || user.getRue() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_RUE);}
		if (user.getCodePostal() == null || user.getCodePostal() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_CODE_POSTAL);}
		if (user.getVille() == null || user.getVille() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_VILLE);}
		if (user.getMotDePasse() == null || user.getMotDePasse() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_MOT_DE_PASSE);}
	}
}

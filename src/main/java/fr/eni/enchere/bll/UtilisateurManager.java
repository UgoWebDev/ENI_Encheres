package fr.eni.enchere.bll;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.Utilitaires;
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
	
	public Utilisateur getUtilisateur(String pseudo) {
		return utilisateurDAO.getUtilisateurByPseudo(pseudo);
	}
	
	public Utilisateur getUtilisateurByNo(Integer noUtilisateur)  {
		return utilisateurDAO.getUtilisateurByNo(noUtilisateur);
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
		this.existsEmail(user.getEmail(), be);
		this.existsLogin(user.getPseudo(), be);
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
	public void deleteUtilisateur(Utilisateur user) throws BusinessException {
		BusinessException be = new BusinessException();
		this.notExists(user, be);
		if(!be.hasErreurs())
		{
			utilisateurDAO.deleteUtilisateurs(user);
			ArticleManager.getInstance().getArticles(null,null, null);
			System.out.println("deleteUtilisateur OK");
		}
		else
		{
			throw be;
		}
	}
	public Utilisateur updateUtilisateur(Utilisateur user, Utilisateur userOld, String password) throws BusinessException{
		BusinessException be = new BusinessException();

		if(!user.getPseudo().equals(userOld.getPseudo())) {
			this.existsLogin(user.getPseudo(), be);
		}
		if(!user.getEmail().equals(userOld.getEmail())) {
			this.existsEmail(user.getEmail(), be);
		}
		
		this.compareMdp(user.getMotDePasse(), password, be);
		this.valideUtilisateur(user,be);


		if(!be.hasErreurs())
		{
			user = utilisateurDAO.updateUtilisateur(user);
			System.out.println("modifUtilisateur OK");
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
	private void existsLogin(String login, BusinessException be) throws BusinessException {

		if (utilisateurDAO.getUtilisateurByPseudo(login)!=null) {
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PSEUDO_DUP);
		}
	}
	
	private void existsEmail(String email, BusinessException be) throws BusinessException {

		if (utilisateurDAO.getUtilisateurByMail(email)!=null) {
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_EMAIL_DUP);
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
		if (!Utilitaires.isAlphaNumeric(user.getPseudo()) ) {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_PSEUDO_ALPHA);}
		if (user.getEmail() == null || user.getEmail() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_EMAIL);}
		if (user.getMotDePasse() == null || user.getMotDePasse() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_MOT_DE_PASSE);}
		if (user.getAdresse().getRue() == null || user.getAdresse().getRue() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_RUE);}
		if (user.getAdresse().getCodePostal() == null || user.getAdresse().getCodePostal() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_CODE_POSTAL);}
		if (user.getAdresse().getVille() == null || user.getAdresse().getVille() == "") {be.ajouterErreur(CodesResultatBLL.UTILISATEUR_CREATION_VILLE);}
	}
	private void notExists(Utilisateur user, BusinessException be) {
		if (user == null) {
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_INEXISTANT);
		} else {

		}
		
	}
}

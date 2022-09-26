package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereManager {
	// Utilisation du pattern Singleton

	private static EnchereManager instance;
	private EnchereDAO enchereDAO;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public Enchere getEnchereByNo(Integer noEnchere) throws BusinessException {
		return enchereDAO.getEnchereByNo(noEnchere);
	}
	
	public List<Enchere> getEncheresByUtilisateur(Utilisateur user) throws BusinessException {
		return enchereDAO.getEncheresByUtilisateur(user);
	}
	
	public List<Enchere> getEnchereByArticle(Article article) throws BusinessException {
		return enchereDAO.getEncheresByArticle(article);
	}
	
	public List<Enchere> getEncheresByUtilisateurByContent(Utilisateur user, String contient) throws BusinessException {
		return enchereDAO.getEncheresByUtilisateurByContent(user, contient);
	}

	public Enchere insertEnchere(Enchere enchere) throws BusinessException {
		BusinessException be = new BusinessException();
		this.valideEnchere(enchere,be);
		if(!be.hasErreurs())
		{
			enchere = enchereDAO.insertEnchere(enchere);
			System.out.println("insertEnchere OK");
		}
		else
		{
			throw be;
		}
		return enchere;
	}
	
	private void valideEnchere(Enchere Enchere, BusinessException be) {
	}
}

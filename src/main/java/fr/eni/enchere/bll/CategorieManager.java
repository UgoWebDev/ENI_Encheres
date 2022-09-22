package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.Utilitaires;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
	// Utilisation du pattern Singleton

	private static CategorieManager instance;
	private CategorieDAO categorieDAO;

	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public Categorie getCategorie(String categorie) throws BusinessException {
		return categorieDAO.getCategorie(categorie);
	}

	public List<Categorie> getCategories() {
		return categorieDAO.getCategories();
	}
	public  Categorie insertCategorie(String libelle) throws BusinessException {
		BusinessException be = new BusinessException();
		Categorie categorie = null;
		this.valideCategorie(libelle,be);
		this.exists(libelle, be);
		if(!be.hasErreurs())
		{
			categorie = categorieDAO.insertCategorie(libelle);
			System.out.println("insertCategorie OK");
		}
		else
		{
			throw be;
		}
		return categorie;
	}
	
	public void deleteCategorie(String libelle) throws BusinessException {
		BusinessException be = new BusinessException();
		this.notExists(libelle, be);
		if(!be.hasErreurs())
		{
			categorieDAO.deleteCategorie(libelle);
			System.out.println("deleteCategorie OK");
		}
		else
		{
			throw be;
		}
	}
	
	private void valideCategorie(String libelle, BusinessException be) {
		if (!Utilitaires.isAlphaNumeric(libelle)) {
			be.ajouterErreur(CodesResultatBLL.CATEGORIE_NON_CONFORME);
		} 
	}
	
	private void exists(String libelle, BusinessException be) throws BusinessException {
		
		if (categorieDAO.getCategorie(libelle)!=null) {
			be.ajouterErreur(CodesResultatBLL.CATEGORIE_DEJA_EXISTANTE);
		} 
	}
	
	private void notExists(String libelle, BusinessException be) throws BusinessException {
		
		if (categorieDAO.getCategorie(libelle)==null) {
			be.ajouterErreur(CodesResultatBLL.CATEGORIE_NON_EXISTANTE);
		} 
	}
}

package fr.eni.enchere.bll;


import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.dal.AdresseDAO;
import fr.eni.enchere.dal.DAOFactory;

public class AdresseManager {
	// Utilisation du pattern Singleton

	private static AdresseManager instance;
	private AdresseDAO AdresseDAO;

	public AdresseManager() {
		AdresseDAO = DAOFactory.getAdresseDAO();
	}
	public static AdresseManager getInstance() {
		if (instance == null) {
			instance = new AdresseManager();
		}
		return instance;
	}
	
	public Adresse getAdresse(Integer noAdresse) throws BusinessException {
		return AdresseDAO.getAdresse(noAdresse);
	}

	public Adresse insertAdresse(Adresse adresse) throws BusinessException {
		BusinessException be = new BusinessException();
		if(!be.hasErreurs())
		{
			adresse = AdresseDAO.insertAdresse(adresse);
			System.out.println("insertAdresse OK");
		}
		else
		{
			throw be;
		}
		return adresse;
	}
	
}
	
	
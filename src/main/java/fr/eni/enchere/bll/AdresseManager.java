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
	
	public Adresse getAdresseByNo(Integer noAdresse) throws BusinessException {
		return AdresseDAO.getAdresseByNo(noAdresse);
	}

	public Adresse insertAdresse(Adresse adresse) throws BusinessException {
		BusinessException be = new BusinessException();
		this.valideAdresse(adresse,be);
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
	
	private void valideAdresse(Adresse adresse, BusinessException be) {
		// TODO Auto-generated method stub
		if (adresse.getRue() == null || adresse.getRue() == "") {be.ajouterErreur(CodesResultatBLL.ADRESSE_CREATION_RUE);}
		if (adresse.getCodePostal() == null || adresse.getCodePostal() == "") {be.ajouterErreur(CodesResultatBLL.ADRESSE_CREATION_CODE_POSTAL);}
		if (adresse.getVille() == null || adresse.getVille() == "") {be.ajouterErreur(CodesResultatBLL.ADRESSE_CREATION_VILLE);}
	}
	
}
	
	
package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Adresse;

public interface AdresseDAO {
	
	Adresse getAdresseByNo(Integer noAdresse);

	Adresse insertAdresse(Adresse adresse) throws BusinessException;

	Adresse updateAdresse(Adresse adresse) throws BusinessException;


}

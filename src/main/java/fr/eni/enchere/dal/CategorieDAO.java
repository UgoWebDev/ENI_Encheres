package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Categorie;


public interface CategorieDAO {

	Categorie getCategorie(String categorie);

	Categorie insertCategorie(String libelle) throws BusinessException;

	 void deleteCategorie(String libelle);

	List<Categorie> getCategories();

	Categorie getCategorieByNo(Integer noCategorie);

}

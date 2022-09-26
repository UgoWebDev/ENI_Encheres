package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public interface EnchereDAO {
	
	Enchere getEnchereByNo(Integer noEnchere) throws BusinessException;

	List<Enchere>  getEncheresByUtilisateur (Utilisateur utilisateur) throws BusinessException;
	List<Enchere>  getEncheresByUtilisateurByContent (Utilisateur utilisateur, String contient) throws BusinessException;
	List<Enchere>  getEncheresByArticle (Article article) throws BusinessException;
	
	Enchere insertEnchere(Enchere enchere) throws BusinessException;

}

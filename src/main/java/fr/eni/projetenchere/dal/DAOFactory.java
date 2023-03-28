package fr.eni.projetenchere.dal;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
}

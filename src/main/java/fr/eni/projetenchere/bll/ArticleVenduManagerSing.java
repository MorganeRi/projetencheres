package fr.eni.projetenchere.bll;

public class ArticleVenduManagerSing {
	
	
	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstanceArticle() {
		if(instance==null) {
			instance = new ArticleVenduManagerImpl();
		}
		return instance;
	}

}

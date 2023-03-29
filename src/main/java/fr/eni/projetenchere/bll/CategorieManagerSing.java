package fr.eni.projetenchere.bll;

public class CategorieManagerSing {
	private static CategorieManager instance;
	
	public static CategorieManager getInstanceCategorieImpl() {
		if(instance ==null) {
			instance = new CategorieManagerImpl();
		}
		return instance;
	}
}

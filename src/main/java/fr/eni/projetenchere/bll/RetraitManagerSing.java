package fr.eni.projetenchere.bll;

public class RetraitManagerSing {
	private static RetraitManager instance;
	
	public static RetraitManager getInstanceRetraitImpl() {
		if(instance ==null) {
			instance = new RetraitManagerImpl();
		}
		return instance;
	}
}

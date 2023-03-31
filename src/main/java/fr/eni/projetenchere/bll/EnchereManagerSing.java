package fr.eni.projetenchere.bll;

public class EnchereManagerSing {
	private static EnchereManager instance;

	public static EnchereManager getInstanceEnchereImpl() {
		if (instance == null) {
			instance = new EnchereManagerImpl();
		}
		return instance;
	}
}

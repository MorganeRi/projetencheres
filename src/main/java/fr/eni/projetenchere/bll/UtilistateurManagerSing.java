package fr.eni.projetenchere.bll;



public class UtilistateurManagerSing {

	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstanceUtilisateur(){
		if(instance==null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance;
	}
	
}

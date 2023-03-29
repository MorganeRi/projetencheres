package fr.eni.projetenchere.bll;



public class UtilistateurManagerSing {

	private static UtilisateurManagerImpl instance;
	
	public static UtilisateurManagerImpl getInstanceUtilisateur(){
		if(instance==null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance;
	}
	
}

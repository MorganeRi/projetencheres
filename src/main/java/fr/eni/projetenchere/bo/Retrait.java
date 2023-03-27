package fr.eni.projetenchere.bo;

public class Retrait {

	private String rue;
	private String codePostal;
	private String Ville;
	private ArticleVendu article;
	
	//Plusieurs constructeurs
	// complet
	public Retrait(String rue, String codePostal, String ville, ArticleVendu article) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		Ville = ville;
		this.article = article;
	}

	// vide
	public Retrait() {
		super();
	}

	//Getters and setters
	
	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return Ville;
	}


	public void setVille(String ville) {
		Ville = ville;
	}


	public ArticleVendu getArticle() {
		return article;
	}


	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	//To string
	
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", Ville=" + Ville + ", article=" + article + "]";
	}
	
	
	
	
	
}

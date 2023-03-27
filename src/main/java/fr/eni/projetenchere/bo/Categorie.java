package fr.eni.projetenchere.bo;

import java.util.List;

public class Categorie {

	private Integer noCategorie;
	private String libelle;
	private List<ArticleVendu> ListeArticles;
	
	//Plusieurs constructeurs differents
	
	//complet
	public Categorie(Integer noCategorie, String libelle, List<ArticleVendu> listeArticles) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		ListeArticles = listeArticles;
	}
	//sans id
	public Categorie(String libelle, List<ArticleVendu> listeArticles) {
		super();
		this.libelle = libelle;
		ListeArticles = listeArticles;
	}
	//sans list
	public Categorie(Integer noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	//san id sans list
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	//vide
	public Categorie() {
		super();
	}

	//Getters and Setters
	
	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getListeArticles() {
		return ListeArticles;
	}

	public void setListeArticles(List<ArticleVendu> listeArticles) {
		ListeArticles = listeArticles;
	}

	//To string 
	
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", ListeArticles=" + ListeArticles
				+ "]";
	}
	

	
	
	
}

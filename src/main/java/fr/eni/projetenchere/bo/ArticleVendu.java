package fr.eni.projetenchere.bo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArticleVendu {
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private Integer prixInitial;
	private Integer prixDeVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private ArrayList<Enchere> listeEncheres;

//	constructeur vide
	public ArticleVendu() {
		super();
	}

//	tous les attributs
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
		LocalDate dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
		Categorie categorie, ArrayList<Enchere> listeEncheres) {
	super();
	this.noArticle = noArticle;
	this.nomArticle = nomArticle;
	this.description = description;
	this.dateDebutEnchere = dateDebutEnchere;
	this.dateFinEnchere = dateFinEnchere;
	this.prixInitial = prixInitial;
	this.prixDeVente = prixDeVente;
	this.utilisateur = utilisateur;
	this.categorie = categorie;
	this.listeEncheres = listeEncheres;
}

	//	sans l'id, sans les null (prix initial, prix de vente), sans liste encheres
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

// 	sans l'id
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur, Categorie categorie,
			ArrayList<Enchere> listeEncheres) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.listeEncheres = listeEncheres;
	}

//	sans liste encheres
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

//	gettes et setters
	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public Integer getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Integer getPrixDeVente() {
		return prixDeVente;
	}

	public void setPrixDeVente(Integer prixDeVente) {
		this.prixDeVente = prixDeVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public ArrayList<Enchere> getListeEncheres() {
		return listeEncheres;
	}

	public void setListeEncheres(ArrayList<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere + ", prixInitial="
				+ prixInitial + ", prixDeVente=" + prixDeVente + ", utilisateur=" + utilisateur + ", categorie="
				+ categorie + ", listeEncheres=" + listeEncheres + "]";
	}

}

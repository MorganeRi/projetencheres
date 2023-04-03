package fr.eni.projetenchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEnchere;
	private LocalDateTime dateFinEnchere;
	private Integer prixInitial;
	private Integer prixDeVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	private String photo;
	private Integer noAcquereur;

	private List<Enchere> listeEncheres = new ArrayList<Enchere>();

//	constructeur vide
	public ArticleVendu() {
		super();
	}
	
	

//	tous les attributs

	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, String photo, Integer noAcquereur, List<Enchere> listeEncheres) {
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
		this.retrait = retrait;
		this.photo = photo;
		this.noAcquereur = noAcquereur;
		this.listeEncheres = listeEncheres;
	}



	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, List<Enchere> listeEncheres) {

		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.listeEncheres = listeEncheres;
	}

	// sans retrait
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie, List<Enchere> listeEncheres) {
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

	// sans l'id, sans les null (prix initial, prix de vente), sans liste encheres
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
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
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur, Categorie categorie,
			List<Enchere> listeEncheres) {
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
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
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

	// Complet sans ID
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur, Categorie categorie, Retrait retrait,
			ArrayList<Enchere> listeEncheres, String photo) {
		super();

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixDeVente = prixDeVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.listeEncheres = listeEncheres;
		this.photo = photo;
	}

//Complet
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixDeVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, ArrayList<Enchere> listeEncheres, String photo) {
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
		this.retrait = retrait;
		this.listeEncheres = listeEncheres;
		this.photo = photo;

	}

	// sans prix de vente et sans ID
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			Integer prixInitial, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	// getters et setters
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

	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
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

	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}

	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

	public Integer getNoAcquereur() {
		return noAcquereur;
	}



	public void setNoAcquereur(Integer noAcquereur) {
		this.noAcquereur = noAcquereur;
	}



	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere + ", prixInitial="
				+ prixInitial + ", prixDeVente=" + prixDeVente + ", utilisateur=" + utilisateur + ", categorie="
				+ categorie + ", retrait=" + retrait + ", photo=" + photo + ", noAcquereur=" + noAcquereur
				+ ", listeEncheres=" + listeEncheres + "]";
	}



	// methode toString()


}

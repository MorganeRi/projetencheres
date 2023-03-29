package fr.eni.projetenchere.bo;


import java.time.LocalDateTime;

public class Enchere {

	private Integer noEnchere;
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	private ArticleVendu article;
	private Utilisateur utilisateur;

	// Plusieurs constructeurs differents
	// complet
	public Enchere(Integer noEnchere, LocalDateTime dateEnchere, Integer montantEnchere, ArticleVendu article,
			Utilisateur utilisateur) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}

	// sans id
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, ArticleVendu article, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}
	// sans Article et sans utilisateur
	public Enchere(Integer noEnchere, LocalDateTime dateEnchere, Integer montantEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	// sans utilisateur
	public Enchere(Integer noEnchere, LocalDateTime dateEnchere, Integer montantEnchere, ArticleVendu article) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
	}
	// sans id, sans Article et sans utilisateur
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	// sans vide
	public Enchere() {
		super();
	}

	// Getters and setters

	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	// To String
	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", article=" + article + ", utilisateur=" + utilisateur + "]";
	}

}

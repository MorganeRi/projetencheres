package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String UPDATE_ARTICLE = "update article_vendu set nom_article=?, description=?, date_debut_enchere=?, date_fin_enchere=?, prix_initial=?,  no_categorie=? where no_article =?";
	private static final String INSERT_ARTICLE = "insert into article_vendu(nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial, no_utilisateur, no_categorie, photo) values (?,?,?,?,?,?,?,?)";
	private static final String DELETE_ARTICLE = "delete from article_vendu where no_article=?";
	private static final String SELECT_BY_CATEGORIE = "select no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_de_vente, no_utilisateur, a.no_categorie from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where libelle=?";
	private static final String SELECT_BY_NOM = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, a.no_categorie, libelle from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where nom_article like ?";
	private static final String UPDATE_PX_VENTE_ARTICLE = "update article_vendu set prix_de_vente=? where no_article =?";
	private static final String SELECT_BY_ID_ARTICLE = "select nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_de_vente, no_utilisateur, a.no_categorie, libelle, photo from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where no_article=?";
	private static final String SELECT_ALL = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, a.no_categorie, libelle from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie";
	private static final String SELECT_BY_NOM_BY_CAT = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, a.no_categorie, libelle from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where nom_article like ? and a.no_categorie=?";
	private static final String SELECT_BY_NOM_BY_CAT_SAUF_UTIL = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, a.no_categorie, libelle from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where nom_article like ? and a.no_categorie=? and no_utilisateur!=?";
	private static final String SELECT_BY_NOM_BY_CAT_BY_UTIL = "select no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_de_vente, no_utilisateur, a.no_categorie, libelle from article_vendu as a inner join categorie as c on a.no_categorie=c.no_categorie where nom_article like ? and a.no_categorie=? and no_utilisateur=?";
	
	
	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);

			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEnchere()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEnchere()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setInt(6, articleVendu.getCategorie().getNoCategorie());
			pstmt.setInt(7, articleVendu.getNoArticle());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);

			throw businessException;
		}
	}

	@Override
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEnchere()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEnchere()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(7, articleVendu.getCategorie().getNoCategorie());
			pstmt.setString(8, articleVendu.getPhoto());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				articleVendu.setNoArticle(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);

			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);

			pstmt.setInt(1, articleVendu.getNoArticle());
			pstmt.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);

			throw businessException;
		}

	}

	@Override
	public List<ArticleVendu> selectByCategorieArticle(String LibCat) throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);

			pstmt.setString(1, LibCat);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Categorie categorie = new Categorie(rs.getInt(9), LibCat);
				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, categorie);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOM_ECHEC);

			throw businessException;
		}
		return articles;

	}

	@Override
	public List<ArticleVendu> selectByNomArticle(String nom) throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM);
			String recherche = "%" + nom + "%";
			pstmt.setString(1, recherche);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(9);
				String libelle = rs.getString(10);
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, cat);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOM_ECHEC);

			throw businessException;
		}
		return articles;

	}

	@Override
	public void updatePxVenteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_PX_VENTE_ARTICLE_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PX_VENTE_ARTICLE);

			pstmt.setInt(1, articleVendu.getPrixDeVente());
			pstmt.setInt(2, articleVendu.getNoArticle());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.UPDATE_PX_VENTE_ARTICLE_ECHEC);

			throw businessException;
		}
	}

	@Override
	public ArticleVendu selectByIdArticle(Integer id) throws BusinessException {
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String nomArticle = rs.getString(1);
				String description = rs.getString(2);
				LocalDate dateDebut = null;
				rs.getDate(3);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(3).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(4).toLocalDate();
				}
				Integer prixInitial = rs.getInt(5);
				Integer prixVente = rs.getInt(6);
				Integer idUtil = rs.getInt(7);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(8);
				String libelle = rs.getString(9);
				String photo = rs.getString(10);
				
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(id, nomArticle, description, dateDebut, dateFin, prixInitial, prixVente,
						utilisateur, cat);
				article.setPhoto(photo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_ARTICLE_ECHEC);

			throw businessException;
		}
		return article;

	}

	@Override
	public List<ArticleVendu> selectAllArticle() throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(9);
				String libelle = rs.getString(10);
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, cat);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_ARTICLE);

			throw businessException;
		}
		return articles;
	}

	@Override
	public List<ArticleVendu> selectByNomArticleByCat(String nom, Integer id) throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM_BY_CAT);
			String recherche = "%" + nom + "%";
			pstmt.setString(1, recherche);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(9);
				String libelle = rs.getString(10);
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, cat);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOM_ECHEC);

			throw businessException;
		}
		return articles;

	}

	@Override
	public List<ArticleVendu> selectByNomArticleByCatSaufUtil(String nom, Integer idArticle, Integer idUtilisateur)
			throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM_BY_CAT_SAUF_UTIL);
			String recherche = "%" + nom + "%";
			pstmt.setString(1, recherche);
			pstmt.setInt(2, idArticle);
			pstmt.setInt(3, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(9);
				String libelle = rs.getString(10);
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, cat);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOM_ECHEC);

			throw businessException;
		}
		return articles;
	}

	@Override
	public List<ArticleVendu> selectByNomArticleByCatByUtil(String nom, Integer idArticle, Integer idUtilisateur)
			throws BusinessException {
		List<ArticleVendu> articles = null;
		ArticleVendu article = null;
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM_BY_CAT_BY_UTIL);
			String recherche = "%" + nom + "%";
			pstmt.setString(1, recherche);
			pstmt.setInt(2, idArticle);
			pstmt.setInt(3, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Integer noArticle = rs.getInt(1);
				String nomArticle = rs.getString(2);
				String description = rs.getString(3);
				LocalDate dateDebut = null;
				rs.getDate(4);
				if (!rs.wasNull()) {
					dateDebut = rs.getDate(4).toLocalDate();
				}
				LocalDate dateFin = null;
				rs.getDate(5);
				if (!rs.wasNull()) {
					dateFin = rs.getDate(5).toLocalDate();
				}
				Integer prixInitial = rs.getInt(6);
				Integer prixVente = rs.getInt(7);
				Integer idUtil = rs.getInt(8);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				Integer idCat = rs.getInt(9);
				String libelle = rs.getString(10);
				cat = new Categorie(idCat, libelle);

				article = new ArticleVendu(noArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixVente, utilisateur, cat);

				if (articles == null) {
					articles = new ArrayList<ArticleVendu>();
				}

				articles.add(article);

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOM_ECHEC);

			throw businessException;
		}
		return articles;
	}
}

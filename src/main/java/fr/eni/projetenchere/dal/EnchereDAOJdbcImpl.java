package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO{

private static final String SELECT_ENCHERE_BY_ID_ARTICLE = "SELECT  e.no_enchere,e.date_enchere, e.montant_enchere,e.no_article,e.no_utilisateur FROM enchere AS e WHERE no_article=?";
private static final String INSERT_ENCHERE = " INSERT INTO enchere (date_enchere, montant_enchere,no_utilisateur,no_article) VALUES (?,?,?,?)";
private static final String SELECT_MAX_ENCHERE= "select no_enchere, date_enchere, MAX(montant_enchere),no_utilisateur,no_article from enchere WHERE no_article=?";

	//	méthode pour insérer une enchère en BDD
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		if(enchere == null || enchere.getMontantEnchere() == null || enchere.getDateEnchere() == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_NULL);
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(4, enchere.getArticle().getNoArticle());
			
			int nb = pstmt.executeUpdate();
			if(nb>0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs != null && rs.next()) {
					enchere.setNoEnchere(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Enchere> selectEnchereById(ArticleVendu articleVendu) throws BusinessException {
		List<Enchere> result = new ArrayList<>();
		if(articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_PARAMETER_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_ID_ARTICLE);
			pstmt.setInt(1, articleVendu.getNoArticle());
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
			
				Integer idUtil = rs.getInt("no_utilisateur");
				Integer idArticle = rs.getInt("no_article");
				
				ArticleVendu articleVendu1 = (ArticleVendu) new ArticleVenduDAOJdbcImpl().selectByIdArticle(idArticle);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				result.add(new Enchere(rs.getInt("no_enchere"),rs.getTimestamp("date_enchere").toLocalDateTime(),rs.getInt("montant_enchere"),articleVendu1,utilisateur));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_SELECT_ENCHERE);
			throw businessException;
		}
		return result;
	}

	@Override
	public Enchere selectMaxEnchere(ArticleVendu articleVendu) throws BusinessException {
		Enchere ench = null;
		if(articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_PARAMETER_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MAX_ENCHERE);
			pstmt.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
			
				Integer idUtil = rs.getInt("no_utilisateur");
				Integer idArticle = rs.getInt("no_article");
				
				ArticleVendu articleVendu1 = (ArticleVendu) new ArticleVenduDAOJdbcImpl().selectByIdArticle(idArticle);
				Utilisateur utilisateur = (Utilisateur) new UtilisateurDAOJdbcImpl().selectByIdUtilisateur(idUtil);
				ench= new Enchere(rs.getInt("no_enchere"),rs.getTimestamp("date_enchere").toLocalDateTime(),rs.getInt(3),articleVendu1,utilisateur);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_SELECT_ENCHERE);
			throw businessException;
		}
		return ench;
	}


}

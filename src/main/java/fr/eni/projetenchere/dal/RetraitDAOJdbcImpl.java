package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String INSERT_RETRAIT = "INSERT INTO retrait (rue,code_postal,ville,no_article) VALUES (?,?,?,?)";

	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		if (retrait == null)
			try {
				{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_NULL);
					throw businessException;
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.setInt(4, retrait.getArticle().getNoArticle());

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
			throw businessException;
		}

	}

	private final String SELECT_RETRAIT_BY_IDARTICLE = "SELECT rue,code_postal,ville FROM retrait WHERE no_article = ?";

	@Override
	public Retrait selectByIdRetrait(Integer id) throws BusinessException {
		Retrait retrait = new Retrait();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_IDARTICLE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_RERTRAIT_ECHEC);
			throw businessException;
		}
		return retrait;
	}

	private static final String UPDATE_RETRAIT = "UPDATE retrait SET rue = ?,code_postal = ?,ville = ? WHERE no_article = ?";

	@Override
	public void UpdateRetrait(Retrait retrait) throws BusinessException {
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
			pstmt.setString(1,  retrait.getRue());
			pstmt.setString(2,  retrait.getCodePostal());
			pstmt.setString(3,  retrait.getVille());
			pstmt.setInt(4, retrait.getArticle().getNoArticle());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_ECHEC);
			throw businessException;
		}
	}

}

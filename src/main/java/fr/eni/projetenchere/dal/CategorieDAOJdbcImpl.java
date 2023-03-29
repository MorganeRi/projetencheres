package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String SELECT_CATEGORIE_BY_ID = "SELECT no_categorie,libelle FROM categorie WHERE no_categorie = ? ";
	private static final String GET_ALL_CATEGORIE = "SELECT libelle FROM categorie";

	@Override
	public List<Categorie> selectAllCategorie() throws BusinessException {
		List<Categorie> result = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_ALL_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				result.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.GET_ALL_CATEGORIE_ECHEC);
			throw businessException;
		}
		return result;
	}

	@Override
	public Categorie selectCategorieByID(Integer idCategorie) throws BusinessException {
		Categorie categorie = null ;
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
			pstmt.setInt(1, idCategorie);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String nomCategorie =rs.getString(1);
				categorie = new Categorie(idCategorie,nomCategorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();

//			businessException.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_BY_ID_ECHEC);
//			throw businessException;
		}
		return categorie;
	}
}

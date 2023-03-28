package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String GET_ALL_CATEGORIE = "SELECT no_categorie, libelle FROM categorie";

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
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return result;
	}
}

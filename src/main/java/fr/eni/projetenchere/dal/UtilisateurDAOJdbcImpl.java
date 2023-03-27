package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERTUTILISATEUR = "INSERT INTO utilisateur (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null)
			try {
				{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
					throw businessException;
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERTUTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			int tinyIntValue;
			boolean booleanValue = utilisateur.getAdministrateur();
			if (booleanValue == false) {
				tinyIntValue = 0;
			} else {
				tinyIntValue = 1;
			}
			stmt.setInt(11, tinyIntValue);
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur((rs.getInt(1)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}

	@Override
	public void connectUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur selectByIdUtilisateur(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMontantCredit(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

}

package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.BusinessException;
import fr.eni.projetenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	
	private static final String UPDATE_ACTIF_UTILISATEUR = "UPDATE utilisateur SET actif = ? WHERE no_utilisateur = ? ";
	private static final String INSERT_UTILISATEUR = "INSERT INTO utilisateur (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null)
			try {
				{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_NULL);
					throw businessException;
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
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
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}

	}

	private static final String SELECT_AUTHENTIFIER = "SELECT no_utilisateur, email, pseudo, administrateur, actif FROM utilisateur WHERE email = ? AND mot_de_passe = ?";

	@Override
	public void connectUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (utilisateur == null) {
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_AUTHENTIFICATION_NULL);
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_AUTHENTIFIER);

				pstmt.setString(1, utilisateur.getEmail());
				pstmt.setString(2, utilisateur.getMotDePasse());
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
					utilisateur.setEmail(rs.getString(2));
					utilisateur.setPseudo(rs.getString(3));
					Boolean estAdmin;
					if (rs.getInt(4)==1) {
						estAdmin = true;
					} else {
						estAdmin=false;
					}
					utilisateur.setAdministrateur(estAdmin);
					
					Boolean estActif = null;
					if (rs.getInt(5)==0) {
						estAdmin = true;
					} else {
						estAdmin=false;
					}
					utilisateur.setActif(estActif);
				} else {
					businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_MDP_ECHEC);
				}
			} catch (Exception e) {
				e.printStackTrace();

				businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_ECHEC);
			}
		}

		if (businessException.hasErreurs())
			throw businessException;

	}
	private static final String SELECT_AUTHENTIFIER_PSEUDO = "SELECT no_utilisateur, email, pseudo, administrateur, actif FROM utilisateur WHERE pseudo = ? AND mot_de_passe = ?";
	@Override
	public void connectUtilisateurPseudo(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (utilisateur == null) {
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_AUTHENTIFICATION_NULL);
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_AUTHENTIFIER_PSEUDO);

				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getMotDePasse());
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
					utilisateur.setEmail(rs.getString(2));
					utilisateur.setPseudo(rs.getString(3));
					Boolean estAdmin;
					if (rs.getInt(4)==1) {
						estAdmin = true;
					} else {
						estAdmin=false;
					}
					utilisateur.setAdministrateur(estAdmin);
					
					Boolean estActif = null;
					if (rs.getInt(5)==0) {
						estAdmin = true;
					} else {
						estAdmin=false;
					}
					utilisateur.setActif(estActif);
					
				} else {
					businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_MDP_ECHEC);
				}
			} catch (Exception e) {
				e.printStackTrace();

				businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_ECHEC);
			}
		}

		if (businessException.hasErreurs())
			throw businessException;
		
	}
	

	private static final String UPDATE_UTILISATEUR = "UPDATE utilisateur SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, administrateur = ? WHERE no_utilisateur = ? ";

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			int tinyIntValue;
			boolean booleanValue = utilisateur.getAdministrateur();
			if (booleanValue == false) {
				tinyIntValue = 0;
			} else {
				tinyIntValue = 1;
			}

			pstmt.setInt(10, tinyIntValue);
			pstmt.setInt(11, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	private static final String DELETE_UTILISATEUR = "DELETE FROM utilisateur WHERE no_utilisateur = ?";

	@Override
	public Utilisateur deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	private final String SELECT_UTILISATUEUR_BY_ID = "SELECT no_utilisateur,pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur,actif FROM utilisateur WHERE no_utilisateur = ?";

	@Override
	public Utilisateur selectByIdUtilisateur(Integer id) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATUEUR_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				int tinyIntValue = rs.getInt("administrateur");
				boolean booleanValue;
				if (tinyIntValue == 0) {
					booleanValue = false;
				} else {
					booleanValue = true;
				}
				utilisateur.setAdministrateur(booleanValue);
				
				Integer tinyUserActif = rs.getInt("actif");
				Boolean booleanActif = null; 
				
				if(tinyUserActif == 0) {
					booleanActif = true;
				} else {
					booleanActif = false;
				}
				utilisateur.setActif(booleanActif);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	private static final String UPDATE_CREDIT = "UPDATE utilisateur SET credit = ? WHERE no_utilisateur = ?";

	@Override
	public void updateMontantCredit(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT);
			pstmt.setInt(1, utilisateur.getCredit());
			pstmt.setInt(2, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
			throw businessException;
		}
	}

	private final String SELECT_UTILISATUEUR_BY_EMAIL = "SELECT no_utilisateur,pseudo, nom, prenom, email, telephone, rue, "
			+ "					code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateur WHERE email = ?";

	
	@Override
	public Utilisateur selectByEmailUtilisateur(String email) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATUEUR_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				int tinyIntValue = rs.getInt("administrateur");
				boolean booleanValue;
				if (tinyIntValue == 0) {
					booleanValue = false;
				} else {
					booleanValue = true;
				}
				utilisateur.setAdministrateur(booleanValue);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_EMAIL_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
	private final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT no_utilisateur,pseudo, nom, prenom, email, telephone, rue, "
			+ "					code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateur WHERE pseudo = ?";

	
	@Override
	public Utilisateur selectByPseudoUtilisateur(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				int tinyIntValue = rs.getInt("administrateur");
				boolean booleanValue;
				if (tinyIntValue == 0) {
					booleanValue = false;
				} else {
					booleanValue = true;
				}
				utilisateur.setAdministrateur(booleanValue);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_EMAIL_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	private static final String SELECT_USERS_FOR_ADMIN = "SELECT no_utilisateur,pseudo,nom,prenom,email,administrateur,actif FROM utilisateur";
	
	@Override
	public List<Utilisateur> selectAllUtilisateur() throws BusinessException {
		List<Utilisateur> result = new ArrayList<>();
		Integer tinyIntAdministrateur, tinyIntActif = null;
		Boolean booleanAdministrateur, booleanActif = null;
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USERS_FOR_ADMIN);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				tinyIntAdministrateur = rs.getInt("administrateur");
				if (tinyIntAdministrateur == 0) {
					booleanAdministrateur = false;
				} else {
					booleanAdministrateur = true;
				}
				utilisateur.setAdministrateur(booleanAdministrateur);
				tinyIntActif = rs.getInt("actif");
				if (tinyIntActif == 0) {
					booleanActif = true;
				} else if (tinyIntActif == 1){
					booleanActif = false;
				}
				utilisateur.setActif(booleanActif);
				result.add(utilisateur);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.GET_ALL_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return result;
	}

	@Override
	public Utilisateur actifOrNot(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ACTIF_UTILISATEUR_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ACTIF_UTILISATEUR);
			
			Integer intValueBoolean = null;
			
			
			if((utilisateur.getActif()).equals(true)) {
				intValueBoolean = 1;
				utilisateur.setActif(false);
			} else if ((utilisateur.getActif()).equals(false)) {
				intValueBoolean = 0;
				utilisateur.setActif(true);
			}
			pstmt.setInt(1, intValueBoolean);
			pstmt.setInt(2, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_USER_ACTIF_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

//	private static final String UPDATE_CREDIT = "UPDATE utilisateur SET credit = ? WHERE no_utilisateur = ?";

//	@Override
//	public void updateMontantCredit(Utilisateur utilisateur) throws BusinessException {
//		if (utilisateur == null) {
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_NULL);
//			throw businessException;
//		}
//		try (Connection cnx = ConnectionProvider.getConnection()) {
//			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT);
//			pstmt.setInt(1, utilisateur.getCredit());
//			pstmt.setInt(2, utilisateur.getNoUtilisateur());
//			pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
//			throw businessException;
//		}
//	}


}

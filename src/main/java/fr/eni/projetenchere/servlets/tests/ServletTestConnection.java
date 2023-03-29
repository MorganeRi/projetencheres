package fr.eni.projetenchere.servlets.tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestConnection
 */
@WebServlet("/ServletTestConnection")
public class ServletTestConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestConnection() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource dataSource = null;
		Connection cnx = null;
		ResultSet rs = null;
		
		Context context;
		try {
			Integer i = 1;
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			cnx = dataSource.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement("SELECT ?");
			pstmt.setInt(1, i);
			pstmt.execute(); 
			rs = pstmt.getResultSet();
			if(rs.next())
			{
				response.getWriter().append("Connexion à la base avec succès (res = " + rs.getInt(1) + ")");
				return;
			}
		} catch (NamingException e) {
			e.printStackTrace();
			response.getWriter().append("Impossible d'accéder à la base de données");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Impossible d'obtenir une connection valide");
			return;
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cnx != null) {
				try {
					cnx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

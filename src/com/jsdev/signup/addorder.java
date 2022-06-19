package com.jsdev.signup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addorder
 */
@WebServlet("/addorder")
public class addorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String service = request.getParameter("serviceID");
		String client = request.getParameter("userID");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsservices?useSSL=false","root","");
			PreparedStatement pst = con.prepareStatement("insert into orders(services_id,client_id) values(?,?)");
			pst.setString(1, service);
			pst.setString(2, client);			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("home.jsp");		
		
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();	
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	

}

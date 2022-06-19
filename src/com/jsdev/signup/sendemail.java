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
 * Servlet implementation class sendemail
 */
@WebServlet("/sendemail")
public class sendemail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String msg = request.getParameter("msg");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsservices?useSSL=false","root","");
			PreparedStatement pst = con.prepareStatement("insert into emails(email,msg) values(?,?)");
			pst.setString(1, email);
			pst.setString(2, msg);
		
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("admin.jsp");
			if(rowCount > 0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
		
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

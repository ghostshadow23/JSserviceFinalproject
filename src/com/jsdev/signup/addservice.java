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
 * Servlet implementation class addservice
 */
@WebServlet("/addservice")
public class addservice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String responses = request.getParameter("response");
		String nbr = request.getParameter("nbr");
		String nbr1 = request.getParameter("nbr1");
		String loyalty = request.getParameter("loyalty");
		String nbr2 = request.getParameter("nbr2");
		String image1 = request.getParameter("image1");
		String image2 = request.getParameter("image2");
		String link =request.getParameter("link");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsservices?useSSL=false","root","");
			PreparedStatement pst = con.prepareStatement("insert into services(title,description,price,response,nbr,nbr1,loyalty,nbr2,image1,image2,link) values(?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, title);
			pst.setString(2, description);
			pst.setString(3, price);
			pst.setString(4, responses);
			pst.setString(5, nbr);
			pst.setString(6, nbr1);
			pst.setString(7, loyalty);
			pst.setString(8, nbr2);
			pst.setString(9, image1);
			pst.setString(10, image2);
			pst.setString(11, link);
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("profileFclient.jsp");
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

package crudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/displaylink")

public class DisplayStudent extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root","sql@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from student_app";
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>Student Id</th>");
			pw.print("<th>Student Name</th>");
			pw.print("<th>Student Stream</th>");
			pw.print("<th>Date of Birth</th>");
			pw.print("</tr>");
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				String dob=rs.getString(4);
				pw.print("<tr>");
				pw.print("<td>"+id+"</td>");
				pw.print("<td>"+name+"</td>");
				pw.print("<td>"+stream+"</td>");
				pw.print("<td>"+dob+"</td>");
				pw.print("</tr>");
			}
			pw.print("/<table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

package crudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/addlink")

public class AddStudent extends HttpServlet{
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//fetch the data from HTML
		String id=req.getParameter("sid");
		String name=req.getParameter("sname");
		String stream=req.getParameter("sstream");
		String dob=req.getParameter("sdate");
		//parse 
		int sid=Integer.parseInt(id);
		Date date=Date.valueOf(dob);
		
		PreparedStatement pstmt=null;
		
		String query="insert into student_app values(?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, sid);
			pstmt.setString(2, name);
			pstmt.setString(3, stream);
			pstmt.setDate(4, date);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" record inserted successfully....!!!</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package crudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deletelink")

public class DeleteStudent extends HttpServlet{
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
		//parse 
		int sid=Integer.parseInt(id);
		
		PreparedStatement pstmt=null;
		
		String query="delete from student_app where student_id=(?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, sid);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" record deleted successfully....!!!</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

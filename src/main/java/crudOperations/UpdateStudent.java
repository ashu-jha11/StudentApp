package crudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/updatelink")

public class UpdateStudent extends HttpServlet{
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
		Date date=Date.valueOf("dob");
		
		PreparedStatement pstmt=null;
		
		String query="update student_app set student_name=(?),student_stream=(?),student_dateofbirth=(?) where student_id=(?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, stream);
			pstmt.setDate(3, date);
			pstmt.setInt(4, sid);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" record updated successfully....!!!</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

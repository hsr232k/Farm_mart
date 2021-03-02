
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateservlet")
public class updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		String name = request.getParameter("s");
		String pname = request.getParameter("menu");
		pname=pname.substring(0,pname.length()-1);
		System.out.println("pname");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement p = con.prepareStatement("update productinfo set cost='" + price + "',quantity='"+ date + "' where uid='" + name + "' and product='" + pname + "'");
			int r = p.executeUpdate();
			if (r > 0) {
				System.out.println("Updated");
				response.sendRedirect("farmerupdate.html");
			} else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
			System.out.println("-->" + e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

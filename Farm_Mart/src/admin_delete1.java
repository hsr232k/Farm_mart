import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin_delete1")
public class admin_delete1 extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("del");
		if(name.length()!=0)
		{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement p=con.prepareStatement("delete from customer_reg where uniqueid='"+name+"'");
			int r=p.executeUpdate();
			if(r>0)
			{
				System.out.println("Deleted");
				response.sendRedirect("admin_delete1.html");
			}
			else
			{
				System.out.println("Failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("-->"+e);
		}
		}
		else
		{
			response.sendRedirect("admin_delete1.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

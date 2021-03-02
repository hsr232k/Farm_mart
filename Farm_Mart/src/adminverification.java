import java.io.IOException;    
import java.io.PrintWriter;    
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.security.SecureRandom;
import java.util.*;

@WebServlet("/adminverification")
public class adminverification extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username+"  "+password);
		
		try{
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement ps=con.prepareStatement("select * from admintable where username='"+username+"'");
			ResultSet res=ps.executeQuery();
			int flag=0;
			while(res.next()){
				if(res.getString(2).equals(username) && res.getString(3).equals(password) ){
					flag=1;
						response.sendRedirect("admin_dashboard.html");
					}
				}
			if(flag==0){
				response.sendRedirect("Admin.html");
			}
		}
		catch(Exception e){
			System.out.print(e);
		}

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

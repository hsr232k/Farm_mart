/*  line 43 - set to redirect page*/

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

@WebServlet("/farmer_registration_verification")
public class farmer_registration_verification extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		
		String phonenumber = request.getParameter("phonenumber");
		System.out.println(phonenumber);
		String otp = request.getParameter("otp");
		
		try{
			PrintWriter out=response.getWriter();

			System.out.println(phonenumber+" "+otp);
			Class.forName("com.mysql.jdbc.Driver");    
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root", "root");
			PreparedStatement ps=con.prepareStatement("select * from farmer_reg where phonenumber='"+phonenumber+"'");
			ResultSet res=ps.executeQuery();
			System.out.println(phonenumber+" "+otp);
			out.println("<table>");
			out.println("<tr><td>Username</td><td>Password</td></tr>");
			while(res.next()){
				if(res.getString(13).equals(otp)){
					PreparedStatement ps1 = con.prepareStatement("update farmer_reg set status = 'success' where phonenumber= '"+phonenumber+"' ");
					ps1.executeUpdate();
					response.sendRedirect("FarmerSuccess.html");
					
				}
			}
			out.println("</table>");
		}
		catch(Exception e){
			System.out.print(e);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}

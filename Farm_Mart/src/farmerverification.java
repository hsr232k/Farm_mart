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

@WebServlet("/farmerverification")
public class farmerverification extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username+"  "+password);
		
		try{
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement ps=con.prepareStatement("select * from farmer_reg where firstname='"+username+"'");
			ResultSet res=ps.executeQuery();
			int flag=0;
			while(res.next()){
				if(res.getString(3).equals(username) && res.getString(6).equals(password) ){
					if(res.getString(12).equals("pending")){
						flag=1;
						response.sendRedirect("OTPVerify.html");
					}else{
						flag=1;
						String farmerphno = "+91"+res.getString(5);
						String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
					    String AUTH_TOKEN ="c8e56803e0a3a26ac14c623f3dc15f67";
					    String rand = "You have recently login to your account";
					    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
					    Message message = Message
				                .creator(new PhoneNumber(farmerphno), // to
				                        new PhoneNumber("+13026130392"), // from
				                        rand)
				                .create();
						response.sendRedirect("FarmerSuccess.html");
					}
				}else{
					response.sendRedirect("loginform.html");
				}
			}
			if(flag==0){
				response.sendRedirect("loginform.html");
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

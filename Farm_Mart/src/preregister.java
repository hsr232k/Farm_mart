import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@WebServlet("/preregister")
public class preregister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("uid");
		String product=request.getParameter("product");
		String quantity=request.getParameter("quantity");
		String message_quantity=quantity;
		String quant="";
		String code = "+91";
		String phonenumber="";
		String customer_address=request.getParameter("address");
		
		
		try{
			//PrintWriter out=response.getWriter();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement p=con.prepareStatement("select phonenumber from farmer_reg where uniqueid='"+uid+"'");
			ResultSet res=p.executeQuery();
			while(res.next()){
				phonenumber=res.getString("phonenumber");
				phonenumber = code+phonenumber;
				System.out.println(phonenumber);
			}

		}
		catch(Exception e){
			System.out.print(e);
		}
		
		try{
			//PrintWriter out=response.getWriter();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement p=con.prepareStatement("select quantity from productinfo where uid='"+uid+"' AND product='"+product+"'");
			ResultSet res=p.executeQuery();
			while(res.next()){
				quant=res.getString("quantity");
				
			}
			int a = Integer.parseInt(quant)-Integer.parseInt(quantity);
			quantity=Integer.toString(a);
			System.out.println(quant+"="+quantity);
			PreparedStatement ps=con.prepareStatement("update productinfo set quantity='"+quantity+"'where uid='"+uid+"' AND product='"+product+"'");
			ps.executeUpdate();
			String rand = product+" of "+quantity+ " kg " +"  is remaining -->"+customer_address+" <--is the address for delivery"+" : TEAM 6 :)";
			
			
			String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
		    String AUTH_TOKEN ="c8e56803e0a3a26ac14c623f3dc15f67";
		    
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    Message message = Message
	                .creator(new PhoneNumber(phonenumber), // to
	                        new PhoneNumber("+13026130392"), // from
	                        rand)
	                .create();
			response.sendRedirect("OrderSuccess.html");
			
		}
		catch(Exception e){
			System.out.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
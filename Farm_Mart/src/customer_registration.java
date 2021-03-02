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


@WebServlet("/customer_registration")
public class customer_registration extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phno");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm-password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String district = request.getParameter("district");
		String pincode = request.getParameter("pincode");
		String status = "pending";
		
		PrintWriter pw =response.getWriter();
		
		
		int uniqueid_flag = 1;
		String unq_id = "";
			
		SecureRandom rid = new SecureRandom();
		int uniqueid = rid.nextInt(999);
		String uniid = Integer.toString(uniqueid);
		unq_id = uniid;
			

		String num = "+91" + phonenumber;
		SecureRandom r = new SecureRandom();
		int randInRange = r.nextInt(99999);
		String rand = Integer.toString(randInRange);
		
		String otp = rand;
		
		//remove spaces
	String str = firstname+uniid;
	String arr[] = str.split(" ");
	int len = arr.length;
	System.out.println(len);
	firstname="";
	for(int i=0;i<len;i++){
		firstname += arr[i];
	}
	System.out.println(firstname);
	rand = rand + "  is your otp for login : TEAM 6 :)";
	
	
	String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
    String AUTH_TOKEN ="c8e56803e0a3a26ac14c623f3dc15f67";
    
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message
            .creator(new PhoneNumber(num), // to
                    new PhoneNumber("+13026130392"), // from
                    rand)
            .create();
    
    rand = firstname + " is your USERNAME for FARM MART";
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message1 = Message
            .creator(new PhoneNumber(num), // to
                    new PhoneNumber("+13026130392"), // from
                    rand)
            .create();
	    
	    
		
		
		try    
        {    
            Class.forName("com.mysql.jdbc.Driver");    
	
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root", "root");    
            String query="insert into customer_reg(firstname,uniqueid,email,phonenumber,password,gender,age,address,district,pincode,status,otp) values (?,?,?,?,?,?,?,?,?,?,?,?);";    
            PreparedStatement pstmt=con.prepareStatement(query);    
            pstmt.setString(1, firstname);
            pstmt.setString(2,unq_id);
            pstmt.setString(3, email);    
            pstmt.setString(4,phonenumber);    
            pstmt.setString(5, password);    
            pstmt.setString(6, gender);    
            pstmt.setString(7,age);
            pstmt.setString(8,address);
            pstmt.setString(9,district);
            pstmt.setString(10,pincode);
            pstmt.setString(11,status);
            pstmt.setString(12,otp);
                
            int x=pstmt.executeUpdate();    
                
            if(x==1)    
            {    
            response.sendRedirect("customerOTPVerify.html");   
            }    
                
        }    
        catch(Exception e)    
        {    
                e.printStackTrace();    
        }    
            
            
        pw.close();    
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	}





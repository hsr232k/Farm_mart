import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/farmeruploadjsp")
@MultipartConfig
public class farmeruploadjsp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pname=request.getParameter("pname");
		Part filepart=request.getPart("photo");
		String uid=request.getParameter("uid");
		String quantity=request.getParameter("quantity");
		String price=request.getParameter("price");
		String pincode="";
		String address="";
		String delivery="";
		
		InputStream is = null;
		if(filepart!=null)
		{
	        is=filepart.getInputStream(); 
		}
		String S="select * from farmer_reg where uniqueid='"+uid+"'";
		String sql="insert into productinfo (uid,product,cost,quantity,pincode,image,address,delivery) values (?,?,?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1","root","root");
			PreparedStatement q=con.prepareStatement(S);
			ResultSet res=q.executeQuery(); 
			while(res.next()){
				pincode=res.getString("pincode");
				address=res.getString("address");
				delivery=res.getString("delivery");
			}
			PreparedStatement p=con.prepareStatement(sql);
			if(is!=null)
			{
				p.setString(1, uid);
				p.setString(2, pname);
				p.setString(3, price);
				p.setString(4, quantity);
				p.setString(5, pincode);
				p.setBlob(6,is);
				p.setString(7,address);
				p.setString(8,delivery);
				
			}
			int r=p.executeUpdate();
			if(r>0)
			{
				System.out.println("File uploaded");
				response.sendRedirect("product_added.html");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
	


}
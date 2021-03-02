<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<br>

	<form method="post" action="updateservlet">
		<div style="padding: 5%;">
			<pre>  </pre>
		</div>
		<h3 style="margin-left: 35%;">Products Available :<h3>
				<select name="menu"	style="border: none; margin-left:35%; border-bottom: 1px solid #000; background: transparent; outline: none;  width:20%; color: #000; font-size: 16px;">

					<%
						String s = request.getParameter("val");
						if (s == null || s.trim().equals("")) {
							out.print("Please enter id");
						} else {
							String id = s.trim();
							try {

								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1", "root", "root");
								PreparedStatement ps = con.prepareStatement("select * from productinfo");
								ResultSet rs = ps.executeQuery();
								while (rs.next()) {
									if (rs.getString("uid").startsWith(id)) {
					%>
					<option value="<%=rs.getString("product")%>>">
						<%=rs.getString("product")%>
					</option>
					<%
						}
								}
								con.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					%>
				</select> <br>
				<h3 style="margin-left: 35%;">Price :</h3>
				<input type="hidden" name="s" value="<%=s%>" />
				<input type="text" name="price"
					style="width: 20%; border: none; margin-left: 35%; border-bottom: 1px solid #000; background: transparent; outline: none; height: 40px; color: #000; font-size: 16px;"><br>
				<h3 style="margin-left: 35%;">Quantity :</h3>
				<input type="text" name="date"
					style="width: 20%; border: none; margin-left: 35%; border-bottom: 1px solid #000; background: transparent; outline: none; height: 40px; color: #000; font-size: 16px;"><br>

				<input type="submit" value="Update"style="border: none; width: 20%; margin-left: 35%;  font-size: 16px;"><br>

				<div style="padding: 5%;">
					<pre>  </pre>
				</div>
	</form>


</body>
</html>
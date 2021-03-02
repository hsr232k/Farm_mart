<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="customer_search.css">
</head>
<body>
	<div class="header">
		<a href="index.html" class="logo">FARM MART</a>
		<div class="header-right">
			<a class="active" href="index.html">Home</a> <a href="AboutUs.html">About
				Us</a>
		</div>
	</div>
	<div id="createspace">
		<pre> </pre>
	</div>
	<table border="1" class="styled-table" style="margin-left: 5%;">
		<tr style="top: 20%;">
			<td>U ID</td>
			<td>Product name</td>
			<td>Price/kg</td>
			<td>Quantity</td>
			<td>Address</td>
			<td>Home Delivery</td>

		</tr>
		<%
			String uid = request.getParameter("uid");
			String product = request.getParameter("product");
			String pincode = request.getParameter("pincode");
			String username = request.getParameter("name_2");
			System.out.println(username+" is the user name");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1", "root", "root");
				Statement statement = connection.createStatement();
				String sql = "";

				if (uid.length() != 0 && product.length() != 0 && pincode.length() != 0) {
					sql = "select * from productinfo where uid='" + uid + "' AND product='" + product + "'AND pincode='"
							+ pincode + "'";
				} else if (uid.length() != 0 && product.length() != 0 && pincode.length() == 0) {
					sql = "select * from productinfo where uid='" + uid + "' AND product='" + product + "'";
				} else if (uid.length() != 0 && product.length() == 0 && pincode.length() != 0) {
					sql = "select * from productinfo where uid='" + uid + "' AND pincode='" + pincode + "'";
				} else if (uid.length() == 0 && product.length() != 0 && pincode.length() != 0) {
					sql = "select * from productinfo where pincode='" + pincode + "' AND product='" + product + "'";
				} else if (uid.length() != 0 && product.length() == 0 && pincode.length() == 0) {
					sql = "select * from productinfo where uid='" + uid + "'";
				} else if (uid.length() == 0 && product.length() != 0 && pincode.length() == 0) {
					sql = "select * from productinfo where product='" + product + "'";
				} else if (uid.length() == 0 && product.length() == 0 && pincode.length() != 0) {
					sql = "select * from productinfo where pincode='" + pincode + "'";
				}

				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>
		<tr>
			<td><%=resultSet.getString("uid")%></td>
			<td><%=resultSet.getString("product")%></td>
			<td><%=resultSet.getString("cost")%></td>
			<td><%=resultSet.getString("quantity")%></td>
			<td><%=resultSet.getString("address")%></td>
			<td><%=resultSet.getString("delivery")%></td>
		</tr>
		<%
			}
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
	<div class="loginbox">
		<form action="preregister" method="post">
			<label for="uid">U id:</label> <input type="text" name="uid"
				required="required"><br> <label for="product">Productname:</label>
			<input type="text" name="product" required="required"><br>
			<label for="quantity"> Quantity:</label> <input type="text"
				name="quantity" required="required"><br><label for="address">Your Address:</label> <input type="text" name="address"
				><br> <input
				type="submit" value="Order">
	</div>
	<div id="createspace1">
		<pre> </pre>
	</div>
	</form>
	</div>
	<div id="footer">
		<p>CREATED BY TEAM-6</p>
		<p>BRINGING GROWTH IN AGRI , A NEW AGRI PERSPECTIVE</p>
	</div>
</body>
</html>
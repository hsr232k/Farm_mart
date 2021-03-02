<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>
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
	<table border="1" class="styled-table" style="margin-left: 2%;">
		<tr>
			<td>Sno</td>
			<td>Unique Id</td>
			<td>UserName</td>
			<td>Email</td>
			<td>PhoneNumber</td>
			<td>Password</td>
			<td>Gender</td>
			<td>Age</td>
			<td>Address</td>
			<td>District</td>
			<td>Pincode</td>
			<td>Status</td>
		</tr>
		<%
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/farm_mart?characterEncoding=latin1", "root", "root");
				statement = connection.createStatement();
				String sql = "select * from customer_reg";
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>
		<tr>
			<td><%=resultSet.getString("sno")%></td>
			<td><%=resultSet.getString("uniqueid")%></td>
			<td><%=resultSet.getString("firstname")%></td>
			<td><%=resultSet.getString("email")%></td>
			<td><%=resultSet.getString("phonenumber")%></td>
			<td><%=resultSet.getString("password")%></td>
			<td><%=resultSet.getString("gender")%></td>
			<td><%=resultSet.getString("age")%></td>
			<td><%=resultSet.getString("address")%></td>
			<td><%=resultSet.getString("district")%></td>
			<td><%=resultSet.getString("pincode")%></td>
			<td><%=resultSet.getString("status")%></td>

		</tr>
		<%
			}
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
	<div class="loginbox2">
		<form action="admin_delete1">
			<h3>Delete User:</h3>
			<input type="text" name="del"><br> <input type="submit"
				value="     Delete     "
				style="border: none; margin-left:0%; margin-top:1%; outline: none; height: 40px; background: #fff; color: 18px; border-radius: 20px;  background: #929292; color: #000;">
		</form>
	</div>
	<div id="createspace">
		<pre> </pre>
	</div>
	<div id="footer">
		<p>CREATED BY TEAM-6</p>
		<p>BRINGING GROWTH IN AGRI , A NEW AGRI PERSPECTIVE</p>
	</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: chenguangming
  Date: 2021/8/12
  Time: 8:42 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.sql.*"%>
<html>
<head>
  <title>index</title>
</head>
<body>
<%
  String driver_name = "com.mysql.cj.jdbc.Driver";	//mysql数据库驱动
  String user_name = "root";						//访问数据库的用户
  String password = "secret";					//用户登陆密码
  String db_name="fundsys";						//要访问的数据库


  //url指定要访问的数据库和访问数据库的用户及密码
  String url = "jdbc:mysql://localhost:3306/"+db_name+"?user="+user_name+"&password="+password;
  //加载mysql数据库驱动
  Class.forName(driver_name);
  Connection conn = DriverManager.getConnection(url);

  Statement stmt = conn.createStatement();
  String sql = "select name,managers.full_name,positions.date,positions.quantity,securities.symbol from funds f left join positions on f.id = positions.position_id left join managers on f.manager_id = managers.id left join securities on positions.security_id = securities.id;";
  ResultSet rs = stmt.executeQuery(sql);


%>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table align="center" cellspacing="0" border="1">
  <tr>
    <th>FundsName</th>
    <th>Managers</th>
    <th>Date</th>
    <th>Quantity</th>
    <th>Symbol</th>
  </tr>
  <% while (rs.next()) { %>
  <tr>
    <td><%out.print(rs.getString(1));%></td>
    <td><%out.print(rs.getString(2));%></td>
    <td><%out.print(rs.getString(3));%></td>
    <td><%out.print(rs.getString(4));%></td>
    <td><%out.print(rs.getString(5));%></td>
  </tr>
  <% } %>

</table>
<div align="center">
  <a href="funds">[funds]</a>
  <a href="managers">[managers]</a>
  <a href="positions">[positions]</a>
  <a href="securities">[securities]</a>
</div>
</body>
</html>

</body>
</html>


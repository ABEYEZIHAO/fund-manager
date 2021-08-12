<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" isELIgnored="false"%>


<div style="margin:0px auto; width:500px">


<form action="../managers/${c.id}" method="post">
	<input type="hidden" name="_method" value="PUT">
	First Name: <input name="first_name" value="${c.firstName}"> <br>
	Last Name: <input name="last_name" value="${c.lastName}"> <br>
<button type="submit">submit</button>

</form>
</div>
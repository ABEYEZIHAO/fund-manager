<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" isELIgnored="false"%>


<div style="margin:0px auto; width:500px">


<form action="../positions/${c.getPositionId()}" method="post">
		<input type="hidden" name="_method" value="PUT">
security id: <input name="security_id" value="${c.getSecurityId()}"> <br>
<button type="submit">submit</button>

</form>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" isELIgnored="false"%>


<div style="margin:0px auto; width:500px">


<form action="../securities/${c.id}" method="post">
		<input type="hidden" name="_method" value="PUT">
symbol: <input name="symbol" value="${c.symbol}"> <br>
<button type="submit">submit</button>

</form>
</div>
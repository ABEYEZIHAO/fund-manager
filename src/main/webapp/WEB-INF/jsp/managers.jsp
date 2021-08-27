<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<script type="text/javascript" src="js/jquery.min.js"></script>

    <script type="text/javascript">
       /*将post method 改变为delete*/
      $(function(){                     
    	   $(".delete").click(function(){
    		   var href=$(this).attr("href");
    		   $("#formdelete").attr("action",href).submit();
    		   return false;
    	   })
       }) 
   </script>   
   
<div align="center">

</div>

<div style="width:500px;margin:20px auto;text-align: center">
	<table align='center' border='1' cellspacing='0'>
	    <tr>
	        <td>ID</td>
	        <td>NAME</td>
	        <td>EDIT</td>
	        <td>DELETE</td>
	    </tr>
	    <c:forEach items="${page.content}" var="c" varStatus="st">
	        <tr>
	            <td>${c.id}</td>
	            <td>${c.firstName} ${c.lastName}</td>
				<td><a href="managers/${c.id}">edit</a></td>
				<td><a class="delete" href="managers/${c.id}">delete</a></td>
	        </tr>
	    </c:forEach>
            	    
	</table>
	<br>
	<div>
	            <a href="?start=0">[first page]</a>
            <a href="?start=${page.number-1}">[prev]</a>
            <a href="?start=${page.number+1}">[next]</a>
            <a href="?start=${page.totalPages-1}">[last page]</a>
			<a href="index">[index]</a>
			<a href="managers">[funds]</a>
			<a href="positions">[positions]</a>
			<a href="securities">[securities]</a>
	</div>
	<br>
	<form action="managers" method="post">
		First Name: <input name="first_name"> <br>
		Last Name: <input name="last_name"> <br>
	<button type="submit">submit</button>
	
	</form>
	
	<form id="formdelete" action="" method="POST" >
       <input type="hidden" name="_method" value="DELETE">
   </form>
</div>

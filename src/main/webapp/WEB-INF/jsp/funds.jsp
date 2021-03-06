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
			<td>MANAGER</td>
	        <td>EDIT</td>
	        <td>DELETE</td>
	    </tr>
	    <c:forEach items="${page.content}" var="c" varStatus="st">
	        <tr>
	            <td>${c.id}</td>
	            <td>${c.name}</td>
				<td>${c.manager.firstName} ${c.manager.lastName}</td>
				<td><a href="funds/${c.id}">edit</a></td>
				<td><a class="delete" href="funds/${c.id}">delete</a></td>
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
	</div>
	<br>

	<form action="funds" method="post">
		Name: <input name="name">
		Manager ID: <input name="manager_id">
		Position ID: <input name="position_id">
	<br>

	<button type="submit">submit</button>
	</form>
	
	<form id="formdelete" action="" method="POST" >
       <input type="hidden" name="_method" value="DELETE">
   </form>
</div>

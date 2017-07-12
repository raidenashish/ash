<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->
		
		<script>
			function chg(){
				
				var val = document.getElementById("one").value;
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if(xhttp.readyState===4 && xhttp.status===200){
						document.getElementById("two").innerHTML=xhttp.responseText;
						
					}
				};
				xhttp.open("POST","Dropdown?valajax="+val,true);
				xhttp.send();
			}
		
		</script>
		<title>Post Query</title>
	</head>
	
<body>
	<form action = "PostQuery" method = "post">
	<ul class="form-style-1">
	<li>
        <label>Query<span class="required">*</span></label>
        <textarea name="question" id="question" class="field-long field-textarea" required></textarea>
    </li>
    
    
  
    <li>
				<label>Category(Optional)</label>
				<select id = "one" class="field-select" name = "assignCID" onchange="chg()">
					<option value="0">Select Category</option>
 					<c:forEach items="${categories}" var="category2">
 						<option value="${category2.CID}">${category2.catName}</option>
 					</c:forEach>
 				</select>
 	</li>
 			<li>
 				<div id = "two"> 
 					<label>SubCategory(Optional)</label>
 					<select class="field-select">
 					 </select>
 				</div>
 			</li>
    
       
    <li>
        <label>Email <span class="required"></span></label>
        <input type="email" name="email" class="field-long" value="${tmpQuery.emailUser}" required>
    </li>

    <li>
   		<span >
			<input type="submit" value="Submit" />
		</span>

</li>
</ul>
</form>
</body>
</html>

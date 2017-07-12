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

		
		<title>UserForm</title>
	</head>
	
<body>
	<form action = "addUser?q=addUser" method="post" id="AddDetails">
	<ul class="form-style-1">

		<li><label>Full Name <span class="required">*</span></label><input type="text" name="name" class="field-long" required/>&nbsp;</li>
		<li><label>Username<span class="required">*</span></label><input type="text" name="UID" class="field-long"required/>&nbsp;</li>
    	<li><label>Email <span class="required">*</span></label><input type="text" name="email" class="field-long" required/>&nbsp;</li>
    	<li><label>Department <span class="required">*</span></label><input type="text" name="department" class="field-long" required/>&nbsp;</li>
		<li><label>PhoneNo.<span class="required">*</span></label><input type="text" name="phoneNo" class="field-lomg" required/>&nbsp;</li>
    	<li><label>Password<span class="required">*</span></label><input type="password" name="password" class="field-long" required/>&nbsp;</li>
    	<li>
			<label>Assign Category</label>
			<input type="hidden" name="UID" value="${tmpSME.UID}">
			<select id = "one" class="field-select" name = "assignCID" onchange="chg()">
					<option>Select Category</option>
 					<c:forEach items="${categories}" var="category2">
 						<option value="${category2.CID}">${category2.catName}</option>
 					</c:forEach>
 				</select>
 		</li>
 		 <li>
		<label>Assign SubCategory</label>
 		<div id = "two"> 
 					<select class="field-select">
 					 </select>
 				</div>
		</li>
	 	<li>
        	<label>Admin</label>
        	<select  name="admin" class="field-select">
    	 	    <option value="0">No</option>
        		<option value="1">Yes</option>
        	</select>
    	</li>
    	<li>
   			<span >
				<input type="submit" value="Submit" >
			</span>
		</li>
	</ul>
</form>

</body>
</html>

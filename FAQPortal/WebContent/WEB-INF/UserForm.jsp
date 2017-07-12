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
			<script>
			function chg2(){
				
				var val = document.getElementById("three").value;
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if(xhttp.readyState===4 && xhttp.status===200){
						document.getElementById("four").innerHTML=xhttp.responseText;
						
					}
				};
				xhttp.open("POST","DeleteDropDown?valajax="+val,true);
				xhttp.send();
			}
			
		
		</script>

		
		<title>UserForm</title>
	</head>
	
<body>
	<form action = "UserForm?q=editUser" method = "post" id="EditDetails">
	<ul class="form-style-1">

		<li><label>Full Name <span class="required">*</span></label><input type="text" name="name" class="field-long" value =${tmpSME.name} required/></li>
    	<li><label>Email <span class="required">*</span></label><input type="text" name="email" class="field-long" value =${tmpSME.email} required/>&nbsp;</li>
    	<li><label>Department <span class="required">*</span></label><input type="text" name="department" class="field-long" value =${tmpSME.department} required/>&nbsp;</li>
		<li><label>PhoneNo.<span class="required">*</span></label><input type="text" name="phoneNo" class="field-lomg" value =${tmpSME.phoneNo} required/>&nbsp;</li>
    	
      
    		
    <li>
    	<input type="hidden" name="UID" value="${tmpSME.UID}">
        <label>Expert Topics  </label>
        <ol>
        	<c:forEach items="${subCategories}" var ="subcategory">
        		<c:choose>
        			<c:when test="${subcategory.UID == tmpSME.UID}">
        				<c:forEach items="${categories}" var="category">
        					<c:choose>
        						<c:when test="${category.CID == subcategory.CID}">
 		       						<li>
 		       							${subcategory.subCatName} (${category.catName})
 		       						</li>
 		       					</c:when>
        					</c:choose>
        				</c:forEach>
        			</c:when>
        		</c:choose>  
        	</c:forEach>
		</ol>
		</li>
		
		<li>
				<label>Assign Expert Topic</label>
				<select id = "one" class="field-select" name = "assignCID" onchange="chg()">
					<option value="0">Select Category</option>
 					<c:forEach items="${categories}" var="category2">
 						<option value="${category2.CID}">${category2.catName}</option>
 					</c:forEach>
 				</select>
 		</li>
 			
 		<li>
 			<div id = "two"> 
 				<select class="field-select"></select>
 			</div>
 		</li>
 		
    	
	<li>
				<label>Delete Expert Topic</label>
				<select id = "three" class="field-select" name = "deleteCID" onchange="chg2()">
					<option value = "0">Select Category</option>
 					<c:forEach items="${categories}" var="category3">
 						<option value="${category3.CID}">${category3.catName}</option>
 					</c:forEach>
 				</select>
 	</li>
 	
 	<li>
 		<div id = "four"> 
 			<select class="field-select"></select>
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
			<input type="hidden" name="UID" value="${tmpSME.UID}">
		</span>
	</li>
	

	</ul>
</form>
</body>
</html>

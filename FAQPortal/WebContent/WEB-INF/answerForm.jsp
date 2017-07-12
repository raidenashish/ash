<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->
		
		<title>Edit Query</title>
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
	</head>
	
<body>
	<form action = "AnswerForm?q=edit" method = "post">
	<ul class="form-style-1">
	<li>
        <label>Query<span class="required">*</span></label>
        <textarea name="question" id="question" class="field-long field-textarea" required>${tmpQuery.query}</textarea>
    </li>
    <li>
        <label>Answer</label>
        <textarea name="answer" id="answer" class="field-long field-textarea" required>${tmpQuery.answer}</textarea>
    </li>
   
    <li>
        <label>Category<span class="required">*</span></label>
       
        <select id = "one" name="category" class="field-select" onchange = "chg()" required>
    	   <c:choose>
				<c:when test = "${tmpQuery.getCategory().getCatName()!= NULL}">
					<option value="${tmpQuery.getCategory().getCID()}">${tmpQuery.getCategory().getCatName()}</option>
				</c:when>
			</c:choose>
        
       
       <c:forEach items="${categories}" var="category">
       		<c:choose>
        	        <c:when test="${tmpQuery.getCategory().getCatName() != category.catName}">
        				<option value="${category.CID}">${category.catName}</option>
        			</c:when>
        	</c:choose>
        </c:forEach>
        
        
        </select>
     
    </li>
 
    <li>
 				<div id = "two"> 
 				<label>SubCategory<span class="required">*</span></label>
 					<select class="field-select" required>
 					 </select>
 				</div>
 			</li>
    
       
    <li>
        <label>Email <span class="required"></span></label>
        <input type="email" name="email" class="field-long" value="${tmpQuery.emailUser}">
    </li>
      
     <li>
        <label>Publish</label>
       
        <select  name="publish" class="field-select" >
    	   <option value="0">No</option>
    	   <option value = "1">Yes</option> 
        </select>
     
    </li>
      <li>
   		<span >
			<input type="submit" value="Submit" />
			<input type="hidden" name="QID" value="${tmpQuery.QID}">
		</span>

	</li>
</ul>
 </form>
 
 <form action = "AnswerForm?q=delete" method = "post">
	<ul class="form-style-1">
	<li>
   		<span >
			<input type="submit" value="Delete" />
    		<input type="hidden" name="QID" value="${tmpQuery.QID}">
			
		</span>
	</li>
	</ul>
</form>
</body>
</html>

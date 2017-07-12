<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->
		
		<title>Edit Query</title>
		
	</head>
	
<body>
	<form action = "AnswerFormSME?q=edit" method = "post">
	<ul class="form-style-1">
	<li>
        <label>Query<span class="required">*</span></label>
        <textarea name="question" id="question" class="field-long field-textarea">${tmpSMEQuery.query}</textarea>
    </li>
    <li>
        <label>Answer<span class="required">*</span></label>
        <textarea name="answer" id="answer" class="field-long field-textarea">${tmpSMEQuery.answer}</textarea>
    </li>
   
    <li>
        <label>Category:-<span></span></label>
					${tmpSMEQuery.getCategory().getCatName()}
					
    </li>
    <li>
 		<label>Sub Category:-<span></span></label>
					${tmpSMEQuery.getSubCategory().getSubCatName()}
    
    	
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
			<input type="hidden" name="QID" value="${tmpSMEQuery.QID}">
			<input type="hidden" name="email" class="field-long" value="${tmpSMEQuery.emailUser}">
			<input type="hidden" name="UID" value="${tmpSMEQuery.assignedUID}">    
		</span>

	</li>
</ul>
 </form>
 
 
</body>
</html>

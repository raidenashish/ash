<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->
		
		<title>Edit Category</title>
	</head>
	
<body>
	<form  method = "post">
	<ul class="form-style-1">
	<li>
        <label>Edit Category<span class="required">*</span></label>
        <input type = "text" name = "category" value = "${tmpCategory.catName}">
        <input type = "hidden" name = "CID" value = "${tmpCategory.CID}"> 
    </li>
    <li> 
    	<label>SubCategory</label>
      	<select  name="subcategory">
    	   <c:forEach items="${subCategories}" var="subCategory">
       			<c:choose>
       				<c:when test="${subCategory.CID == tmpCategory.CID}">
       					<option value="${subCategory.subCatName}">${subCategory.subCatName}</option>
           			</c:when>
           		</c:choose>	
           </c:forEach>
        
        
        </select>
    </li>
    <li>
        <label>New SubCategory<span class="required"></span></label>
        <input type = "text" name = "newsubcategory"> 
    </li>
     <li>
   		<span >
			<input type="submit" value="Submit" formaction = "categoryForm?q=submit">
		</span>
		<span >
			<input type="submit" value="Delete SubCategory" formaction = "categoryForm?q=delete">
		</span>
	</li>
	</ul>
 </form>
</body>
</html>

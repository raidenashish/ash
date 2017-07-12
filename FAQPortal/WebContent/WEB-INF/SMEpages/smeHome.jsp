<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!doctype html>
<html lang="en" class="no-js">
<head>
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" type="text/css" href="CSS/basic.css">
	<link rel="stylesheet" type="text/css" href="CSS/table.css">
	<link rel="stylesheet" href="css1/reset.css"> 
	<link rel="stylesheet" type="text/css" href="css1/style.css">
	<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->
	<script src="js1/modernizr.js"></script> <!-- Modernizr -->
		<script type="text/javascript" src="JS/jquery-1.8.2.min.js">
		</script>
		<script type="text/javascript" src="JS/basic.js">
		</script>
		<script type="text/javascript" src="WEB-INF/JS/admin_js.js">
		</script>
		<title>Admin</title>
</head>
<body>
	<header>
	<h1>Frequently Asked Question</h1>
	
</header>
	<section class="cd-faq">
		<ul class="cd-faq-categories">	
				<li><a href="sme?q=home&UID=${smeUID}">Home</a></li>
				<li><a href="Logout">Logout</a></li>
		</ul>

	<div class="cd-faq-items">
		<div class="caption">Queries</div>	
<div id="table">
	<div class="header-row row">
    <span class="cell primary">Query</span>
    <span class="cell">Email</span>
     <span class="cell">Category</span>
    <span class="cell">Subcategory</span>
    <span class="cell">Answer</span>
    <span class="cell">Published</span>
 	</div>
  <c:forEach items="${smeQueries}" var="query">
  <div class="row">
	<input type="radio" name="expand">
	<c:choose>
		<c:when test="${query.answer == NULL}"> 
			<span class="cell primary" data-label="Query">
				<a href="AnswerFormSME?q=${query.QID}">${query.query}</a>
			</span>
     		<span class="cell" data-label="Email">
     			${query.emailUser}
     		</span>
    		<span class="cell" data-label="Category">
     			${query.getCategory().getCatName()}
     		</span>
     		<span class="cell" data-label="subCategory">
     			${query.getSubCategory().getSubCatName()}
     		</span>
     		<span class="cell" data-label="Answer">
     			${query.answer}
     		</span>
     		<span class="cell" data-label="Published">
     			${query.isPublishFlag()}
     		</span>
     		
		
			
		</c:when>
	</c:choose>
  </div>
  </c:forEach>
  <c:forEach items="${smeQueries}" var="query">
  <div class="row">
	<input type="radio" name="expand">
	<c:choose>
		<c:when test="${query.answer != NULL}"> 
			<span class="cell primary" data-label="Query">
				<a href="AnswerFormSME?q=${query.QID}">${query.query}</a>
			</span>
     		<span class="cell" data-label="Email">
     			${query.emailUser}
     		</span>
    		<span class="cell" data-label="Category">
     			${query.getCategory().getCatName()}
     		</span>
     		<span class="cell" data-label="subCategory">
     			${query.getSubCategory().getSubCatName()}
     		</span>
     		<span class="cell" data-label="Answer">
     			${query.answer}
     		</span>
     			<span class="cell" data-label="Published">
     			${query.isPublishFlag()}
     		</span>
     	</c:when>
	</c:choose>
  </div>
  </c:forEach>
</div>

</div> <!-- cd-faq-items -->
<a href="#0" class="cd-close-panel">Close</a>
</section> <!-- cd-faq -->
	
	
		
	
</body>
</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="css1/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css1/style.css"> <!-- Resource style -->
	<script src="js1/modernizr.js"></script> <!-- Modernizr -->
	<title>FAQ</title>
</head>
<body>
<header>
	<h1>Frequently Asked Question</h1>
	<h2><a href="Home.jsp">Home</a></h2>
</header>


<section class="cd-faq">
	<ul class="cd-faq-categories">
		<c:forEach items="${categories}" var="category">
			<li><a href="#${category.catName}">${category.catName}</a></li>
		</c:forEach>
		
		
		
	</ul> <!-- cd-faq-categories -->
	
	<div class="cd-faq-items">
	
	<c:forEach items="${categories}" var="category">
		<ul id="${category.catName}" class="cd-faq-group">
			<li class="cd-faq-title"><h2>${category.catName}</h2></li>
			<c:forEach items="${subCategories}" var="subCategory">
				<c:choose>
  					<c:when test="${subCategory.CID == category.CID}">
						<li>
							<a class="cd-faq-trigger" href="#0">${subCategory.subCatName}</a>
								<div class="cd-faq-content">
									<c:forEach items="${queries}" var="question">
											<c:choose>
												<c:when test="${question.isPublishFlag() == 1 && question.answer!=NULL}">
												<c:choose>  
													<c:when test = "${question.getCategory().getCatName() == category.catName}">
														<c:choose>
															<c:when test="${question.getSubCategory().getSubCatName() == subCategory.subCatName}">
																<p> Q.${question.query} <br> Ans.${question.answer}<br><br></p>
															</c:when>	
														</c:choose>
													</c:when>
												</c:choose>	
												</c:when>
											</c:choose>									
										
									</c:forEach>
								</div> <!-- cd-faq-content -->
						</li>
					</c:when>
  				</c:choose>
			</c:forEach>
			</ul> 
	</c:forEach>
		<!-- cd-faq-group -->
	
	
		
	</div> <!-- cd-faq-items -->
	<a href="#0" class="cd-close-panel">Close</a>
</section> <!-- cd-faq -->
<script src="js1/jquery-2.1.1.js"></script>
<script src="js1/jquery.mobile.custom.min.js"></script>
<script src="js1/main.js"></script> <!-- Resource jQuery -->
</body>
</html>
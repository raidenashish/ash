<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="css1/reset.css"> 
	<link rel="stylesheet" type="text/css" href="css1/style.css">
	<link rel="stylesheet" type="text/css" href="css1/sme.css">
	<link rel="stylesheet" href="css1/form.css"> <!-- CSS reset -->

	<script src="js1/modernizr.js"></script> <!-- Modernizr -->
	<script type="text/javascript" src="JS/jquery-1.8.2.min.js"></script>
	
	<title>SME List</title>
</head>
<body>
	<header>
	<h1>Frequently Asked Question</h1>
	
</header>
	<section class="cd-faq">
		<ul class="cd-faq-categories">	
				<li><a href="admin?q=Home">Home</a></li>
				<li><a href="admin?q=category">Category</a></li>
				<li><a href="FetchUsers">SME</a></li>
				<li><a href = "ChangePassword">Change Password</a></li>
				<li><a href="Logout">Logout</a></li>
		</ul>
		<div class="cd-faq-items">
			<form id="login_form" name="login_form" action ="ChangePassword" method="post">
			<br><br>
			USER ID:<br />
			<input type="text" name="username" required>
			<br>
			PASSWORD:<br>
			<input type="PASSWORD" name="password" required>
			<br><br>
			<button type="submit">Update Password</button> 
		</form>
		</div>
	</section>
</html>
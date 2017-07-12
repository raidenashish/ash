<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/basic.css">
		<script type="text/javascript" src="JS/jquery-1.8.2.min.js">
		</script>
		<script type="text/javascript" src="JS/basic.js">
		</script>
<title>Login</title>
</head>
	<body style="margin: 0px;">
		<div id="heading_bitspilani">
			 FREQUENTLY  ASKED QUESTIONS<br /><p class="bits_pilani"> BITS, PILANI</p>
		</div>
		<div id="view_options">
			<div class="options_class"><a href="Home.jsp">HOME</a></div>
			<div class="options_class"><a href="Fetch">FAQs</a></div>
			<div id="HOD" class="options_class"><a href="Login.jsp">LOGIN</a></div>
			<div class="options_class"><a href="PostQuery.jsp">Post Query</a></div>
			<div class="options_class"><a href="http://www.bits-pilani.ac.in/" > BITS HOME</a></div>
		</div>
		<form id="login_form" name="login_form"  action ='Login' method="post">
		<br><br>
			USER ID:<br />
			<input type="text" name="userid" class="text_properties" required>
			<br>
			PASSWORD:<br>
			<input type="PASSWORD" name="password" class="text_properties" required>
			<br><br>
			<button type="submit">LOGIN</button> 
		</form>

	</body>
</html>
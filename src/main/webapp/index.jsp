<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authorization required</title>
<style>
label {display:block}
</style>
</head>

<body>
	<form action="login" method="get">
		<label>Username:<input type="text" name="username" required></label>
		<label>Password:<input type="password" name="pass" required></label>
				
		<input type="submit" value="Log in">
	</form>
	<p><a href="/registration.jsp">No account yet? Come on, register now!</a></p>
</body>
</html>
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
	<form action="register" method="get">
		<label>Username:<input type="text" name="username"></label>
		<label>Password:<input type="password" name="pass"></label>
		<label>Confirm password:<input type="password" name="passconfirm"></label>
		<label>Email:<input type="email" name="email"></label>
				
		<input type="submit" value="Register">
	</form>
	<p><a href="/">Already registered? Go and log in yourself!</a></p>
</body>
</html>
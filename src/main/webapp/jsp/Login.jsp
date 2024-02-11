<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
	<style>
		div {
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
		}
		.btn{
		background-color: green;
		}
		.btn1{
		background-color: powderblue;
		}
		.btn2{
		background-color: red;
		}
		
	</style>

</head>

<body>
<h3 style="color:green">${pass}</h3>
<div>
		<h1>Todo Login</h1>
		<!-- action is address where the values are carried to  -->
		<form action="login" method="post">
			<fieldset>
				<legend>Login here,</legend>
				<table>
					<tr>
						<th>Email: </th>
						<!-- Name attribute is Complusory to carry value  -->
						<th><input type="email" name="email"></th>
					</tr>
					<tr>
						<th>Password: </th>
						<!-- Name attribute is Complusory to carry value  -->
						<th><input type="password" name="password"></th>
					</tr>
					<tr>
						<!-- Clicking this button will carry the values to action Location  -->
						<th><button class="btn1">Login</button></th>
						<th><button type="reset" class="btn2">Cancel</button></th>
					</tr>
					<tr>
						<!-- This is for Loading Signup Page -->
						<th colspan="2"><a href="signup"><button type="button" class="btn">Click to Create Account</button></a>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>
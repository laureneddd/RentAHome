<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div style="font-size: 50px; color: #FF5A5F; text-align: center;">Welcome
		To RentAHome</div>
	<div style="text-align: center; font-size: 30px; color: #00A699">Provide
		your Details for Registration</div>
	<div style="margin-left: 500px; margin-right: 500px">
		<form action="/register" method="post">

			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Username</label>
				<input type="text" class="form-control"
					id="exampleFormControlInput1" placeholder="Provide your Name"
					name="name">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Password</label>
				<input type="text" class="form-control"
					id="exampleFormControlInput1" placeholder="Provide your Password"
					name="password">
			</div>

			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Email
					Address</label> <input type="text" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Provide your Email Address" name="email">
			</div>

			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Choose your Role</label> 
			
			<select name="role">
				<option value="Owner">Owner</option>
				<option value="Customer">Customer</option>
			</select>
			</div>

			<div style="font-family: Arial, sans-serif; font-size: 16px; color: #333; font-weight: bold;">
				By selecting Register, I agree to RentAHome's Terms of Service, Payments Terms of Service, and Nondiscrimination Policy and acknowledge the Privacy Policy.
			</div>

			<button style="margin-left: 100px" type="submit"
				class="btn btn-primary">Register</button>


		</form>

			<br> <br> <a style="margin-left: 50px" href="login">I am already
							member</a>
	</div>
</body>
</html>
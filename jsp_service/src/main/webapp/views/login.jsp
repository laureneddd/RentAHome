<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Rent A Home Application</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous">
</head>
<body>


<div style="margin-left: 280px; font-size: 50px; color: red;">Welcome
    To Rent A Home Application</div>


${unauthorizedMessage }
${unauthenticatedMessage }

<div style="margin-left: 500px; color: green;">${registerSuccess}</div>
<div style="margin-left: 500px; color: red;">${loginFailedStatus}</div>
<div style="margin-left: 500px; color: red;">${updateMsg}</div>
<form action="/user_login" method="post">
    <div style="margin-left: 500px; margin-right: 500px">
        <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">name</label>
            <input type="text" class="form-control" id="exampleFormControlInput1"
                   placeholder="Provide your name" name="name">
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput2" class="form-label">password</label>
            <input type="text" class="form-control" id="exampleFormControlInput2"
                   placeholder="Provide your Password" name="password">
        </div>

        <button style="margin-left: 100px" type="submit"
                class="btn btn-primary">Login</button>
    </div>
</form>
<br>
<br> <a style="margin-left: 50px" href="/signup_page">New
    User? Register Here!!</a>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>


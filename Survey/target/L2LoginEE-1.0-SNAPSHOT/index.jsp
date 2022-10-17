<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Survey</title>
</head>
<body>
<% String login = (String) session.getAttribute("user_login"); %>
<% if (login == null || "".equals(login)) { %>
<form action="/login" method="POST">
    <fieldset>
        <legend>Sign in</legend>
        <br>
        <label for="login">Login</label>
        <input type="text" id="login" name="login">
        <br>
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <br>
        <input type="submit" value="Send">
    </fieldset>
    <br>
    <label>To registrate click</label>
    <a href="/login?a=signUp">Sign UP</a>
    <br>
</form>

<%String status = (String) session.getAttribute("sign_in_status");%>
<% if ("wrong".equals(status)) { %>
<h3>You login or password is wrong. Please try again or registrate</h3>
<%}%>
<% } else { %>
<h1>You are logged in as: <%= login %>
</h1>
<br>Click this link to <a href="/login?a=exit">logout</a>
<% } %>
</body>
</html>
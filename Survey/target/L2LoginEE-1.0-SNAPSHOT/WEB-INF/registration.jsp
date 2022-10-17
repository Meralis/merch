
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/login" method="POST">
    <fieldset>
        <legend>Sign up</legend>
        <label for="surname">Input your surname</label>
        <input type="text" id="surname">
        <br>
        <label for="name">Input your name</label>
        <input type="text" id="name">
        <br>
        <label for="age">Input your age</label>
        <input type="text" id="age">
        <br>
        <label for="login">Input your login</label>
        <input type="text" id="login">
        <br>
        <label for="password">Input your password</label>
        <input type="password" id="password">
        <br>
        <input type="submit" value="Send">
        <br>
    </fieldset>
</form>
</body>
</html>

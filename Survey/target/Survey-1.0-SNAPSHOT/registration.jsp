<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/signup" method="POST">
    <fieldset>
        <legend>Sign up</legend>
        <label for="surname">Input your surname</label>
        <input type="text" id="surname" name="surname" required>
        <br>
        <label for="name">Input your name</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="age">Input your age</label>
        <input type="text" id="age" name="age" required>
        <br>
        <label for="login">Input your login</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Input your password</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Send">
        <br>
    </fieldset>
</form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<form action="/survey" method="POST">
    <h3 id="question1">Choose a course</h3>
    <input type="radio" name="course" value="question1answerA">a) Front-end dev
    <br>
    <input type="radio" name="course" value="question1answerB">b) Back-end dev
    <br>
    <input type="radio" name="course" value="question1answerC">c) Full-stack dev
    <br>
    <h3 id="question2">Choose a learning format</h3>
    <input type="radio" name="learning" value="question2answerA">a) Online group
    <br>
    <input type="radio" name="learning" value="question2answerB">b) Offline group
    <br>
    <input type="radio" name="learning" value="question2answerC">c) Individual
    <br>
    <h3 id="question3">Choose a days</h3>
    <input type="radio" name="days" value="question3answerA">a) Tuesday/Thursday
    <br>
    <input type="radio" name="days" value="question3answerB">b) Wednesday/Friday
    <br>
    <input type="radio" name="days" value="question3answerC">c) Saturday/Sunday
    <br>
    <p><input type="submit" name="formSend" value="Send"></p>

    <table width="10%" border="2">
        <caption>Statistics</caption>
        <tr>
            <th></th>
            <th>a</th>
            <th>b</th>
            <th>c</th>
        </tr>
        <tr>
            <th>Course</th>
            <td align=center>${question1answerA}</td>
            <td align=center>${question1answerB}</td>
            <td align=center>${question1answerC}</td>
        </tr>
        <tr>
            <th>Learning format</th>
            <td align=center>${question2answerA}</td>
            <td align=center>${question2answerB}</td>
            <td align=center>${question2answerC}</td>
        </tr>
        <tr>
            <th>Days</th>
            <td align=center>${question3answerA}</td>
            <td align=center>${question3answerB}</td>
            <td align=center>${question3answerC}</td>
        </tr>
    </table>
</form>
<br>


<%--    <% String login = (String)session.getAttribute("user_login"); %>--%>

<%--    <% if (login == null || "".equals(login)) { %>--%>
<%--        <form action="/login" method="POST">--%>
<%--            Login: <input type="text" name="login"><br>--%>
<%--            Password: <input type="password" name="password"><br>--%>
<%--            <input type="submit" />--%>
<%--        </form>--%>
<%--    <% } else { %>--%>
<%--        <h1>You are logged in as: <%= login %></h1>--%>
<%--        <br>Click this link to <a href="/login?a=exit">logout</a>--%>
<%--    <% } %>--%>
</body>
</html>

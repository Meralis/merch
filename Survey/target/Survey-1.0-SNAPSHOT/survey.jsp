<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Survey</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/survey" method="POST">
    <fieldset>
        <h3>Во сколько вы просыпаетесь:</h3>
        <input type="radio" name="awake" id="question1answerA" value="question1answerA">
        <label for="question1answerA">a) До 07:00</label>
        <br>
        <input type="radio" name="awake" id="question1answerB" value="question1answerB">
        <label for="question1answerB">b) После 07:00</label>
        <h3>Во сколько вы засыпаете:</h3>
        <input type="radio" name="sleep" id="question2answerA" value="question2answerA">
        <label for="question2answerA">a) До 23:00</label>
        <br>
        <input type="radio" name="sleep" id="question2answerB" value="question2answerB">
        <label for="question2answerB">b) После 23:00</label>
        <br>
        <button type="submit">Send</button>
    </fieldset>
    <table>
        <caption>All users statistics</caption>
        <tr>
            <th></th>
            <th>a</th>
            <th>b</th>
        </tr>
        <tr>
            <th>Во сколько вы просыпаетесь:</th>
            <td>${question1answerA}</td>
            <td>${question1answerB}</td>
        </tr>
        <tr>
            <th>Во сколько вы засыпаете:</th>
            <td>${question2answerA}</td>
            <td>${question2answerB}</td>
        </tr>
    </table>

    <table>
        <caption>Your statistics</caption>
        <tr>
            <th></th>
            <th>a</th>
            <th>b</th>
        </tr>
        <tr>
            <th>Во сколько вы просыпаетесь:</th>
            <td>${q1aA}</td>
            <td>${q1aB}</td>
        </tr>
        <tr>
            <th>Во сколько вы засыпаете:</th>
            <td>${q2aA}</td>
            <td>${q2aB}</td>
        </tr>
    </table>
</form>
<% String login = (String) session.getAttribute("user_login"); %>
<h3>You are logged in as: <%= login %>
</h3>
<br>Click this link to <a href="/survey?a=exit">logout</a>
</body>
</html>

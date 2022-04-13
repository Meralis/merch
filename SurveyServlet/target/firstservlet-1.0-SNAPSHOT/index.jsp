<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<form action="survey" method="POST">
    <h3 id="question1">Choose a course</h3>
    <input type="radio" id="question1answerA" name="course" value="question1answerA">
    <label for="question1answerA">a) Front-end dev</label>
    <br>
    <input type="radio" id="question1answerB" name="course" value="question1answerB">
    <label for="question1answerB">b) Back-end dev</label>
    <br>
    <input type="radio" id="question1answerC" name="course" value="question1answerC">
    <label for="question1answerC">c) Full-stack dev</label>
    <br>
    <h3 id="question2">Choose a learning format</h3>
    <input type="radio" id="question2answerA" name="learning" value="question2answerA">
    <label for="question2answerA">a) Online group</label>
    <br>
    <input type="radio" id="question2answerB" name="learning" value="question2answerB">
    <label for="question2answerB">b) Offline group</label>
    <br>
    <input type="radio" id="question2answerC" name="learning" value="question2answerC">
    <label for="question2answerC">c) Individual</label>
    <br>
    <h3 id="question3">Choose a days</h3>
    <input type="radio" id="question3answerA" name="days" value="question3answerA">
    <label for="question3answerA">a) Tuesday/Thursday</label>
    <br>
    <input type="radio" id="question3answerB" name="days" value="question3answerB">
    <label for="question3answerB">b) Wednesday/Friday</label>
    <br>
    <input type="radio" id="question3answerC" name="days" value="question3answerC">
    <label for="question3answerC">c) Saturday/Sunday</label>
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
</body>
</html>

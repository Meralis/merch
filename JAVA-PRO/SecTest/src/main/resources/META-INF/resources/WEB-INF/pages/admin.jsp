<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>
<body>
<div align="center">
    <h1>Secret page for admins only!</h1>

    <p>Click to go back: <a href="/">back</a></p>

    <c:url value="/logout" var="logoutUrl"/>
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>

    <button type="button" id="add_user">Add</button>
    <button type="button" id="delete_user">Delete</button>
    <c:url value="/prepareUserForUpdate" var="prepareUserForUpdateUrl"/>
    <form action="${prepareUserForUpdateUrl}" method="GET">
        <table border="1">
            <thead>
            <tr>
                <th></th>
                <th>LOGIN</th>
                <th>ROLE</th>
                <th>EMAIL</th>
                <th>PHONE</th>
                <th>ADDRESS</th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
            <tbody>
            <tr>
                <td><input type="checkbox" name="toDelete" value="${user.id}" id="check_${user.id}"></td>
                <td><input type="submit" name= "usernameToUpdate" value="${user.login}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><c:out value="${user.address}"/></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

<script>
    $('#add_user').click(function () {
        window.location.href = "/register";
    });

    $('#delete_user').click(function () {
        var data = {'toDelete': []};
        $(":checked").each(function () {
            data['toDelete'].push($(this).val());
        });
        $.post("/delete", data, function (data, status) {
            window.location.reload();
        });
    });

</script>
</body>
</html>

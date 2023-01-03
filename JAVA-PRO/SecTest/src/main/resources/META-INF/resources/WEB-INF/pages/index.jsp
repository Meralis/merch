<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">


    <c:if test="${username eq null}">
        <h1>Profile username: <c:out value="${pageContext.request.userPrincipal.name}"/>, roles are:</h1>
    </c:if>

    <c:if test="${username ne null}">
        <h1>Profile username: ${username}, roles are:</h1>
    </c:if>

    <c:forEach var="s" items="${roles}">
        <h3><c:out value="${s}"/></h3>
    </c:forEach>

    <c:if test="${admin}">
        <c:url value="/admin" var="adminUrl"/>
        <p><a href="${adminUrl}">Click</a> for admin page</p>
    </c:if>

    <c:url value="/update" var="updateUrl"/>

    <c:if test="${username eq null}">
        <c:set var="username" value="${login}"/>
    </c:if>

    <form action="${updateUrl}" method="POST">
        Login:<br/><input type="text" name="username" readonly value="${username}"/><br/>
        E-mail:<br/><input type="text" name="email" value="${email}"/><br/>
        Phone:<br/><input type="text" name="phone" value="${phone}"/><br/>
        Address:<br/><input type="text" name="address" value="${address}"/><br/>
        <input type="submit" value="Update"/>
    </form>

    <c:url value="/logout" var="logoutUrl"/>
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>
</body>
</html>

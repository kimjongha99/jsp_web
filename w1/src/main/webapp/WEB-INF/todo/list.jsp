<%--
  Created by IntelliJ IDEA.
  User: 킴
  Date: 2023-07-27
  Time: 오전 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>list page</h1>

<c:forEach var="dto" items="${list}">
    <li>${dto}</li>
</c:forEach></body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>
<h2>${appName}</h2>
<h3>
    ${loginInfo}
</h3>
<h2>
    ${loginInfo.mname}
</h2>
<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료 ": "비완료"}</span>
        </li>
    </c:forEach>
</ul>

<form action="/logout" method="post">
    <button>LOGOUT</button>
</form>
</body>
</html>
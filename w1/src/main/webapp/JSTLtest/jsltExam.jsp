<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>JSTL 종합 예제</title>
</head>
<body>
<h2>JSTL 종합 예제</h2>

<hr>
<h3>set, out</h3>
<c:set var="product1" value="<h2>애플 아이폰</h2>" />
<c:set var="product2" value="삼성 갤럭시 노트" />
<c:set var="intArray" value="${[1,2,3,4,5]}" />

<hr>

</body>
</html>
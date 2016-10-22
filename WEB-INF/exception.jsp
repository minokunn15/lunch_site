<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/style.css">
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/exception.css">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
<meta name="viewport" content="width=360,initial-scale=1">
<title>エラーページ</title>
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />
	<h1><c:out value="${message}"/></h1>
	<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
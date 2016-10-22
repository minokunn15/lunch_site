<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="ログアウトページです。"/>
<title>ログアウト</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/logout.css">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
<meta name="viewport" content="width=360,initial-scale=1">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />
	<main>
		 <h2>ログアウトしました。</h2>
		 <button type="button" class="round" onclick="location.href='./Index'">
		 	メインページに戻る
		 </button>
	 </main>
	 <c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
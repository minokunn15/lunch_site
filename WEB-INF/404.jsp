<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="認証失敗ページです。"/>
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
<title>認証失敗</title>
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/style.css">
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/logout.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script src="/tyoushoku/js/jquery-1.8.3.js"></script>
<script src="/tyoushoku/js/jquery.sidr.min.js"></script>
<script src="/tyoushoku/js/sider.js"></script>
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />
	<main>
		 <h2>指定されたファイルがありません。(404)</h2>
		 <button type="button" class="round" onclick="location.href='/tyoushoku//Controller/Index'">
		 	メインページに戻る
		 </button>
	 </main>
	 <c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
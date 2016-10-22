<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="朝食記事投稿完了画面です。"/>
<title>ライターさん朝食記事投稿完了</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/logout.css">
<script src="js/jquery-1.8.3.js"></script>
<script src="js/jquery.sidr.min.js"></script>
<script src="js/sider.js"></script>
<link rel="stylesheet" href="../css/stylesheets/jquery.sidr.dark.css">
<link rel="stylesheet" href="../css/top_back.css">
<meta name="viewport" content="width=360,initial-scale=1">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />

    <h2>記事追加完了しました。</h2>

    <form action="./WriterIndex" method="post">
        <input type="submit" class="round" value="トップページに戻る">
    </form>

	<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
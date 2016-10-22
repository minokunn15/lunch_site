<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description"
	content="記事の詳細ページです。詳しい記事を見て自身の朝食に関するアドバイスとしてご活用ください。" />
<title>個別記事詳細(<c:out value="${detailNews.title}" />)
</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/detail.css">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script type="text/javascript" charset="utf-8"
	src="../js/jquery-1.8.3.js"></script>
</head>

<body>
	<c:import url="/WEB-INF/header.jsp" />

	<main id="main">
	<section id="detail">
		<h2>
			<c:out value="${detailNews.title}" />
		</h2>
		<img src="<c:out value="${detailNews.newsImg}"/>" alt="breakfast"
			height="300" width="300">
		<div class="articlebody">
			<p>
				<c:out value="${detailNews.texts}" />
			</p>
		</div>
	</section>

	<c:if test="${userId == '000000'}">
		<div class="favorite">
			<button type="button" class="round favoritebutton"
				onclick="location.href='../login.html'">
				マイページにお気に入り登録する</button>
		</div>
	</c:if> <c:if test="${userId != '000000'}">
		<link type="text/css"
			href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/start/jquery-ui.css"
			rel="stylesheet">
		<script type="text/javascript"
			src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="../js/favoriteajax.js"></script>
		<div class="favorite">
			<button type="button" class="round favoritebutton">
				マイページにお気に入り登録する</button>
		</div>

		<div id="dialogdemo">
			<p></p>
		</div>
	</c:if>

	<div class="wrapper">
		<div class="storeinfo">
			<h2>記事のお店</h2>
			<div class="storename">
				<p>
					店名:<a href="<c:out value="${detailNews.storeUrl}"/>"><c:out
							value="${detailNews.storeName}" /></a>
				</p>
			</div>
		</div>

		<c:if test="${userId == '000000'}">
			<div class="review">
				<button type="button" class="round reviewbutton"
					onclick="location.href='../login.html'">コメント投稿</button>
			</div>
		</c:if>
		<c:if test="${userId != '000000'}">
			<div class="review">
				<button type="button" class="round reviewbutton"
					onclick="location.href='./Comment?newsId=<c:out value="${detailNews.newsId}"/>'">
					コメント投稿</button>
			</div>
		</c:if>
	</div>
	</main>

	<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>

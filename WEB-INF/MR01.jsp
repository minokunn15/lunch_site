<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>朝食のススメメインページ</title>
<meta name="description"
	content="朝食のススメのメインページです。どんな朝食に関する記事があるかお楽しみください。" />
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/style.css">
<link rel="stylesheet" type="text/css" href="/tyoushoku/css/main.css">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
<meta name="viewport" content="width=360,initial-scale=1">
</head>

<body>
	<c:import url="/WEB-INF/header.jsp" />
	<main class="main">
	<section id="suggestarticle">
		<h2>本日のおすすすめ記事</h2>
		<ul class="wrap">
			<c:forEach var="suggestNews" items="${suggestList}"
				varStatus="status">
				<li class="box1 a"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${suggestNews.newsId}"/>'">
					<img src="<c:out value="${suggestNews.newsImg}"/>" alt="main"
					height="200" width="180">
					<div class="wrapperdetail">
						<div class="articledetail">
							<a
								href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${suggestNews.newsId}"/>"><h3>
									<c:out value="${suggestNews.title}" />
								</h3></a>
						</div>
						<div class="storename">
							<h3>
								<a href="<c:out value="${suggestNews.storeUrl}"/>"><c:out
										value="${suggestNews.storeName}" /></a>
							</h3>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</section>

	<section id="allarticle">
		<h2>記事一覧</h2>
		<ul class="wrap">
			<c:forEach var="news" items="${newsList}" begin="0" end="2">
				<li class="box2 a"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${news.newsId}"/>'">
					<img src="<c:out value="${news.newsImg}"/>" alt="main" height="100"
					width="100">
					<h3>
						<a href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${news.newsId}"/>"><c:out
								value="${news.title}" /></a>
					</h3>
				</li>
			</c:forEach>
		</ul>
		<ul class="wrap">
			<c:forEach var="news" items="${newsList}" begin="3" end="5">
				<li class="box2 d"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${news.newsId}"/>'">
					<img src="<c:out value="${news.newsImg}"/>" alt="main" height="100"
					width="100">
					<h3>
						<a href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${news.newsId}"/>"><c:out
								value="${news.title}" /></a>
					</h3>
				</li>
			</c:forEach>
		</ul>
		<p>
			<a class="next"
				href="/tyoushoku/Controller/SearchResult?freeword=&cate=all&area=&start=1&end=5">もっと見る</a>
		</p>
	</section>

	<section id="ranking">
		<h2>記事ランキング</h2>
		<ul class="wrap">
			<c:forEach var="rankNews" items="${rankNews}" begin="0" end="0">
				<li class="box3 a"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>'">
					<div id="gold">
						<div class="prize">
							<img src="/tyoushoku/images/ranking1.jpg" alt="main" height="40"
								width="40">
						</div>
						<img src="<c:out value="${rankNews.newsImg}"/>" alt="main"
							height="100" width="100">
						<h3>
							<a href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>"><c:out
									value="${rankNews.title}" />"</a>
						</h3>
					</div>
				</li>
			</c:forEach>
			<c:forEach var="rankNews" items="${rankNews}" begin="1" end="1">
				<li class="box3 b"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>'">
					<div id="silver">
						<div class="prize">
							<img src="/tyoushoku/images/ranking2.jpg" alt="main" height="40"
								width="40">
						</div>
						<img src="<c:out value="${rankNews.newsImg}"/>" alt="main"
							height="100" width="100">
						<h3>
							<a href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>"><c:out
									value="${rankNews.title}" />"</a>
						</h3>
					</div>
				</li>
			</c:forEach>
			<c:forEach var="rankNews" items="${rankNews}" begin="2" end="2">
				<li class="box3 c"
					onclick="location.href='/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>'">
					<div id="bronze">
						<div class="prize">
							<img src="/tyoushoku/images/ranking3.jpg" alt="main" height="40"
								width="40">
						</div>
						<img src="<c:out value="${rankNews.newsImg}"/>" alt="main"
							height="100" width="100">
						<h3>
							<a href="/tyoushoku/Controller/DetailNews?newsId=<c:out value="${rankNews.newsId}"/>"><c:out
									value="${rankNews.title}" />"</a>
						</h3>
					</div>
				</li>
			</c:forEach>
		</ul>
	</section>
	</main>

	<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
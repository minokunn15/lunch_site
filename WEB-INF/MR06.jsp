<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="朝食のススメにおけるマイページです。お気に入りに登録した記事などが確認できます。"/>
<title>朝食のススメマイページ</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/mypage.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script src="../js/jquery-1.8.3.js"></script>
<script src="../js/jquery.sidr.min.js"></script>
<script src="../js/mypage.js"></script>
<link rel="stylesheet" href="../css/stylesheets/jquery.sidr.dark.css">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />

    <main id="mypage">
        <h2><c:out value="${nickname}"/>さんのマイページ</h2>
        <section id="suggestarticle">
            <h3>本日のおすすすめ記事</h3>
                <ul id="wrap">
                	<c:forEach var="suggest" items="${suggestList}">
	                    <li class="box1 a">
	                        <img src="<c:out value="${suggest.newsImg}"/>" alt="breakfast" height="200" width="180">
	                        <div class="wrapperdetail">
	                            <div class="articledetail">
	                                <a href="./DetailNews?newsId=<c:out value="${suggest.newsId}"/>"><h4><c:out value="${suggest.title}" /></h4></a>
	                            </div>
	                            <div class="storename">
	                                <h4><a href="<c:out value="${suggest.storeUrl}"/>"><c:out value="${suggest.storeName}"/></a></h4>
	                            </div>
	                        </div>
	                    </li>
	                </c:forEach>
                </ul>
        </section>

        <section id="favoritearticle">
            <h3>お気に入り一覧</h3>
                <c:set var = "count" value="${fn:length(favoriteList)}" />
               	<c:if test="${count==0}">
                	<p class="none">お気に入り登録している記事はありません</p>
                <ul class="wrap2 foloatbox">
                </c:if>
                	<c:forEach var="favorite" items="${favoriteList}" begin="0" end="1">
	                    <li class="box2 a">
	                        <div class="favoritebox">
	                            <a href="./DetailNews?newsId=<c:out value="${favorite.newsId}"/>"><h4 class="favoritetitle"><c:out value="${favorite.title}"/></h4></a>
	                        </div>
	                        <div class="favoritebox">
	                            <h4><a href="<c:out value="${favorite.storeUrl}"/>"><c:out value="${favorite.storeName}"/></a></h4>
	                        </div>
	                    </li>
	                </c:forEach>
	                <c:if test="${count > 2}">
	                	<c:forEach var="favorite" items="${favoriteList}" begin="2" end="${count-1}">
		                    <li class="box3 a add">
		                        <div class="favoritebox">
		                            <a href="./DetailNews?newsId=<c:out value="${favorite.newsId}"/>"><h4 class="favoritetitle"><c:out value="${favorite.title}"/></h4></a>
		                        </div>
		                        <div class="favoritebox">
		                            <h4><a href="<c:out value="${favorite.storeUrl}"/>"><c:out value="${favorite.storeName}"/></a></h4>
		                        </div>
	                    	</li>
	                	</c:forEach>
	                </c:if>
                </ul>

                <c:if test="${count > 2}">
            		<p class="next">次へ</p>
            	</c:if>
        </section>
    </main>
	<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
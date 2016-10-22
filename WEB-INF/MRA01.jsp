<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="ライターさんの記事投稿ページです。新しい記事を投稿してください"/>
<title>ライターさん朝食記事投稿</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/writermain.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script src="../js/jquery-1.8.3.js"></script>
<script src="../js/jquery.sidr.min.js"></script>
<script src="../js/sider.js"></script>
<link rel="stylesheet" href="../css/stylesheets/jquery.sidr.dark.css">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />
    <main>
	    <section id="main">
	        <h2>記事を追加する</h2>

	        <div class="article_submit">
	            <form action="./AddNewsComfirm" method="post" enctype="multipart/form-data">
	                <ul>
	                    <li>
	                        <label for="title">タイトル</label>
	                        <input type="text" name="title" size=30 placeholder="記事のタイトル" id="title">
	                    </li>
	                    <li>
	                        <label for="img">メイン画像</label>
	                        <input type="file" name="articleimg" size="40" id="img">
	                    </li>
	                    <li>
	                        <label for="store">関連店舗</label>
	                        <select name="store" class="storeList" id="store">
	                        	<c:forEach var="store" items="${storeList}">
	                            	<option value="<c:out value="${store.storeId}"/>"><c:out value="${store.storeName}"/></option>
	                            </c:forEach>
	                        </select>
	                    </li>
	                    <li>
	                        <label for="text">本文</label>
	                        <textarea name="text" id="text"></textarea>
	                    </li>
	                    <li>
	                        <input type="submit" id="button" name="button" value="確認画面に飛ぶ">
	                    </li>
	                </ul>
	            </form>
	        </div>
	    </section>
    </main>
<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
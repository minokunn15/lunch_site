<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="投稿する記事の確認画面です。"/>
<title>ライターさん朝食記事確認</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/detail.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script type="text/javascript" charset="utf-8" src="../js/jquery-1.8.3.js"></script>
<script src="../js/jquery-1.8.3.js"></script>
<script src="../js/jquery.sidr.min.js"></script>
<script src="../js/sider.js"></script>
<link rel="stylesheet" href="../css/stylesheets/jquery.sidr.dark.css">
</head>
<body>
<c:import url="/WEB-INF/header.jsp" />
    <main id="main">
        <section id="detail">
            <h1>確認画面</h1>
            <h2><c:out value="${newNews.title}"/></h2>
            <img src="<c:out value="${newNews.newsImg}"/>" alt="mainImg" height="300" width="300">
            <div class="articlebody">
                <p>
 					<c:out value="${newNews.texts}"/>
                </p>
            </div>
        </section>

        <div class="favorite">
	        <form method="post" action="./AddNewsComplete">
	            <button type="submit" id="round" class="favoritebutton">
					記事を追加する。
	            </button>
	            <input name="token" type="hidden" value="<c:out value="${token}"/>"/>
	        </form>
        </div>
    </main>

<c:import url="/WEB-INF/footer.jsp" />
</body>
</html>

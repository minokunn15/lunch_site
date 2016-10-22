<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="朝食記事の検索結果表示ページです。このなかからお好みの記事を読んでみてください。"/>
    <title>朝食記事検索結果表示<c:out value="${start/5}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/searchresult.css">
    <link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
    <meta name="viewport" content="width=360,initial-scale=1">
</head>
<body>
	<c:import url="/WEB-INF/header.jsp" />

    <main id="main">
        <h2>記事検索結果一覧</h2>
        <div class="resultcount">
            <p>全ての記事<c:out value="${newsCount}"/>件中<span><c:out value="${searchCount}"/>件</span>見つかりました</p>
        </div>

        <ul class="selection" id="page_1">
	        <c:forEach var="searchResult" items="${searchResultList}" begin="0" end="4">
	            <li>
	                <div class="wrapper">
	                    <div class="articleimg">
	                        <img src="<c:out value="${searchResult.newsImg}"/>" alt="breakfast" width="100" height="100">
	                    </div>

	                    <div class="wrapper-right">
	                        <div class="title">
	                            <h3><a href="./DetailNews?newsId=<c:out value="${searchResult.newsId}"/>"><c:out value="${searchResult.title}"/></a></h3>
	                        </div>

	                        <div class="storename">
	                            <h3><a href="<c:out value="${searchResult.storeUrl}"/>"><c:out value="${searchResult.storeName}"/></a></h3>
	                        </div>
	                    </div>
	                </div>
	            </li>
	        </c:forEach>
	    </ul>


        <div id="pagenation">
        	<c:if test="${searchCount==0}">
        		<h5>該当する記事はありません。</h5>
        	</c:if>
        	<c:if test="${searchCount<=5}">
        		<h5>現在、1件~<c:out value="${searchCount}"/>件表示</h5>
        	</c:if>
        	<c:if test="${searchCount>5}">
	            <h5>現在、<c:out value="${start}"/>件～<c:out value="${end}"/>件表示</h5>
	            <ul class="pageNav01">
	            	<c:set var="countByPage" value="${countByPage}"/>
	            	<c:forEach var="i" begin="1" end="${countByPage}">
	            		<c:set var="start" value="${i*5-4}"/>
	                	<li><a href="./SearchResult?freeword=<c:out value="${freeword}"/>&area=<c:out value="${area}"/>&cate=<c:out value="${category}"/>&start=<c:out value="${start}"/>&end=<c:out value="${i*5}"/>"><c:out value="${i}"/></a></li>
	                </c:forEach>
	            </ul>
	        </c:if>
        </div>
    </main>
    <c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
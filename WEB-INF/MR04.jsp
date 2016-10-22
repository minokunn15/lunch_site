<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="朝食記事の検索ページです。好みのワードやジャンルによって記事を絞って記事を探してください。"/>
<title>朝食記事検索</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/search.css">
<script type="text/javascript" charset="utf-8" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="../js/tab.js"></script>
<script src="../js/jquery.sidr.min.js"></script>
<script src="../js/sider.js"></script>
<link rel="stylesheet" href="../css/stylesheets/jquery.sidr.dark.css">
<meta name="viewport" content="width=360,initial-scale=1">
</head>

<body>
    <c:if test="${userId == '000000'}">
		<header class="header">
		        <!-- ロゴ -->
	            <div class="logo">
	                <h1><a href="./Index">朝食のススメ</a></h1>
	            </div>
	            <!-- / ロゴ -->
	            <div class="header-right">
		            <div class="detail">
		            	<img src="../images/arrow.gif" alt="mark"><a href="">このサイトについて</a>
		            </div>
		            <!-- ナビゲーション -->
		            <nav id="navigation">
						<ul>
							<li><a href="./Search"><p class="round">記事検索</p></a></li>
							<li><a href="../login.html"><p class="round">ログイン</p></a></li>
						</ul>
		            </nav>
		         </div>
		</header>
	</c:if>
		<c:if test="${userId != '000000'}">
			<header class="header">
			        <!-- ロゴ -->
		            <div class="logo">
		                <h1><a href="./Index">朝食のススメ</a></h1>
		            </div>
		            <!-- / ロゴ -->
		            <div class="header-right">
			            <div class="detail">
			            	<img src="../images/arrow.gif"><a href="">このサイトについて</a>
			            </div>
			            <!-- ナビゲーション -->
			            <nav id="navigation" class="loginusernav">
		                    <a id="simple-menu" href="#sidr" class="round">メニュー</a>

		                    <div id="sidr">
		                      <ul>
		                        <li><a href="./Search">記事検索</a></li>
		                        <li><a href="./MyPage">マイページ</a></li>
		                        <li><a href="./Logout">ログアウト</a></li>
		                      </ul>
		                    </div>
			            </nav>
			         </div>
			</header>
		</c:if>

    <main id="search">
        <h2>記事検索</h2>
	        <form method="get" action="./SearchResult">
	        <div id="tabhoge">
	            <ul class="tab">
	                <li><a href="#freesearch"><span>フリーワード検索</span></a></li>
	                <li><a href="#detailsearch"><span>詳しく検索</span></a></li>
	            </ul>
	            <div id="freesearch">
	                <input type="text" class="freeword" size="40" name="freeword" placeholder="キーワード入力" maxlength="20"/>
	                <div id="suggest">
	                    <p>おすすめキーワード:朝食、ホテル、GW</p>
	                </div>
	            </div>

	            <div id="detailsearch">
	                <ul>
	                    <li><input type="text" class="area" size="40" name="area" placeholder="エリア（都道府県名)" maxlength="5"/></li>
	                    <li><img src="../images/small.gif" alt="sign"></li>
	                    <li>
	                    	<select name="cate" size="1">
	                    		<option value="all" selected>選択しない</option>
	                    		<c:forEach var="category" items="${categoryList}">
	                    			<option value="<c:out value="${category.categoryId}"/>"><c:out value="${category.storeCategory}"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </li>
	                </ul>
	            </div>
	        </div>
			<input type="hidden" name="start" value="1"/>
			<input type="hidden" name="end" value="5" />
	        <div id="searchdo">
	            <button type="button" onclick="submit()">検索する</button>
	        </div>
        </form>
    </main>
    <c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
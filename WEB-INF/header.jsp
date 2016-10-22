<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:if test="${userId == '000000'}">
		<header class="header">
		        <!-- ロゴ -->
	            <div class="logo">
	                <h1><a href="/tyoushoku/Controller/Index">朝食のススメ</a></h1>
	            </div>
	            <!-- / ロゴ -->
	            <div class="header-right">
		            <div class="detail">
		            	<img src="/tyoushoku/images/arrow.gif" alt="mark"><a href="/tyoushoku/prepare.html">このサイトについて</a>
		            </div>
		            <!-- ナビゲーション -->
		            <nav id="navigation">
						<ul>
							<li><a href="/tyoushoku/Controller/Search"><p class="round">記事検索</p></a></li>
							<li><a href="/tyoushoku/login.html"><p class="round">ログイン</p></a></li>
						</ul>
		            </nav>
		         </div>
		</header>
	</c:if>
	<c:if test="${userId != '000000'}">
			<script src="/tyoushoku/js/jquery-1.8.3.js"></script>
			<script src="/tyoushoku/js/jquery.sidr.min.js"></script>
			<script src="/tyoushoku/js/sider.js"></script>
		<header class="header">
		        <!-- ロゴ -->
	            <div class="logo">
	                <h1><a href="/tyoushoku/Controller/Index">朝食のススメ</a></h1>
	            </div>
	            <!-- / ロゴ -->
	            <div class="header-right">
		            <div class="detail">
		            	<img src="/tyoushoku/images/arrow.gif" alt="sign"><a href="">このサイトについて</a>
		            </div>
		            <!-- ナビゲーション -->
		            <nav id="navigation" class="loginusernav">
	                    <a id="simple-menu" href="#sidr" class="round">メニュー</a>

	                    <div id="sidr">
	                      <ul>
	                        <li><a href="/tyoushoku/Controller/Search">記事検索</a></li>
	                        <li><a href="/tyoushoku/Controller/MyPage">マイページ</a></li>
	                        <li><a href="/tyoushoku/Controller/Logout">ログアウト</a></li>
	                      </ul>
	                    </div>
		            </nav>
		         </div>
		</header>
	</c:if>
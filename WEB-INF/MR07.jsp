<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="description" content="朝食記事に関するコメントページです。記事に関して感じたことをコメントしてみてください。"/>
<title>朝食記事コメント投稿</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/comment.css">
<link rel="stylesheet" type="text/css" href="../css/font-awesome-4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/font-awesome-4.6.3/css/font-awesome.css">
<meta name="viewport" content="width=360,initial-scale=1">
<script type="text/javascript" charset="utf-8" src="../js/jquery-1.8.3.js"></script>
<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/start/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="/tyoushoku/css/stylesheets/jquery.sidr.dark.css">
</head>

<body>
	<c:import url="/WEB-INF/header.jsp" />

    <main class="main">
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="../js/commentajax.js"></script>
        <section>
            <h2>コメント投稿</h2>

            <div id="comment_submit">
                <form onsubmit="return false">
                    <ul>
                        <li class="commentTitle">
                            <label for="title">記事タイトル</label>
                            <input type="text" size="30" value="<c:out value="${title}"/>" disabled="disabled" id="title">
                        </li>
                        <li class="evaluation">
                            <label for="eval">評価</label>
                            <select class="evalcount" name="evalcount" id="eval">
                                <option selected value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </li>
                        <li class="comment">
                            <label for="comment">コメント</label>
                            <textarea class="textcomment" name="comment" id="comment"></textarea>
                        </li>
                        <li>
                        	<input type="hidden" value="<c:out value="${nickname}" />" class="nickname">
                        </li>
                        <li>
                            <button type="button" id="commentbutton" name="button">投稿する</button>
                        </li>
                    </ul>
                    <div id="dialogdemo">
                    	<p></p>
                    </div>
              </form>
            </div>
        </section>

        <section class="comment_evaluation">
            <h2>この記事の総合評価</h2>
            <div id="comment_evaluation">
                    <div id="star">
                    	<fmt:parseNumber var="ave" value="${commentRating.avgValue}" />
                    	<c:if test="${ave!=0}">
	                    	<c:forEach begin="0" end="${ave-1}">
		                        <i class="fa fa-star" aria-hidden="true"></i>
		                    </c:forEach>
		                <c:if test="${ave!=5}">
		                    <c:forEach begin="0" end="${4-ave}">
		                        <i class="fa fa-star-o" aria-hidden="true"></i>
	                        </c:forEach>
	                    </c:if>
	                        <span>評価数:<c:out value="${commentRating.count}" /></span>
	                        <span>平均評価:<c:out value="${commentRating.avgValue}" /></span>
                        </c:if>
                        <c:if test="${ave==0}">
                        	<p>この記事はまだコメントされていません</p>
                        </c:if>
                    </div>

                    <ul id="starcount">
                    	<c:forEach var="countByRating" items="${commentRating.valueList}" begin="0" end="4" varStatus="status">
                    		<fmt:parseNumber var="count" value="${countByRating}" />
                        	<li>
                        		<span>星<fmt:formatNumber value="${status.index+1}"/>つ</span>
								<progress value="${count}" max="${commentRating.count}"></progress>
                        	</li>
                        </c:forEach>
                    </ul>
            </div>
        </section>

        <section class="comment_all">
            <h2>みんなのコメント内容</h2>
            <ul class="otherlist">
            	<c:forEach var="rating" items="${otherComments}">
	                <li>
	                	<fmt:parseNumber var="ratingNum" value="${rating.rating}" />
	                	<c:forEach begin="0" end="${ratingNum-1}">
		                    <i class="fa fa-star" aria-hidden="true"></i>
		                </c:forEach>
		                <p class="other_comment"><c:out value="${rating.comment}" /></p>
		                <p class="other">by <c:out value="${rating.nickname}" /></p>
	                </li>
                </c:forEach>
            </ul>
        </section>
    </main>
    <c:import url="/WEB-INF/footer.jsp" />
</body>
</html>
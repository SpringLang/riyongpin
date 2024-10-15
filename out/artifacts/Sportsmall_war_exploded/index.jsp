<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>体育购物商城</title>
</head>
<body>
	<div id="header" class="wrap">
		<jsp:include page="common/top.jsp" />
	<div id="main" class="wrap">
		<div class="lefter">
			<jsp:include page="common/left.jsp" />
		</div>
		<div class="main">


			<div class="price-off ">
				<h2>今日特价</h2>
				<ul class="product clearfix">
					<c:forEach items="${applicationScope.specialPriceProductList }" var="spProduct">
					<li>
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product/showProduct/${spProduct.id}.do"
								   target="_blank"><img src="${pageContext.request.contextPath}/${spProduct.proPic }" /></a>
							</dt>
							<dd class="title">
								<a href="${pageContext.request.contextPath}/product/showProduct/${spProduct.id}.do"
								   target="_blank">${spProduct.name }</a>
							</dd>
							<dd class="price">￥${spProduct.price }</dd>
						</dl>
					</li>
				</c:forEach>
				</ul>
			</div>
			<div class="side">

				<div class="news-list">
					<h4>最新公告</h4>
					<ul>
						<c:forEach items="${noticeList }" var="notice">
							<li><a
								href="${pageContext.request.contextPath}/notice/showNotice/${notice.id}.do">${notice.title }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="spacer"></div>
				<div class="news-list">
					<h4>新闻动态</h4>
					<ul>
						<c:forEach items="${newsList }" var="news">
							<li><a href="${pageContext.request.contextPath}/news/showNews/${news.id}.do">${news.title }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="spacer clear"></div>

			<div class="hot">
				<h2>热卖推荐</h2>
				<ul class="product clearfix">
					<c:forEach items="${hotProductList }" var="hProduct">
						<li>
							<dl>
								<dt>
									<a href="${pageContext.request.contextPath}/product/showProduct/${hProduct.id}.do"
										target="_blank"><img src="${pageContext.request.contextPath}/${hProduct.proPic }" /></a>
								</dt>
								<dd class="title">
									<a href="${pageContext.request.contextPath}/product/showProduct/${hProduct.id}.do"
										target="_blank">${hProduct.name }</a>
								</dd>
								<dd class="price">￥${hProduct.price }</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>

		</div>
		<div class="clear"></div>
	</div>

	<div id="footer">
		<jsp:include page="common/footer.jsp" />
	</div>
</body>
</html>